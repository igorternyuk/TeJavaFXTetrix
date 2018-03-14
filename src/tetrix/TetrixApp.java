package tetrix;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TetrixApp extends Application {
    public static final int TILE_SIZE = 40;
    public static final String TITLE_OF_PROGRAM_WINDOW = "JTetrix";
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("TetrixFXML.fxml"));
        primaryStage.setTitle(TITLE_OF_PROGRAM_WINDOW);
        primaryStage.setScene(new Scene(root, 400, 600));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
