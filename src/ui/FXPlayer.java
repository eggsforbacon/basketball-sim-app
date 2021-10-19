package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import model.objects.Fiba;

public class FXPlayer {

    private static final long serialVersionUID = 1;
    private Fiba fb;
    private FXController xGui;

    public FXPlayer(Fiba fb, FXController xGui) {
        this.fb = fb;
        this.xGui = xGui;
    }

    @FXML
    void onNextV(ActionEvent event) {
        try {
            changeTextFieldsSelecteds(rc.findVehicletoShowNext(positionCar));
            positionCar++;
        } catch (NullPointerException e) {
            fxGUI.showAlert(false, "No hay vehiculos para mostrar", stackPane);
        }
    }

    @FXML
    void onPrevV(ActionEvent event) {
        try {
            changeTextFieldsSelecteds(rc.findVehicletoShowPrev(Math.abs(amountCar)));
            amountCar--;
        } catch (NullPointerException e) {
            fxGUI.showAlert(false, "No hay vehiculos para mostrar", stackPane);
        }
    }
}
