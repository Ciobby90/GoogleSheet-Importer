package OpenTableStage;

import constants.CloseWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class OpenTableFx extends Application {

    Stage window;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        window=primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/OpenTableSample.fxml"));
        window.setTitle("Open Table");

        window.setOnCloseRequest(e -> {
            e.consume();
            CloseWindow.closeProgram(window);
        });

        window.setScene(new Scene(root));
        window.show();
    }


}
