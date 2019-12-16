package OpenImportStage;

import additionalScenes.ConfirmBox;
import constants.CloseWindow;
import constants.Constants;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OpenImportFX extends Application {
    Stage window;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        window=primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/OpenImportSample.fxml"));
        window.setTitle("Open or Import");

        window.setOnCloseRequest(e ->{
            e.consume();
            CloseWindow.closeProgram(window);
        });

        window.setScene(new Scene(root));
        window.show();
    }

}
