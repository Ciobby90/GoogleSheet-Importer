package additionalScenes;

import javafx.application.Application;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class Alert{
        public static void display(String title, String message){
            Stage window=new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle(title);
            window.setMaxWidth(300);

            Label label = new Label(message);

            Button closeWindow= new Button("Close");
            closeWindow.setOnAction(e -> window.close());

            closeWindow.setOnKeyPressed(event ->{
                switch (event.getCode()){
                    case ENTER:  window.close();
                }
            } );


            VBox layout = new VBox(20);
            layout.getChildren().addAll(label,closeWindow);
            layout.setAlignment(Pos.CENTER);

            Scene scene= new Scene(layout,300,100);
            window.setScene(scene);
            window.showAndWait();
        }
}
