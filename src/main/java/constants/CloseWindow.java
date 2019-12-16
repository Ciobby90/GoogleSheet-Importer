package constants;

import additionalScenes.ConfirmBox;
import javafx.stage.Stage;

public class CloseWindow {
    public static void closeProgram(Stage window){
        boolean result= ConfirmBox.display("Title","sure u wanna exit?");
        if(result)
            window.close();
    }
}
