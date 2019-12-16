package CreateAccount;

import constants.CloseWindow;
import constants.Constants;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import additionalScenes.ConfirmBox;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CreateAccountFX extends Application {

    Stage window;
    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) throws Exception{

        window=primaryStage;

        Parent root= FXMLLoader.load(getClass().getResource("/createSample.fxml"));

        window.setTitle("Create Account");

        window.setOnCloseRequest(e -> {
            e.consume();
            CloseWindow.closeProgram(window);
        });

        window.setScene(new Scene(root,352,204));
        window.show();
    }
}
