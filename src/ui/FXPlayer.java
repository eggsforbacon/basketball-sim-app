package ui;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.objects.Fiba;

public class FXPlayer implements Initializable {

    private static final long serialVersionUID = 1;
    private Fiba fb;
    private FXController xGUI;
    @FXML
    private ImageView iAdd;
    @FXML
    private ImageView iSave;
    @FXML
    private ImageView iEdit;
    @FXML
    private ImageView iRemove;
    @FXML
    private ImageView iLeft;
    @FXML
    private ImageView iRight;
    @FXML
    private ImageView iSearch;
    @FXML
    private ImageView iPicture;

    public FXPlayer(Fiba fb, FXController xGUI) {
        this.fb = fb;
        this.xGUI = xGUI;
    }

    /*
    public void changeTextFieldsSelecteds(Car carSelected) {
        txtCodeV.setText(carSelected.getCodeV() + "");
        if (carSelected.getBrand() != null) {
            cbBrandV.setValue(carSelected.getBrand().getNameP());
        } else {
            cbBrandV.setValue(null);
        }
        if (carSelected.getTypeV() != null) {
            cbTypeV.setValue(carSelected.getTypeV().getNameQ());
        } else {
            cbTypeV.setValue(null);
        }
        txtPlateV.setText(carSelected.getPlate());
        txtModelV.setText(carSelected.getModel());
        txtColorV.setText(carSelected.getColor());
        txtYearV.setText(carSelected.getYear() + "");
        txtPriceV.setText(carSelected.getPriceXDay() + "");
        iPhotoV.setImage(stringToImage(carSelected.getPhoto()));
        if (carSelected.isDispV()) {
            rbDispVY.setSelected(true);
            rbDispVN.setSelected(false);
        } else {
            rbDispVN.setSelected(true);
            rbDispVY.setSelected(false);
        }
    }
     */
    @FXML
    public void onNext(ActionEvent event) {
        /*
        try {
            changeTextFieldsSelecteds(fb.findVehicletoShowNext(positionCar));
            positionCar++;
        } catch (NullPointerException e) {
            xGUI.showAlert(false, "No hay vehiculos para mostrar", stackPane);
        }
         */
    }

    @FXML
    public void onPrev(ActionEvent event) {
        /*
        try {
            changeTextFieldsSelecteds(fb.findVehicletoShowPrev(Math.abs(amountCar)));
            amountCar--;
        } catch (NullPointerException e) {
            xGUI.showAlert(false, "No hay vehiculos para mostrar", stackPane);
        }
         */
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setImages();
    }

    public void setImages() {
        iAdd.setImage(new Image(new File("resources/img/others/add-file.png").toURI().toString()));
        iSave.setImage(new Image(new File("resources/img/others/save-disk.png").toURI().toString()));
        iEdit.setImage(new Image(new File("resources/img/others/add-report.png").toURI().toString()));
        iRemove.setImage(new Image(new File("resources/img/others/remove-report.png").toURI().toString()));
        iLeft.setImage(new Image(new File("resources/img/others/back-button.png").toURI().toString()));
        iRight.setImage(new Image(new File("resources/img/others/next-button.png").toURI().toString()));
        iSearch.setImage(new Image(new File("resources/img/others/search.png").toURI().toString()));
        iPicture.setImage(new Image(new File("resources/img/others/picture.png").toURI().toString()));
    }
}
