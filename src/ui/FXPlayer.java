package ui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.objects.Fiba;

public class FXPlayer implements Initializable {

    private static final long serialVersionUID = 1;
    private Fiba fb;
    private FXController xGUI;
    private String imagePath;
    @FXML
    private StackPane stackPane;
    @FXML
    private Pane pane;
    @FXML
    private ImageView iPhoto;
    @FXML
    private ImageView iSave;
    @FXML
    private ImageView iRemove;
    @FXML
    private ImageView iSearch;
    @FXML
    private ImageView iPicture;
    @FXML
    private ImageView iTeam;
    @FXML
    private Slider BUsage;
    @FXML
    private Slider BTurnOver;
    @FXML
    private Slider BRebound;
    @FXML
    private Label txtTurnOver;
    @FXML
    private Label txtRebound;
    @FXML
    private Label txtUsage;

    public FXPlayer(Fiba fb, FXController xGUI) {
        this.fb = fb;
        this.xGUI = xGUI;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setImages();
    }

    public void setImages() {
        iSave.setImage(new Image(new File("resources/img/others/save-disk.png").toURI().toString()));
        iRemove.setImage(new Image(new File("resources/img/others/remove-report.png").toURI().toString()));
        iSearch.setImage(new Image(new File("resources/img/others/search.png").toURI().toString()));
        iPicture.setImage(new Image(new File("resources/img/others/picture.png").toURI().toString()));
        iTeam.setImage(new Image(new File("resources/img/others/teams.png").toURI().toString()));
    }

    @FXML
    public void onSelectImage(ActionEvent event) {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select a picture");
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));

            Stage stage = (Stage) pane.getScene().getWindow();
            File iconImage = fileChooser.showOpenDialog(stage);

            if (iconImage != null) {
                imagePath = iconImage.getAbsolutePath();
                iPhoto.setImage(stringToImage(imagePath));
            } else {
                xGUI.showAlert(false, "Por favor seleeciona una imagen", stackPane);
            }
        } catch (NullPointerException e) {
            xGUI.showAlert(false, "Imagen no encontrada, por favor selecciona una imagen", stackPane);
        }
    }

    public Image stringToImage(String image) {
        try {
            File f = new File(image);
            Image imP = new Image(f.toURI().toString());
            return imP;
        } catch (NullPointerException e) {
        }
        return null;
    }

    @FXML
    public void onSearchPlayer() throws IOException {
        xGUI.openListPlayers();
    }

    @FXML
    public void onTeamDisplay() throws IOException {
        xGUI.openTeam();
    }
}
