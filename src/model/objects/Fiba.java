package model.objects;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import model.data_structures.DefaultHashTable;

import java.io.Serializable;
import javafx.stage.FileChooser;

public class Fiba implements Serializable {

    private DefaultHashTable<String, Team> teams;
    private DefaultHashTable<String, Player> players;

    public Fiba(DefaultHashTable<String, Team> teams, DefaultHashTable<String, Player> players) {
        this.teams = teams;
        this.players = players;
    }

    public Fiba() {

    }

    public File fileChooser() {
        FileChooser fc = new FileChooser();
        fc.setTitle("Open File Client");
        File file = fc.showOpenDialog(null);
        return file;
    }

    public boolean importData() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileChooser()));
            String line = br.readLine();
            line = br.readLine();
            while (line != null) {
                //TO DO
            }
            br.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public DefaultHashTable<String, Team> getTeams() {
        return teams;
    }

    public void setTeams(DefaultHashTable<String, Team> teams) {
        this.teams = teams;
    }

    public DefaultHashTable<String, Player> getPlayers() {
        return players;
    }

    public void setPlayers(DefaultHashTable<String, Player> players) {
        this.players = players;
    }
}
