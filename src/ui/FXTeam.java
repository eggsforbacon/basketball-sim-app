package ui;

import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import model.objects.Fiba;
import model.objects.Team;

public class FXTeam implements Initializable {

    private Fiba fb;
    private FXController xGUI;
    @FXML
    private StackPane stackPane;
    @FXML
    private ImageView iSave;
    @FXML
    private ImageView iRemove;
    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXTextField txtSearch;
    @FXML
    private JFXTextField txtCountry;
    @FXML
    private TableView<Team> tblTeam;
    @FXML
    private TableColumn<Team, String> tcName;
    @FXML
    private TableColumn<Team, String> tcCountry;

    public FXTeam(Fiba fb, FXController xGUI) {
        this.fb = fb;
        this.xGUI = xGUI;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iSave.setImage(new Image(new File("resources/img/others/save-disk.png").toURI().toString()));
        iRemove.setImage(new Image(new File("resources/img/others/remove-report.png").toURI().toString()));
        setTxtProperties();
        onTableListTeams();
    }

    public void setTxtProperties() {
        txtName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                txtName.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });
        txtCountry.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                txtCountry.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                txtSearch.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });
    }

    @FXML
    public void onSelectTeam(MouseEvent event) {

    }

    @FXML
    public void onSave(ActionEvent event) throws IOException {
        if (!txtName.getText().equals("") && !txtCountry.getText().equals("")) {
            fb.addTeam(new Team(txtName.getText(), txtCountry.getText()));
            onTableListTeams();
            xGUI.saveData();
            xGUI.showAlert(true, "¡Guardado Con Exito!", stackPane);
        } else {
            xGUI.showAlert(false, "¡No Se Pudo Guardar!", stackPane);
        }
    }

    @FXML
    public void onDelete(ActionEvent event) {

    }

    public void onTableListTeams() {
        try {
            List<Team> teams = fb.getTeams();
            ObservableList<Team> tableTeam = FXCollections.observableArrayList(teams);

            tblTeam.setItems(tableTeam);
            tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
            tcCountry.setCellValueFactory(new PropertyValueFactory<>("country"));

            tblTeam.refresh();
        } catch (NullPointerException e) {
        }
    }
}
