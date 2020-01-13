package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "Sheets")
public class SheetTable {
    @Id
    @Column(name = "Sheet_Id")
    @GeneratedValue(generator="incrementor")
    @GenericGenerator(name = "incrementator" , strategy = "increment")
    private int id;

    @Column(name = "Table_Id")
    private int TableId;

    @Column(name = "Sheet_Name")
    private String SheetName;

    public SheetTable(){}

    public int getTableId() {
        return TableId;
    }

    public void setTableId(int tableId) {
        TableId = tableId;
    }

    public String getSheetName() {
        return SheetName;
    }

    public void setSheetName(String sheetName) {
        SheetName = sheetName;
    }
}
