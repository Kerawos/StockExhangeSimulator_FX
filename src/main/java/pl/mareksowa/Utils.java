package pl.mareksowa;

import javafx.application.Platform;

public class Utils {

    public static void closeApp(){
        Platform.exit();
        System.exit(0);
    }
}
