package additionalScenes;

import constants.CloseWindow;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SplashLoginController implements Initializable {
    @FXML
    private AnchorPane pane;


    class SplashScreen extends Thread{
        @Override
        public void run(){
            try {
                Thread.sleep(100);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Parent root= null;
                        try {
                            root = FXMLLoader.load(getClass().getResource("/loginSample.fxml"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Stage window=new Stage();
                        window.setTitle("Log In");
                        window.setOnCloseRequest(e -> {
                            e.consume();
                            CloseWindow.closeProgram(window);
                        });
                        window.setScene(new Scene(root));
                        window.show();

                        pane.getScene().getWindow().hide();
                    }
                });

            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new SplashScreen().start();
    }
}
