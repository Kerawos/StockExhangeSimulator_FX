package pl.mareksowa;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppStarter extends Application {

    public AppStarter(){
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("console.fxml"));
        primaryStage.setTitle("Stock Exhange Simulatro 1.0 by Kerawos");
        primaryStage.setScene(new Scene(root, 800, 640));
        primaryStage.setOnCloseRequest(event -> Utils.closeApp());
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
