package pl.mareksowa.views;

import pl.mareksowa.controllers.MainController;

public class ShowOutput {

    MainController controller;

    public ShowOutput() {
        controller = new MainController();
    }

    public void showToConsole(String output){
        controller.showToConsole(output);
    }

    public void print30EmptyLines(){
        for (int i = 0; i < 30 ; i++) {
            showToConsole("");
        }
    }
}
