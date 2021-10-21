package ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import model.objects.Fiba;

public class FXController implements Serializable, Initializable {

    @FXML
    private StackPane stackPane;
    @FXML
    private ImageView iLogo = new ImageView();
    @FXML
    private ImageView iPlayer = new ImageView();
    @FXML
    private ImageView iTeam = new ImageView();
    @FXML
    private ImageView iStat = new ImageView();
    private static final long serialVersionUID = 1;
    private final String SAVE_PATH_FILE = "data/persistent/Data.das";
    @SuppressWarnings("FieldMayBeFinal")
    private Fiba fb;
    private FXPlayer xPlayer;
    private FXListPlayers xListPlayer;
    private FXTeam xTeam;

    public FXController(Fiba fb) {
        this.fb = fb;
        xPlayer = new FXPlayer(this.fb, this);
        xListPlayer = new FXListPlayers(this.fb, this);
        xTeam = new FXTeam(this.fb, this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setImages();

    }

    public void setImages() {
        iLogo.setImage(new Image(new File("resources/img/logo/logo_small.png").toURI().toString()));
        iPlayer.setImage(new Image(new File("resources/img/others/players.jpg").toURI().toString()));
        iTeam.setImage(new Image(new File("resources/img/others/teams.png").toURI().toString()));
        iStat.setImage(new Image(new File("resources/img/others/stats.png").toURI().toString()));
    }

    public void setFb(Fiba fb) {
        this.fb = fb;
    }

    public void saveData() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_PATH_FILE));
        oos.writeObject(this.fb);
        oos.close();
    }

    public void newStage(Parent root) {
        Stage newStage = new Stage();
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.setTitle("FIBA Stats");
        newStage.getIcons().add(new Image(new File("resources/img/logo/logo_small_icon_only.png").toURI().toString()));
        newStage.setResizable(false);
        newStage.show();
    }

    public void openListPlayers() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/ListPlayers.fxml"));
        fxmlLoader.setController(xListPlayer);
        Parent root = fxmlLoader.load();
        newStage(root);
    }

    public void openTeam() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/Team.fxml"));
        fxmlLoader.setController(xTeam);
        Parent root = fxmlLoader.load();
        newStage(root);
    }

    public void showAlert(boolean success, String msg, StackPane stackPane) {
        JFXDialogLayout content = new JFXDialogLayout();
        JFXButton button = new JFXButton("Okay");
        JFXDialog dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);
        button.setOnAction((ActionEvent event) -> {
            dialog.close();
        });
        content.setActions(button);
        String header = (success) ? "¡Listo!" : "¡Error!";
        content.setHeading(new Text(header));
        content.setBody(new Text(msg));
        dialog.show();
    }

    @FXML
    public void onBPlayers(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/Players.fxml"));
        fxmlLoader.setController(xPlayer);
        Parent root = fxmlLoader.load();
        newStage(root);
    }

    @FXML
    public void onBStats(ActionEvent event) {
        //¿?
    }

    @FXML
    public void onImport(ActionEvent event) throws IOException {
        boolean imported = fb.importData(fb);
        String msg = (imported) ? "Yei" : "Oh no";
        showAlert(imported, msg, stackPane);
        saveData();
    }


}
