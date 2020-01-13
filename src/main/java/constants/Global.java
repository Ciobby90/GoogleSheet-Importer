package constants;

public class Global {
    public static int id_reminder;

    public static String googleSheet_ID;

    public static int TableId_reminder;

    public static String SheetName_reminder;

    public static String getSheetName_reminder() {   return SheetName_reminder;    }

    public static void setSheetName_reminder(String sheetName_reminder) {    Global.SheetName_reminder = sheetName_reminder;    }

    public static void displaySheetName(){  System.out.println(Global.SheetName_reminder);  }

    public static int getTableId_reminder() {  return TableId_reminder;   }

    public static void setTableId_reminder(int tableId_reminder) {   Global.TableId_reminder = tableId_reminder;}

    public static String getGoogleSheet_ID() {   return googleSheet_ID;  }

    public static void setGoogleSheet_ID(String googleSheet_ID) {  Global.googleSheet_ID = googleSheet_ID;   }

    public static void displayGoogleSheetCode(){  System.out.println(Global.googleSheet_ID);  }


    public static int getId_reminder() {  return id_reminder;  }

    public static void setId_reminder(int id_reminder) {  Global.id_reminder = id_reminder;  }

    public static void display(){  System.out.println(Global.id_reminder);  }
}
