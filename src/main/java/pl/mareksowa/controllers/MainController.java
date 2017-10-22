//package pl.mareksowa.controllers;
//
//import javafx.application.Platform;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.Button;
//import javafx.scene.control.TextArea;
//import javafx.scene.control.TextField;
//import javafx.scene.input.KeyCode;
//
//import java.net.URL;
//import java.util.ResourceBundle;
//
//public class MainController implements Initializable {
//
//    //FX declaration
//    @FXML
//    TextField txtInput;
//
//    @FXML
//    TextArea txtArea;
//
//    @FXML
//    Button btnSubmit;
//
//    //Instances of usable class
//    //StockExchangeSimulator game = new StockExchangeSimulator();
//
//    @Override //what will be loaded
//    public void initialize(URL location, ResourceBundle resources) {
//        Platform.runLater(()->txtInput.requestFocus());
//        txtArea.setWrapText(true);
//        registerSubmitEnterButtonAction();
//        registerSubmitButtonAction();
//        StockExchangeSimulator.test();
//
//    }
//
//    public void submitAction(String input){
//        StockExchangeSimulator.comend = "asdfasdfasf";
//        showToConsole(StockExchangeSimulator.comend);
//        txtInput.clear();
//    }
//
//
//    public void registerSubmitButtonAction(){
//        btnSubmit.setOnMouseClicked(e->submitAction(txtInput.getText()));
//    }
//
//
//    private void registerSubmitEnterButtonAction(){
//        txtInput.setOnKeyPressed(event -> {
//            if (event.getCode() == KeyCode.ENTER){
//                submitAction(txtInput.getText());
//            }
//        });
//    }
//
//    public void showToConsole(String output){
//        txtArea.setText(output);
//    }
//
//}
