package pl.mareksowa.views;

public class ShowOutput {
    public void showToConsole(String output){
        txtArea.setText(output);
    }

    public void print30EmptyLines(){
        for (int i = 0; i < 30 ; i++) {
            showToConsole("");
        }
    }
}
