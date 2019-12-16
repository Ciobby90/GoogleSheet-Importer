package loginStage;

import additionalScenes.Alert;
import constants.ChangeScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import javax.persistence.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static constants.ChangeScene.changeScene;
import static constants.Constants.entityManager;
import static constants.Global.display;
import static constants.Global.setId_reminder;

public class loginController implements Initializable {

    @FXML
    private PasswordField password;

    @FXML
    private TextField mail;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if(!entityManager.isOpen()){     entityManager.getTransaction().begin();    }
    }


    @FXML
    void checkLogin(ActionEvent event) throws Exception {
        if(!mail.getText().isEmpty()|| !password.getText().isEmpty()) {
            if (check(mail.getText(), password.getText())) {
                Alert.display("Notification", "Loged In!");
                System.out.println("successful");
                changeScene(event,"/OpenImportSample.fxml","Open or Import");

            } else {
                Alert.display("Notification", "Mail or password is wrong!");
                System.out.println("wrong pass or mail");
            }
        }else {
            Alert.display("Notification", "Empty password or email textfield!");
            System.out.println("Empty password or email textfield!");
        }


    }
    @FXML
    void jPasswordFieldKeyPressed(KeyEvent event) throws Exception {
        if(event.getCode()== KeyCode.ENTER){
            if (check(mail.getText(),password.getText())){
                Alert.display("Notification", "Loged In!");
                System.out.println("successful");
                Parent createParent = FXMLLoader.load(ChangeScene.class.getResource("/OpenImportSample.fxml"));
                Scene createScene = new Scene(createParent);

                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setTitle("Open or Import");
                window.setScene(createScene);
                window.show();
            }else {
                Alert.display("Notification", "Mail or password is wrong!");
                System.out.println("wrong pass or mail");
            }
        }

    }

    @FXML
    void changeToCreateScene(ActionEvent event) throws IOException {
        changeScene(event,"/createSample.fxml","Create Account");
    }

    boolean check(String Mail, String Password){
        boolean flag = true;
        String username = null;
        String passwordString = null;
        try {


            String qlQuery = "SELECT a.mailAdress FROM AccountTable a WHERE a.mailAdress LIKE('" + mail.getText() + "')";
            Query usernameQuery = entityManager.createQuery(qlQuery);
            username = (String) usernameQuery.getSingleResult();
            System.out.println(username);

            String q2Query = "SELECT a.password FROM AccountTable a WHERE a.password LIKE('" + password.getText() + "') AND mailAdress LIKE('"+mail.getText()+"')";
            Query passwordQuery = entityManager.createQuery(q2Query);
            passwordString = (String) passwordQuery.getSingleResult();
            System.out.println(passwordString);

            if (username.equals(mail.getText()) && passwordString.equals(password.getText())) {
                int id_reminder;
                Integer aux;
                String q3Query = "SELECT a.id FROM AccountTable a WHERE a.password LIKE('" + password.getText() + "') AND mailAdress LIKE('"+mail.getText()+"')";
                Query idQuery = entityManager.createQuery(q3Query);
                id_reminder = (Integer) idQuery.getSingleResult();
                System.out.println(id_reminder);
                setId_reminder(id_reminder);
                display();
                return true; }

        } catch (NoResultException nre) {

        } finally {

        }
        if (username == null || passwordString == null) {
            return false;

        }

        return true;
    }


}
