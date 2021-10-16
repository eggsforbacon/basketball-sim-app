package ui;

import java.io.File;
import java.io.IOException;
import javafx.application.Preloader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Baancc;

public class FXSplash extends Preloader {

    //---------------------------- Attributes of FxSplash class ----------------------------\\
    @SuppressWarnings("FieldMayBeFinal")
    private Baancc bc;
    @SuppressWarnings("FieldMayBeFinal")
    private FXController controller;
    private Stage preloaderStage;
    private Scene scene;

    //-------------------------- Constructor class --------------------------\\
    /**
     * FXSplash class constructor, initialize all relations.
     *
     * @throws IOException
     */
    public FXSplash() throws IOException {
        bc = new Baancc();
        controller = new FXController(bc);
    }

    /**
     * Configure the FXGUI
     *
     * @throws Exception
     */
    @Override
    public void init() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/welcome.fxml"));
        fxmlLoader.setController(controller);
        Parent root = fxmlLoader.load();
        scene = new Scene(root);
    }

    /**
     * Start to show the GUI
     *
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.preloaderStage = primaryStage;
        scene.setFill(Color.TRANSPARENT);
        preloaderStage.initStyle(StageStyle.TRANSPARENT);
        preloaderStage.setScene(scene);
        preloaderStage.setResizable(false);
        preloaderStage.getIcons().add(new Image(new File("resources/image/baancc.png").toURI().toString()));
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
