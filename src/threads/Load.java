package threads;

import javafx.application.Platform;
import model.objects.Fiba;
import ui.FXController;
import ui.FXSplash;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Load extends Thread implements Serializable {

    private boolean loader;
    private final String SAVE_PATH_FILE = "data/persistent/Data.das";
    private FXSplash fxSplash;
    private ObjectInputStream ois;
    private static final long serialVersionUID = 1;

    public Load(FXSplash splash) {
        this.fxSplash = splash;
    }

    @Override
    public void run() {
        try {

            Fiba fb  = read();
            Platform.runLater(new Thread(() -> fxSplash.setFb(fb)));
            ois.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Fiba read() throws IOException, ClassNotFoundException {
        ois = new ObjectInputStream(new FileInputStream(SAVE_PATH_FILE));
        return  (Fiba) ois.readObject();
    }

}
