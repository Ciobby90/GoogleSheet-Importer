package additionalScenes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SplashLoginFX extends Application {

    Stage window;
    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) throws Exception{

        window=primaryStage;

        Parent root= FXMLLoader.load(getClass().getResource("/SplashLoginSample.fxml"));

        window.setScene(new Scene(root));
        window.show();
    }
}
