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
import javafx.stage.Stage;
import tetrix.model.Model;

public class TetrixApp extends Application {
    private static final String TITLE_OF_PROGRAM_WINDOW = "JTetrix";
    private static final int SIDE_PANEL_WIDTH = 100;
    private static final double FPS = 60.0;
    private static final int WINDOW_WIDTH = Renderer.TILE_SIZE * Model.FIELD_WIDTH + SIDE_PANEL_WIDTH;
    private static final int WINDOW_HEIGHT = Renderer.TILE_SIZE * (Model.FIELD_HEIGHT + 1);
    private static final String EXIT_DIALOG_WINDOW_MESSAGE = "Do you really want to exit?";
    private static final String EXIT_DIALOG_WINDOW_TITLE = "Confirm exit, please";

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
                final double delay = calculateDelayByGameLevel(model.getLevel());
                //System.out.println("Current timer delay = " + delay);
                if (time >= delay) {
                    updatePhase();
                    renderPhase();
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
        renderPhase();
        this.timer.start();
    }

    private static double calculateDelayByGameLevel(final int level) {
        return 2.0f / (1.0f + level);
    }

    private void onKeyReleased(final KeyEvent event) {
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
        renderPhase();
        event.consume();
    }

    private void onStartNewGame() {
        this.model.prepareNewGame();
        this.isGamePaused = false;
        this.timer.start();
        renderPhase();
    }

    private void onTogglePause() {
        if (this.isGamePaused) {
            this.isGamePaused = false;
            this.timer.start();
        } else {
            this.isGamePaused = true;
            this.timer.stop();
        }
        renderPhase();
    }

    private void onExitEvent() {
        final Alert alert = new Alert(Alert.AlertType.WARNING, EXIT_DIALOG_WINDOW_MESSAGE,
                ButtonType.YES, ButtonType.NO);
        alert.setTitle(EXIT_DIALOG_WINDOW_TITLE);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            Platform.exit();
            System.exit(0);
        } else {
            alert.close();
        }
    }

    private void updatePhase() {
        this.model.tick();
    }

    private void renderPhase() {
        GraphicsContext gc = this.controller.getGraphicsContext();
        gc.clearRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        Renderer.renderField(gc, this.model);
        Renderer.renderSidePanel(gc, this.model);
        if (this.model.isGameOver()) {
            Renderer.renderGameOverMessage(gc);
            timer.stop();
        } else {
            Renderer.renderActiveTetramino(gc, this.model);
            if (isGamePaused) {
                Renderer.renderPauseMessage(gc);
            }
        }
    }

    public static void main(String[] args) {
        //Font.getFamilies().forEach(System.out::println);
        launch(args);
    }
}
