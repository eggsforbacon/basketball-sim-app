package ui;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import model.objects.Fiba;
import model.objects.Player;
import model.objects.Team;

public class FXListPlayers implements Initializable {

    private Fiba fb;
    private FXController xGUI;
    @FXML
    private StackPane stackPane;
    @FXML
    private JFXComboBox cbSearch;
    @FXML
    private JFXTextField txtSearch;
    @FXML
    private TableView<Player> tblPlayer;
    @FXML
    private TableColumn<Player, String> tcName;
    @FXML
    private TableColumn<Team, String> tcTeam;
    @FXML
    private TableColumn<Player, Integer> tcNumber;
    @FXML
    private TableColumn<Player, Boolean> tcActive;
    @FXML
    private TableColumn<Player, Double> tcPoints;
    
    private Player playerSelected;
    
    public FXListPlayers(Fiba fb, FXController xGUI) {
        this.fb = fb;
        this.xGUI = xGUI;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCBSearch();
        txtSearch.setDisable(true);
    }

    public void setCBSearch() {
        List<String> types = new ArrayList<>();
        types.add("Name");
        types.add("Team");
        types.add("Points");
        types.add("Turn Over");
        types.add("Usage");
        types.add("Rebound");
        types.add("Deffensive");
        types.add("Offensive");
        ObservableList<String> list = FXCollections.observableArrayList(types);
        cbSearch.setItems(list);
    }

    @FXML
    public void select(ActionEvent event) {
        if (cbSearch.getValue().equals("Name") || cbSearch.getValue().equals("Team") || cbSearch.getValue().equals("Points")
                || cbSearch.getValue().equals("Turn Over") || cbSearch.getValue().equals("Usage") || cbSearch.getValue().equals("Rebound")
                || cbSearch.getValue().equals("Deffensive") || cbSearch.getValue().equals("Offensive")) {
            txtSearch.setDisable(false);
        } else {
            txtSearch.setDisable(true);
        }
    }

    @FXML
    public void onSelectPlayer(MouseEvent event) {
        if (event.getClickCount() == 2) {
            playerSelected = tblPlayer.getSelectionModel().getSelectedItem();
            if (playerSelected != null) {
                xGUI.refreshPlayer(playerSelected);
                xGUI.showAlert(true, "Se ha seleccionado correctamente el jugador", stackPane);
            }
        }
    }

    @FXML
    public void onSearch(KeyEvent event) {
        ArrayList<Player> s;
        if (cbSearch.getValue().equals("Name")) {
            s = fb.getBSTPlayersName().searchApproximate(txtSearch.getText());
        } else if (cbSearch.getValue().equals("Team")) {
            s = fb.getAVLPlayersTeamName().searchApproximate(txtSearch.getText());
        } else if (cbSearch.getValue().equals("Points")) {
            s = fb.getRBTPlayersPoints().searchApproximate(txtSearch.getText());
        } else if (cbSearch.getValue().equals("Turn Over")) {
            s = fb.getRBTPlayersTurnoverPercentage().searchApproximate(txtSearch.getText());
        } else if (cbSearch.getValue().equals("Usage")) {
            s = fb.getRBTFPlayersUsagePercentage().searchApproximate(txtSearch.getText());
        } else if (cbSearch.getValue().equals("Rebound")) {
            s = fb.getAVLPlayersReboundPercentage().searchApproximate(txtSearch.getText());
        } else if (cbSearch.getValue().equals("Deffensive")) {
            s = fb.getAVLPlayersDefensiveBPM().searchApproximate(txtSearch.getText());
        } else {
            s = fb.getAVLPlayersOffensiveBPM().searchApproximate(txtSearch.getText());
        }
        onTableSearch(s);
    }

    public void onTableSearch(ArrayList<Player> arr) {
        ObservableList<Player> tableTeam = FXCollections.observableArrayList(arr);

        tblPlayer.setItems(tableTeam);
        tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcTeam.setCellValueFactory(new PropertyValueFactory<>("team"));
        tcNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        tcActive.setCellValueFactory(new PropertyValueFactory<>("active"));
        tcPoints.setCellValueFactory(new PropertyValueFactory<>("points"));

        tblPlayer.refresh();
    }
}
