package ui;

import model.data_structures.DefaultHashTable;
import model.objects.Fiba;
import model.objects.Player;
import model.objects.Team;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Main {

    private static final String SAVE_PATH_FILE = "data/persistent/Data.das";
    private static ArrayList<Team> teams;
    private static DefaultHashTable<String, Player> players;

    public static void main(String[] args) throws Exception {
        players = new DefaultHashTable<>(2);
        teams = new ArrayList<>(2);

        players.insert("Lebron", new Player("Lebron"));
        teams.add(new Team("Pene"));

        Fiba fs = new Fiba(teams, players);

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_PATH_FILE));
        oos.writeObject(fs);
        oos.close();
    }
}
