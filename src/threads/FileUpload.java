package threads;

import model.data_structures.AVL;
import model.data_structures.BST;
import model.data_structures.RBT;
import model.objects.Fiba;
import model.objects.Player;

import java.io.BufferedReader;
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
        fb.setAVLPlayersDefensiveBPM(new AVL<>());
        fb.setAVLPlayersOffensiveBPM(new AVL<>());
        fb.setAVLPlayersReboundPercentage(new AVL<>());
        fb.setBSTPlayersName(new BST<>());
        fb.setAVLPlayersTeamName(new AVL<>());
        fb.setRBTFPlayersUsagePercentage(new RBT<>());
        fb.setRBTPlayersAssistPercentage(new RBT<>());
        fb.setRBTPlayersPoints(new RBT<>());
        fb.setRBTPlayersTurnoverPercentage(new RBT<>());

        int num = 1;
        String line = "";

        while (line != null){
            try {
                line = br.readLine();

                if (line != null){
                    String[] arrayLine = line.split(";");
                    Player player = new Player(arrayLine[0] + " " + arrayLine[1], Integer.parseInt(arrayLine[2]),
                            arrayLine[3], arrayLine[4].equals("Activo"), Double.parseDouble(arrayLine[5]), Double.parseDouble(arrayLine[6]),
                            Double.parseDouble(arrayLine[7]), Double.parseDouble(arrayLine[8]), Double.parseDouble(arrayLine[9]),
                            Double.parseDouble(arrayLine[10]), Double.parseDouble(arrayLine[11]), arrayLine[12], arrayLine[13]);

                    fb.getAVLPlayersDefensiveBPM().insert(Double.parseDouble(arrayLine[10]), player);
                    fb.getAVLPlayersOffensiveBPM().insert(Double.parseDouble(arrayLine[11]), player);
                    fb.getBSTPlayersName().insert(arrayLine[0] + " " + arrayLine[1], player);
                    fb.getAVLPlayersReboundPercentage().insert(Double.parseDouble(arrayLine[9]), player);
                    fb.getAVLPlayersTeamName().insert(arrayLine[12], player);
                    fb.getRBTFPlayersUsagePercentage().add(Double.parseDouble(arrayLine[7]), player);
                    fb.getRBTPlayersAssistPercentage().add(Double.parseDouble(arrayLine[8]), player);
                    fb.getRBTPlayersPoints().add(Double.parseDouble(arrayLine[5]), player);
                    fb.getRBTPlayersTurnoverPercentage().add(Double.parseDouble(arrayLine[6]), player);
                    System.out.println(num);
                    num++;


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
