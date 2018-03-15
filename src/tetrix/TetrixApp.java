package tetrix;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import tetrix.model.Model;

public class TetrixApp extends Application {
    public static final String TITLE_OF_PROGRAM_WINDOW = "JTetrix";
    public static final int TILE_SIZE = 30;
    private static final int SIDE_PANEL_WIDTH = 110;
    private static final double FPS = 60;
    private static final int WINDOW_WIDTH = TILE_SIZE * Model.FIELD_WIDTH + SIDE_PANEL_WIDTH;
    private static final int WINDOW_HEIGHT = TILE_SIZE * (Model.FIELD_HEIGHT + 1);
    private Model model = new Model();
    private AnimationTimer timer;
    private double time = 0;
    private boolean isGamePaused = false;
    private TetrixController controller;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                time += (1 / FPS);
                if (time >= calculateDelayByGameLevel(model.getLevel())) {
                    update();
                    render();
                    time = 0;
                }
            }
        };
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TetrixFXML.fxml"));
        Parent root = fxmlLoader.load();
        this.controller = fxmlLoader.getController();
        this.controller.setOnStartNewGame(event -> onStartNewGame());
        this.controller.setOnTogglePause(event -> onTogglePause());
        this.controller.setOnExitEvent(event -> onExitEvent());
        primaryStage.setTitle(TITLE_OF_PROGRAM_WINDOW);
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        scene.setOnKeyReleased(this::onKeyReleased);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        render();
        this.timer.start();
    }

    private static double calculateDelayByGameLevel(final int level) {
        return 2 / (1 + level);
    }

    public void onKeyReleased(final KeyEvent event) {
        final KeyCode keyCode = event.getCode();
        if (!this.isGamePaused) {
            if (keyCode.equals(KeyCode.LEFT)) {
                model.moveActiveTetraminoLeft();
            } else if (keyCode.equals(KeyCode.RIGHT)) {
                model.moveActiveTetraminoRight();
            } else if (keyCode.equals(KeyCode.UP)) {
                model.rotateActiveTetraminoCounterclockwise();
            } else if (keyCode.equals(KeyCode.DOWN)) {
                model.rotateActiveTetraminoClockwise();
            } else if (keyCode.equals(KeyCode.SPACE)) {
                model.dropActiveTetraminoDown();
            } else if (keyCode.equals(KeyCode.SPACE)) {
                model.dropActiveTetraminoDown();
            }
        }
        if (keyCode.equals(KeyCode.P)) {
            onTogglePause();
        } else if (keyCode.equals(KeyCode.N)) {
            onStartNewGame();
        } else if (keyCode.equals(KeyCode.Q)) {
            onExitEvent();
        }
        render();
        event.consume();
    }

    private void onStartNewGame() {
        this.model.prepareNewGame();
        this.isGamePaused = false;
        this.timer.start();
        render();
    }

    private void onTogglePause() {
        if (this.isGamePaused) {
            this.isGamePaused = false;
            this.timer.start();
        } else {
            this.isGamePaused = true;
            this.timer.stop();
        }
    }

    private void onExitEvent() {
        final Alert alert = new Alert(Alert.AlertType.WARNING, "Do you really want to exit?",
                ButtonType.YES, ButtonType.NO);
        alert.setTitle("Confirm exit, please");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            Platform.exit();
            System.exit(0);
        } else {
            alert.close();
        }
    }

    private void update() {
        this.model.tick();
        System.out.println("ty = " + model.getActiveTetramino().getY());
    }

    private void render() {
        GraphicsContext gc = this.controller.getGraphicsContext();
        gc.clearRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        renderField(gc);
        renderSidePanel(gc);
        if (this.model.isGameOver()) {
            renderGameOverMessage(gc);
            timer.stop();
        } else {
            renderActiveTetramino(gc);
            if (isGamePaused) {
                renderPauseMessage(gc);
            }
        }
    }

    private void renderField(GraphicsContext gc) {
        gc.setStroke(Color.GREEN.darker());
        for (int y = 0; y < Model.FIELD_HEIGHT; ++y) {
            for (int x = 0; x < Model.FIELD_WIDTH; ++x) {
                gc.strokeOval(x * TILE_SIZE, y * TILE_SIZE, 2, 2);
                if (this.model.getTileAt(x, y)) {
                    RenderUtils.renderBlock(gc, Color.BISQUE, x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE);
                }
            }
        }
        gc.strokeLine(Model.FIELD_WIDTH * TILE_SIZE, 0, Model.FIELD_WIDTH * TILE_SIZE,
                Model.FIELD_HEIGHT * TILE_SIZE);
    }

    private void renderActiveTetramino(GraphicsContext gc) {
        this.model.getActiveTetramino().getBlocks().forEach(block -> {
            RenderUtils.renderBlock(gc, this.model.getNextTetramino().getColor(), block.getX() * TILE_SIZE,
                    block.getY() * TILE_SIZE, TILE_SIZE);
        });
    }

    private void renderSidePanel(GraphicsContext gc) {
        final int leftBound = Model.FIELD_WIDTH * TILE_SIZE + 20;
        //Render next piece on the side panel
        final int x = leftBound + 12;
        final int y = 60;
        this.model.getNextTetramino().getBlocks().forEach(block -> {
            RenderUtils.renderBlock(gc, this.model.getNextTetramino().getColor(),
                    x + block.getRelativeX() * 3 * TILE_SIZE / 4,
                    y + block.getRelativeY() * 3 * TILE_SIZE / 4,
                    3 * TILE_SIZE / 4);
        });
        gc.setStroke(Color.GREEN.darker().darker());
        gc.setFont(new Font("Ubuntu Mono", 18));
        gc.strokeText("NEXT:\n", leftBound, 20);
        gc.strokeText("NEXT:\n", leftBound, 20);
        gc.strokeText("SCORE:\n" + this.model.getScore(), leftBound, 170);
        gc.strokeText("LINES:\n" + this.model.getNumberOfRemovedLines(), leftBound, 220);
        gc.strokeText("LEVEL:\n" + this.model.getLevel(), leftBound, 260);

    }

    private void renderGameOverMessage(GraphicsContext gc) {
        gc.setFont(new Font("Ubuntu Mono", 40));
        gc.setStroke(Color.RED);
        gc.strokeText("GAME OVER!!!", 50, 250);
    }

    private void renderPauseMessage(GraphicsContext gc) {
        gc.setFont(new Font("Ubuntu Mono", 40));
        gc.setStroke(Color.GREEN);
        gc.strokeText("GAME PAUSED", 50, 250);
    }

    public static void main(String[] args) {
        //Font.getFamilies().forEach(System.out::println);
        launch(args);
    }
}
