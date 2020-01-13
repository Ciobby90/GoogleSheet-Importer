package GoogleTableStage;


import SheetDataExtracter.sheetsJava;
import additionalScenes.Alert;
import constants.Global;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import static SheetDataExtracter.sheetsJava.getValues;
import static constants.ChangeScene.changeScene;
import static constants.Constants.entityManager;

public class GoogleTableController implements Initializable {
    @FXML
    private TableColumn<ObservableList<StringProperty>, String> column;

    private ObservableList<ObservableList> data;
    @FXML
    private TableView<ObservableList<StringProperty>> tableView;

    @FXML
    void backButton(ActionEvent event) throws IOException {
        changeScene(event,"/OpenImportSample.fxml","Open or Import");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateTable(tableView);
    }
    private TableColumn<ObservableList<StringProperty>, String> createColumn(final int columnIndex, String columnTitle,TableColumn<ObservableList<StringProperty>, String> column) {
        column = new TableColumn<>();
        column.setText(columnTitle);
        column.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList<StringProperty>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList<StringProperty>, String> cellDataFeatures) {
                ObservableList<StringProperty> values = cellDataFeatures.getValue();
                if (columnIndex >= values.size()) {
                    return new SimpleStringProperty("");
                } else {
                    return cellDataFeatures.getValue().get(columnIndex);
                }
            }
        });
        return column;
    }
    private void populateTable( final TableView<ObservableList<StringProperty>> table) {
        table.getItems().clear();
        table.getColumns().clear();

        Task<Void> task = new Task<Void>() {
            List<List<Object>> in = DataGenerator.getNext();
            @Override
            protected Void call() throws Exception {
                //header
                Iterator iter=in.iterator();
                    final Object headerLine = iter.next();
                    final List<Object> headerValues = (List<Object>) headerLine;
                    Iterator headerIter= headerValues.iterator();
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int Column = 0; Column < headerValues.size(); Column++) {
                                table.getColumns().add(
                                        createColumn(Column, (String) headerIter.next(),column));
                            }
                        }
                    });

                // Data:
                in.remove(0);


                String dataLine;
                for(List list : in) {
                    final List<String> dataValues = list;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            // Add additional columns if necessary:
                            for (int columnIndex = table.getColumns().size(); columnIndex < dataValues.size(); columnIndex++) {
                                table.getColumns().add(createColumn(columnIndex, "",column));
                            }
                            // Add data to table:
                            ObservableList<StringProperty> data = FXCollections
                                    .observableArrayList();
                            for (String value : dataValues) {
                                data.add(new SimpleStringProperty(value));
                            }
                            table.getItems().add(data);
                        }
                    });
                }
                return null;
            }
        };
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }
    private static class DataGenerator {

        static List<List<Object>> getNext() {
            List<List<Object>> list = new ArrayList<>();
            try {
                list= getValues();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            }
            return list;
        }

    }
}
