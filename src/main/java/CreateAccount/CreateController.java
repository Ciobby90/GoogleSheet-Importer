package CreateAccount;

import additionalScenes.Alert;
import additionalScenes.mailVerification;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.AccountTable;


import static constants.ChangeScene.changeScene;
import static constants.Constants.entityManager;
import static constants.Constants.entityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


public class CreateController {

    @FXML
    private TextField mail;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField repeatPassword;

    @FXML
    void backAction(ActionEvent event) throws IOException {
        changeScene(event,"/loginSample.fxml","Log In");
    }

    @FXML
    void createAccount(ActionEvent event) throws Exception {

        boolean flag=true;
        if (!checkMailValidity(mail.getText())){
            System.out.println("mail false");
            flag=false;
        }
        if (!password.getText().equals(repeatPassword.getText())){
            flag=false;
        }
        if (flag){
          try {
              AccountTable accountHiber= new AccountTable();

             accountHiber.setMailAdress(mail.getText());
             accountHiber.setPassword(password.getText());

             String qlQuery = "SELECT a.mailAdress FROM AccountTable a WHERE a.mailAdress LIKE('" + mail.getText() + "')";
             Query usernameQuery = entityManager.createQuery(qlQuery);
             List username = usernameQuery.getResultList();
             if(username.size()==0){
                 System.out.println("ok");
                 System.out.println(username);

                 entityManager.getTransaction().begin();

                 entityManager.persist(accountHiber);

                 entityManager.getTransaction().commit();

                 System.out.println("Account created");
                 Alert.display("Notification", "Account Created");
                 changeScene(event,"/loginSample.fxml","Log In");
             }
             else {
                 System.out.println("Gmail already taken");
                 Alert.display("Notification", "Gmail already used");
             }



          }catch (NullPointerException e){
              e.printStackTrace();
          }finally {

          }

        }
        else {
            System.out.println("fail");
            System.out.println(password.getText());
            System.out.println(repeatPassword.getText());
            System.out.println(flag);
            Alert.display("Notification", "Something went wrong");
        }

    }

    boolean checkMailValidity(String mail) throws Exception {
        mailVerification mailCheck = new mailVerification(mail);
        if (mailCheck.checkMail()) {
            System.out.println("ok");
            return true;
        } else {
            System.out.println("not ok");
            return false;
        }
    }

}



