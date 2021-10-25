package threads;

import javafx.application.Platform;
import model.data_structures.DefaultHashTable;
import model.objects.Fiba;
import model.objects.Player;

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

                if (line != null){
                    String[] arrayLine = line.split(";");
                    fb.getPlayers().insert(arrayLine[0], new Player(arrayLine[0] + " " + arrayLine[1], Integer.parseInt(arrayLine[2]),
                            arrayLine[3], arrayLine[4].equals("Activo"), Double.parseDouble(arrayLine[5]), Double.parseDouble(arrayLine[6]),
                            Double.parseDouble(arrayLine[7]), Double.parseDouble(arrayLine[8]), Double.parseDouble(arrayLine[9]),
                            Double.parseDouble(arrayLine[10]), Double.parseDouble(arrayLine[11]), arrayLine[12], arrayLine[13]));
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}