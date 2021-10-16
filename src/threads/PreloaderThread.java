package threads;

public class PreloaderThread extends Thread {

    public PreloaderThread() {

    }
    
    @Override
    public void run() {

    }

    private void pause() {
        try {
            Thread.sleep(2);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}
