package ui;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.objects.Fiba;
import model.objects.Player;
import model.objects.Team;

public class FXPlayer implements Initializable {

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
    private JFXSlider sTurn;
    @FXML
    private JFXSlider sRebound;
    @FXML
    private JFXSlider sUsage;
    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXTextField txtPoints;
    @FXML
    private JFXTextField txtNumber;
    @FXML
    private JFXTextField txtOffensive;
    @FXML
    private JFXTextField txtDefensive;
    @FXML
    private JFXRadioButton rbYes;
    @FXML
    private JFXRadioButton rbNo;
    @FXML
    private ToggleGroup tgActive;
    @FXML
    private JFXComboBox<Team> cbTeam;
    private Player playerSelected;
    
    public FXPlayer(Fiba fb, FXController xGUI) {
        this.fb = fb;
        this.xGUI = xGUI;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setImages();
        setTxtProperties();
        onListTeam();
    }

    public void setTxtProperties() {
        txtName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                txtName.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });
        txtPoints.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtPoints.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        txtNumber.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtNumber.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        txtOffensive.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtOffensive.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        txtDefensive.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtDefensive.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
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

    @FXML
    public void onDelete(ActionEvent event) {
        
    }

    @FXML
    public void onSave(ActionEvent event) throws IOException {
        if ((!txtName.getText().equals("")) && (cbTeam.getValue() != null) && (!txtNumber.getText().equals("")) && (!txtPoints.getText().equals(""))
                && (!txtOffensive.getText().equals("")) && (!txtDefensive.getText().equals("")) && (tgActive.getSelectedToggle() != null)) {

            String name = txtName.getText();
            int number = Integer.parseInt(txtNumber.getText());
            JFXRadioButton selected = (JFXRadioButton) tgActive.getSelectedToggle();
            boolean active = selected.getText().equals("Yes");
            double points = Double.parseDouble(txtNumber.getText());
            double turn = sTurn.getValue();
            double usage = sUsage.getValue();
            double rebound = sRebound.getValue();
            double offensive = Double.parseDouble(txtOffensive.getText());
            double deffensive = Double.parseDouble(txtDefensive.getText());
            Team team = cbTeam.getValue();

            fb.addPlayer(new Player(name, number, "Position", active, points, turn, usage, rebound, deffensive, offensive, team, imagePath));
            xGUI.saveData();
            xGUI.showAlert(true, "¡Player added!", stackPane);
        } else {
            xGUI.showAlert(false, "¡Can't add the player!", stackPane);
        }
    }

    public void onListTeam() {
        List<Team> teams = fb.getTeams();
        ObservableList<Team> listTeams = FXCollections.observableArrayList(teams);
        cbTeam.setItems(listTeams);
    }
    
    public void refreshPlayer(Player p) {
        playerSelected = p;
    }
}
