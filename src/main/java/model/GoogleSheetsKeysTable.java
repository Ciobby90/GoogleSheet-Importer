package model;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity
@Table
public class GoogleSheetsKeysTable {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "theKey")
    private String theKey;

    @Column(name = "nameOfKeys")
    private String nameOfKey;
    public GoogleSheetsKeysTable(){}


    public void setNameOfKey(String nameOfKey) { this.nameOfKey = nameOfKey; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKey() { return theKey; }

    public void setKey(String key) { this.theKey = key; }

    public String getNameOfKey() { return nameOfKey; }

}
