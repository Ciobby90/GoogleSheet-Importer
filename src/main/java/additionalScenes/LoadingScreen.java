package additionalScenes;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoadingScreen {
    public static void loading(){
        Stage window=new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMaxWidth(300);

        Label label = new Label("Loading");

        VBox layout = new VBox(20);
        layout.getChildren().addAll(label);
        layout.setAlignment(Pos.CENTER);


        Scene scene= new Scene(layout,300,100);
        window.setScene(scene);
        window.showAndWait();
    }

}
