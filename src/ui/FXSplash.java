package ui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.objects.Fiba;
import model.objects.PreloaderBar;
import threads.PreloaderBarThread;

public class FXSplash implements Initializable {

    @FXML
    private Rectangle pBarRCT;

    @FXML
    private ImageView iLogo = new ImageView();

    private Fiba fb;
    private PreloaderBar bar;
    private boolean isLoaded;
    private FXController xMenu;
    private Stage preloaderStage;
    private Scene scene;

    private static final int COUNT_LIMIT = 3000;

    public FXSplash(Fiba fb) {
        this.fb = null;
        bar = new PreloaderBar();
        isLoaded = false;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bar.setActive(true);
        new PreloaderBarThread(this, bar, fb).start();
        iLogo.setImage(new Image(new File("resources/img/logo/logo_small.png").toURI().toString()));
    }

    public void loadBar() throws InterruptedException {
        double newWidth = bar.getBarWidth();
        pBarRCT.setWidth(newWidth);
        double percentage = (newWidth / bar.LOADED_WIDTH) * 100;
    }

    public void postLoaded() {
        ((Stage) pBarRCT.getScene().getWindow()).close();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/Menu.fxml"));
            xMenu = new FXController(fb);
            fxmlLoader.setController(xMenu);
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene((scene));
            stage.getIcons().add(new Image(new File("resources/img/logo/logo_small_icon_only.png").toURI().toString()));
            stage.setResizable(false);
            stage.setTitle("FIBA Stats");
            stage.show();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public FXController getxMenu() {
        return xMenu;
    }

    public void setFb(Fiba fb) {
        this.fb = fb;
    }

    public void setxMenu(FXController xMenu) {
        this.xMenu = xMenu;
    }
}
