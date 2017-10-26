package pl.mareksowa.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import pl.mareksowa.models.StockExchangeSimulator;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    StockExchangeSimulator simulator;

    //FX declaration
    @FXML
    TextField txtInput;

    @FXML
    TextArea txtOutput;

    @Override //what will be loaded
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(()->txtInput.requestFocus());
        txtOutput.setWrapText(true);
        txtInput.requestFocus();
        registerHitEnterKey();
        simulator = new StockExchangeSimulator();
        simulator.startStockExhangeSimulator();
    }

    public void registerHitEnterKey(){
           txtInput.setOnKeyPressed(event -> {
               if (event.getCode() == KeyCode.ENTER) {
                   if (txtInput.getText().length() > 0) {
                       //getUserInput();
                       txtOutput.setText(getUserInput());
                   }
               }
           });

    }

    public void showToConsole(String output){
        txtOutput.setText(output);
    }

    public String getUserInput(){
        String input = txtInput.getText();
        txtInput.clear();
        return input;
    }

}
