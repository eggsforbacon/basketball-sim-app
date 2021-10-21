package threads;

import model.objects.Fiba;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileUpload extends Thread{

    private BufferedReader br;
    private Fiba fb;

    public FileUpload(BufferedReader br, Fiba fb) {
       this.br = br;
       this.fb = fb;
    }

    @Override
    public void run() {
        String line = "";

        while (line != null){
            try {
                line = br.readLine();
                String[] arrayLine = line.split(";");

//                fb.getPlayers().insert();

            } catch (IOException e) {
                e.printStackTrace();
            }


        }


    }


}
