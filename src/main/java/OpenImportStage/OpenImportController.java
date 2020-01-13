package OpenImportStage;


import additionalScenes.Alert;
import additionalScenes.ConfirmBox;
import constants.Global;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.GoogleSheetsKeysTable;
import model.SheetTable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static constants.ChangeScene.changeScene;
import static constants.Constants.entityManager;

public class OpenImportController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if(!entityManager.isOpen()){     entityManager.getTransaction().begin();    }
    }

    @FXML
    void importNew(ActionEvent event) {
        KeyGetter keyGetter=new KeyGetter();
        keyGetter.keyAndNameGetter();
        String key=keyGetter.getKey();
        String nameOfTable=keyGetter.getNameOfTable();
        String SheetName=keyGetter.getSheetName();
        if(!(key.isEmpty()&&nameOfTable.isEmpty())) {
            System.out.println(key);
            System.out.println(nameOfTable);
            GoogleSheetsKeysTable g = new GoogleSheetsKeysTable();
            g.setKey(key);
            g.setNameOfKey(nameOfTable);
            g.setSecondId(Global.getId_reminder());
            entityManager.getTransaction().begin();
            entityManager.persist(g);
            SheetTable s =new SheetTable();
            s.setSheetName(SheetName);
            s.setTableId(g.getId());
            entityManager.persist(s);
            entityManager.getTransaction().commit();
            System.out.println("Table imported");
            Alert.display("Notification", "Table imported");

        }else {
            Alert.display("Notification", "Key or Name of Key empty");
        }
    }

    @FXML
    void openTable(ActionEvent event) throws IOException {
        changeScene(event,"/OpenTableSample.fxml","Open Table");
    }

    @FXML
    void logOut(ActionEvent event) throws IOException {
        if(ConfirmBox.display("LogOut","Sure u wanna logout?"));{
            changeScene(event,"/loginSample.fxml","Log In");
        }
    }



}


