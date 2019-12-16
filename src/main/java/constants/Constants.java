package constants;

import additionalScenes.ConfirmBox;
import javafx.stage.Stage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Constants {
    public static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("AccountQuerry");
    public static EntityManager entityManager=entityManagerFactory.createEntityManager();


}
