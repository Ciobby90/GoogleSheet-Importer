package constants;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChangeScene {
    public static void changeScene(ActionEvent event,String path,String title) throws IOException {
        Parent createParent = FXMLLoader.load(ChangeScene.class.getResource(path));
        Scene createScene = new Scene(createParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle(title);
        window.setScene(createScene);
        window.show();
    }
}
