package OpenImportStage;
import javafx.application.Application;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;


public class KeyGetter {
    private  String key;
    private  String nameOfTable;
    public  void keyAndNameGetter(){
        Stage window= new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Key recognition");

        Label label1=new Label("Table Key:");
        TextField keyField=new TextField();
        Label label2=new Label("Table Name:");
        TextField TableNameField=new TextField();

        Button okButton=new Button("Import");
        okButton.setOnAction(e -> {
            key=keyField.getText();
            nameOfTable=TableNameField.getText();
            window.close();
        });

        HBox layoutForField1=new HBox(5);
        layoutForField1.getChildren().addAll(label1,keyField);
        layoutForField1.setAlignment(Pos.CENTER);
        HBox layoutForField2=new HBox(5);
        layoutForField2.getChildren().addAll(label2,TableNameField);
        layoutForField2.setAlignment(Pos.CENTER);
        VBox layout = new VBox(20);
        layout.getChildren().addAll(layoutForField1,layoutForField2,okButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene= new Scene(layout,350,150);
        window.setScene(scene);
        window.showAndWait();

    }

    public String getKey() {
        return key;
    }

    public String getNameOfTable() {
        return nameOfTable;
    }
}
