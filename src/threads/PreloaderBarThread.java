package threads;

import javafx.application.Platform;
import model.objects.Fiba;
import model.objects.PreloaderBar;
import ui.FXController;
import ui.FXSplash;

public class PreloaderBarThread extends Thread {

    PreloaderBar bar;
    FXSplash preloader;
    Fiba fb;

    public PreloaderBarThread(FXSplash preloader, PreloaderBar bar, Fiba fb) {
        this.preloader = preloader;
        this.bar = bar;
        this.fb = fb;

    }

    @Override
    public void run() {
        new Load(preloader).start();
        pause(1000);
        int LOADING_TIME_INTERVAL = 10;
        while (bar.isActive()) {
            if (!currentThread().isInterrupted()) {
                bar.doProgress();
                Platform.runLater(new Thread(() -> {
                    try {
                        preloader.loadBar();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }));
                pause(LOADING_TIME_INTERVAL);
                double percentage = (bar.getBarWidth() / bar.LOADED_WIDTH) * 100;
                if (percentage >= 75.00 && percentage < 85.00) {
                    pause(100);
                }

                if (percentage >= 95.00 && percentage < 95.10 /*&& !PreloaderThread.isLoaded()*/) {
                    //Wait Here (?
                }
            }

        }
        Platform.runLater(new Thread(() -> preloader.postLoaded()));

        Platform.runLater(new Thread(() -> preloader.getxMenu().setFb(fb)));
    }

    private void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}
