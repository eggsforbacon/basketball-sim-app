package ui;

import com.sun.javafx.application.LauncherImpl;

import java.io.File;
import java.io.IOException;
import javafx.application.Preloader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Fiba;

public class FXMain extends Application {

    public static boolean loaded = false;
    private Fiba fb;
    private FXController controller;

    private static final int COUNT_LIMIT = 30000;

    public FXMain() throws IOException {
        fb = new Fiba();
        controller = new FXController(fb);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/login.fxml"));
        fxmlLoader.setController(controller);
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        primaryStage.getIcons().add(new Image(new File("resources/image/baancc.png").toURI().toString()));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Baancc");
        primaryStage.show();
    }

    @Override
    public void init() throws Exception {
        for (int i = 0; i < COUNT_LIMIT; i++) {
            double progress = (100 * i) / COUNT_LIMIT;
            LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(progress));
        }
    }

    public static void main(String[] args) {
        LauncherImpl.launchApplication(FXMain.class, FXSplash.class, args);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }
}
