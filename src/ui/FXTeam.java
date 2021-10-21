package ui;

import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import model.objects.Fiba;

public class FXTeam implements Initializable {

    private Fiba fb;
    private FXController xGUI;
    @FXML
    private StackPane stackPane;
    @FXML
    private ImageView iSave;
    @FXML
    private ImageView iEdit;
    @FXML
    private ImageView iRemove;
    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXTextField txtSearch;
    @FXML
    private ImageView iSearch;
    @FXML
    private TableView<?> tblTeam;
    @FXML
    private TableColumn<?, ?> tcName;
    @FXML
    private TableColumn<?, ?> tcCountry;
    @FXML
    private JFXTextField txtCountry;

    public FXTeam(Fiba fb, FXController xGUI) {
        this.fb = fb;
        this.xGUI = xGUI;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iSave.setImage(new Image(new File("resources/img/others/save-disk.png").toURI().toString()));
        iEdit.setImage(new Image(new File("resources/img/others/add-report.png").toURI().toString()));
        iRemove.setImage(new Image(new File("resources/img/others/remove-report.png").toURI().toString()));
        iSearch.setImage(new Image(new File("resources/img/others/search.png").toURI().toString()));
    }

    @FXML
    public void onSelectBrand(MouseEvent event) {

    }
}
