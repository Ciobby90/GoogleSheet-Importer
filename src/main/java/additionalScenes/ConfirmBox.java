package additionalScenes;

import javafx.application.Application;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class ConfirmBox {
    static boolean answer;
    public static boolean display(String title, String message){
        Stage window= new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMaxWidth(300);
        Label label1= new Label(message);

        Button YesButton= new Button("Yes");
        Button NoButton= new Button("No");

        YesButton.setOnAction(e ->{
            answer=true;
            window.close();
        });
        NoButton.setOnAction(e ->{
            answer=false;
            window.close();
        });

        window.setOnCloseRequest(e ->{
            e.consume();
            answer=false;
            window.close();
        });

        HBox layout= new HBox(20);
        layout.getChildren().addAll(YesButton,NoButton);
        layout.setAlignment(Pos.CENTER);
        VBox layout2= new VBox(25);
        layout2.getChildren().addAll(label1,layout);
        layout2.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout2,300,100);
        window.setScene(scene);
        window.showAndWait();
        System.out.println(answer);
        return answer;
    }
}
