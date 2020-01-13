package OpenTableStage;

import constants.ChangeScene;
import constants.Global;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.GoogleSheetsKeysTable;
import model.SheetTable;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static constants.ChangeScene.changeScene;
import static constants.Constants.entityManager;
import static constants.Global.displayGoogleSheetCode;
import static constants.Global.displaySheetName;

public class OpenTableController implements Initializable {
    @FXML
    private TreeTableView<String> tableView;
    @FXML
    private TreeTableColumn<String, String> column;

    TreeItem<String> parent=new TreeItem<>("Your Tables");

    protected boolean empty;

    private List<GoogleSheetsKeysTable> cpytableNames;
    private List<SheetTable> cpyTableSheets;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        MakeTable(parent);
        column.setCellValueFactory((TreeTableColumn.CellDataFeatures<String, String> param) -> new SimpleStringProperty(param.getValue().getValue()));

        if(empty){
            parent= new TreeItem<>("You don't have any tables imported.");
        }else {
            tableView.setRoot(parent);
            tableView.setRowFactory(tv -> {
                TreeTableRow<String> row = new TreeTableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2 && (!row.isEmpty())) {
                        String rowData = row.getItem();
                        searchByName(rowData);
                        Parent createParent = null;
                        try {
                            createParent = FXMLLoader.load(ChangeScene.class.getResource("/SplashOpenTableSample.fxml"));
                        } catch (IOException e) {

                            e.printStackTrace();
                        }
                        assert createParent != null;
                        Scene createScene = new Scene(createParent);
                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        window.setScene(createScene);
                        window.show();

                    }
                });
                return row;
            });
        }
    }

    private void MakeTable(TreeItem<String> parent){
        cpytableNames = new ArrayList<>();
        cpyTableSheets = new ArrayList<>();
        try {
            String qlQuery = "SELECT a FROM GoogleSheetsKeysTable a WHERE a.secondId LIKE('"+ Global.getId_reminder() +"')";
            Query query1 = entityManager.createQuery(qlQuery);
            cpytableNames = query1.getResultList();


        } catch ( NoResultException nre) {

        } finally {
            if(cpytableNames.isEmpty())
                empty= true;
        }
        for (GoogleSheetsKeysTable el : cpytableNames){
            TreeItem<String> element=new TreeItem<>(el.getNameOfKey());
            String q2Query = "SELECT b FROM SheetTable b WHERE b.TableId ="+el.getId();
            Query query2 = entityManager.createQuery(q2Query);
            cpyTableSheets = query2.getResultList();
            for (SheetTable e : cpyTableSheets){
                System.out.println(e.getSheetName());
                element.getChildren().add(new TreeItem<>(e.getSheetName()));
            }
            parent.getChildren().add(element);
        }

    }

    private void searchByName(String NameOfKey){
        List<SheetTable> SheetNames= new ArrayList<>();
        String qlQuery = "SELECT a FROM SheetTable a WHERE a.SheetName LIKE('"+ NameOfKey +"')";
        Query query1 = entityManager.createQuery(qlQuery);
        SheetNames = query1.getResultList();
        boolean flag=false;
        for (GoogleSheetsKeysTable el : cpytableNames)
            for (SheetTable e: SheetNames){
                System.out.println();
                System.out.println(el.getNameOfKey());
                System.out.println(NameOfKey);
                if(e.getTableId()==el.getId()||el.getNameOfKey().equals(NameOfKey)){
                    System.out.println(true);
                    Global.setGoogleSheet_ID(el.getKey());
                    Global.setSheetName_reminder(e.getSheetName());
                    displaySheetName();
                    displayGoogleSheetCode();
                    break;
                }else System.out.println(false);

            }


    }

    @FXML
    void backAction(ActionEvent event) throws IOException {
        changeScene(event,"/OpenImportSample.fxml","Open or Import");
    }


}
