package constants;

public class Global {
    public static int id_reminder;

    public static String googleSheet_ID;

    public static String getGoogleSheet_ID() {   return googleSheet_ID;  }

    public static void setGoogleSheet_ID(String googleSheet_ID) {  Global.googleSheet_ID = googleSheet_ID;   }

    public static void displayGoogleSheetCode(){  System.out.println(Global.googleSheet_ID);  }


    public static int getId_reminder() {  return id_reminder;  }

    public static void setId_reminder(int id_reminder) {  Global.id_reminder = id_reminder;  }

    public static void display(){  System.out.println(Global.id_reminder);  }
}
