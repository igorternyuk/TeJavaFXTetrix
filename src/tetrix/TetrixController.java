package tetrix;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class TetrixController implements Initializable {

    @FXML
    private Canvas canvas;
    private GraphicsContext gc;
    private Consumer<ActionEvent> onStartNewGame, onTogglePause, onExitEvent;

    public Canvas getCanvas() {
        return this.canvas;
    }

    public GraphicsContext getGraphicsContext() {
        return this.gc;
    }

    public void setOnStartNewGame(Consumer<ActionEvent> onStartNewGame) {
        this.onStartNewGame = onStartNewGame;
    }

    public void setOnTogglePause(Consumer<ActionEvent> onTogglePause) {
        this.onTogglePause = onTogglePause;
    }

    public void setOnExitEvent(Consumer<ActionEvent> onExitEvent) {
        this.onExitEvent = onExitEvent;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.gc = canvas.getGraphicsContext2D();
    }

    public void startNewGame(ActionEvent actionEvent) {
        this.onStartNewGame.accept(actionEvent);
    }

    public void onTogglePause(ActionEvent actionEvent) {
        this.onTogglePause.accept(actionEvent);
    }

    public void onExit(ActionEvent actionEvent) {
        this.onExitEvent.accept(actionEvent);
    }
}
