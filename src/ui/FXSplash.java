package ui;

import java.io.File;
import java.io.IOException;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.objects.Fiba;

public class FXSplash extends Preloader {

    @SuppressWarnings("FieldMayBeFinal")
    private Fiba fb;
    @SuppressWarnings("FieldMayBeFinal")
    private FXController xMenu;
    private Stage preloaderStage;
    private Scene scene;

    public FXSplash() throws IOException {
        fb = new Fiba();
        xMenu = new FXController(fb);
    }

    @Override
    public void init() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/Welcome.fxml"));
        fxmlLoader.setController(xMenu);
        Parent root = fxmlLoader.load();
        scene = new Scene(root);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.preloaderStage = primaryStage;
        scene.setFill(Color.TRANSPARENT);
        preloaderStage.initStyle(StageStyle.TRANSPARENT);
        preloaderStage.setScene(scene);
        preloaderStage.setResizable(false);
        preloaderStage.getIcons().add(new Image(new File("resources/img/logo/logo_small_icon_only.png").toURI().toString()));
        preloaderStage.show();
    }

    @Override
    public void handleApplicationNotification(Preloader.PreloaderNotification info) {
        if (info instanceof Preloader.ProgressNotification) {
        }
    }

    @Override
    public void handleStateChangeNotification(Preloader.StateChangeNotification info) {
        Preloader.StateChangeNotification.Type type = info.getType();
        switch (type) {
            case BEFORE_INIT:
                break;
            case BEFORE_START:
                preloaderStage.hide();
                break;
            case BEFORE_LOAD:
                break;
        }
    }
}
