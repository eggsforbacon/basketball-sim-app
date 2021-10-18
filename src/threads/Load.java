package threads;

import model.objects.Fiba;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Load extends Thread{

    private Fiba fb;
    private boolean loader;
    private final String SAVE_PATH_FILE = "data/Data.das";

    public Load(Fiba fb) {
        this.fb = fb;
    }

    @Override
    public void run() {
        //hilo run
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SAVE_PATH_FILE));
            setFb((Fiba) ois.readObject());
            ois.close();
        } catch (Exception e) {
        }
    }

    public void setFb(Fiba fb) {
        this.fb = fb;
    }
}
