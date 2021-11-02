package ui;

import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.input.KeyEvent;
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

    private Team teamSelected;

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
        clearTxt();
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
        if (event.getClickCount() == 2) {
            teamSelected = tblTeam.getSelectionModel().getSelectedItem();
            if (teamSelected != null) {
                txtName.setText(teamSelected.getName());
                txtCountry.setText(teamSelected.getCountry());
                xGUI.showAlert(true, "Se ha seleccionado correctamente el equipo", stackPane);
            }
        }
    }

    @FXML
    public void onSave(ActionEvent event) throws IOException {
        if (!txtName.getText().equals("") && !txtCountry.getText().equals("")) {
            fb.addTeam(new Team(txtName.getText(), txtCountry.getText()));
            onTableListTeams();
            xGUI.refreshCbTeam();
            xGUI.saveData();
            clearTxt();
            xGUI.showAlert(true, "¡Guardado Con Exito!", stackPane);
        } else {
            xGUI.showAlert(false, "¡No Se Pudo Guardar!", stackPane);
        }
    }

    @FXML
    public void onDelete(ActionEvent event) throws IOException {
        if (teamSelected.getName().equals(txtName.getText()) && teamSelected.getCountry().equals(txtCountry.getText())) {
            fb.getTeams().remove(teamSelected);
            onTableListTeams();
            xGUI.refreshCbTeam();
            xGUI.saveData();
            clearTxt();
            xGUI.showAlert(true, "¡Se elimino con exito!", stackPane);
        } else {
            xGUI.showAlert(false, "¡No seleccionaste ningun equipo!", stackPane);
        }
    }

    public void onTableListTeams() {
        List<Team> teams = fb.getTeams();
        ObservableList<Team> tableTeam = FXCollections.observableArrayList(teams);

        tblTeam.setItems(tableTeam);
        tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcCountry.setCellValueFactory(new PropertyValueFactory<>("country"));

        tblTeam.refresh();
    }

    public void onTableListTeams(ArrayList<Team> teams) {
        ObservableList<Team> tableTeam = FXCollections.observableArrayList(teams);

        tblTeam.setItems(tableTeam);
        tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcCountry.setCellValueFactory(new PropertyValueFactory<>("country"));

        tblTeam.refresh();
    }

    public void clearTxt() {
        txtName.setText("");
        txtCountry.setText("");
    }

    @FXML
    public void onSearch(KeyEvent event) {
        if (txtSearch.getText().equals("")) {
            onTableListTeams();
        } else {
            ArrayList<Team> teams = fb.getTeams();
            ArrayList<Team> query = new ArrayList<>();
            for (int i = 0; i < teams.size(); i++) {
                if (teams.get(i).getName().contains(txtSearch.getText())) {
                    query.add(teams.get(i));
                }
            }
            onTableListTeams(query);
        }
    }
}
