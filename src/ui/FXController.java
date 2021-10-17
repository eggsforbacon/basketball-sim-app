package ui;

import java.io.File;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.objects.Fiba;

public class FXController implements Serializable, Initializable {

    @FXML
    private Pane pMainMenu;
    @FXML
    private ImageView iLogo = new ImageView();
    private static final long serialVersionUID = 1;
    private final String SAVE_PATH_FILE = "data/RentingCar.cgd";
    @SuppressWarnings("FieldMayBeFinal")
    private Fiba fb;

    public FXController(Fiba fb) {
        this.fb = fb;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setImages();
    }

    public void setImages() {
        iLogo.setImage(new Image(new File("resources/img/logo/logo_small.png").toURI().toString()));
    }

    @FXML
    void onBPlayers(ActionEvent event) {

    }

    @FXML
    void onBStats(ActionEvent event) {

    }

    @FXML
    void onBTeams(ActionEvent event) {

    }

    @FXML
    void onExit(ActionEvent event) {

    }

    @FXML
    void onImport(ActionEvent event) {

    }
}
