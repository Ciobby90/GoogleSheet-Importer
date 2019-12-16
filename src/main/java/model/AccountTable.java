package model;

import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;

@Entity
@Table
public class AccountTable {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator="incrementor")
    @GenericGenerator(name = "incrementator" , strategy = "increment")
    private int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Column(name="mail")
    private String mailAdress;
    public String getMailAdress() {
        return mailAdress;
    }

    @Column(name = "password")
    private String password;



    public String getPassword() {
        return password;
    }

    public void setMailAdress(String mailAdress) {
        this.mailAdress = mailAdress;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}
