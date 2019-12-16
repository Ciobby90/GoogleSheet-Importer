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

public class OpenTableController implements Initializable {
    @FXML
    private TreeTableView<String> tableView;
    @FXML
    private TreeTableColumn<String, String> column;

    TreeItem<String> parent=new TreeItem<>("Your Tables");

    protected boolean empty;

    private List<GoogleSheetsKeysTable> cpytableNames;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        parent.getChildren().setAll();
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
        try {
            String qlQuery = "SELECT a FROM GoogleSheetsKeysTable a WHERE a.id LIKE('"+ Global.getId_reminder() +"')";
            Query query = entityManager.createQuery(qlQuery);
            cpytableNames = query.getResultList();
        } catch ( NoResultException nre) {

        } finally {
            if(cpytableNames.isEmpty())
                empty= true;
        }
        for (GoogleSheetsKeysTable el : cpytableNames)
            parent.getChildren().add(new TreeItem<>(el.getNameOfKey()));
    }

    private void searchByName(String NameOfKey){
        for (GoogleSheetsKeysTable el : cpytableNames)
            if(el.getNameOfKey().equals(NameOfKey)){
                Global.setGoogleSheet_ID(el.getKey());
                displayGoogleSheetCode();
            }


    }

    @FXML
    void backAction(ActionEvent event) throws IOException {
        changeScene(event,"/OpenImportSample.fxml","Open or Import");
    }


}
