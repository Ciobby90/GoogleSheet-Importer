package GoogleTableStage;

import constants.CloseWindow;
import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class GoogleTableFX extends Application {
    Stage window;
    public static void main(String[] args){ launch(args);}

    @Override
    public void start(Stage primaryStage) throws Exception {
        window=primaryStage;
        Parent root= FXMLLoader.load(getClass().getResource("/SplashOpenTableSample.fxml"));
        window.setScene(new Scene(root));
        window.show();
    }
}
