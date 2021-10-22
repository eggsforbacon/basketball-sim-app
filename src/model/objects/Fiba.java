package model.objects;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import model.data_structures.DefaultHashTable;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.stage.FileChooser;
import threads.FileUpload;

public class Fiba implements Serializable {

    private ArrayList<Team> teams;
    private DefaultHashTable<String, Player> players;

    public Fiba(ArrayList<Team> teams, DefaultHashTable<String, Player> players) {
        this.teams = teams;
        this.players = players;
    }

    public Fiba() {

    }

    public File fileChooser() {
        FileChooser fc = new FileChooser();
        fc.setTitle("Open File Client");
        return fc.showOpenDialog(null);
    }

    public boolean importData(Fiba fb) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileChooser()));
            new FileUpload(br, fb).start();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }

    public DefaultHashTable<String, Player> getPlayers() {
        return players;
    }

    public void setPlayers(DefaultHashTable<String, Player> players) {
        this.players = players;
    }

    public void addPlayer(Player newPlayer) throws Exception {
        players.insert(newPlayer.getName(), newPlayer);

    }

    public void addTeam(Team newTeam) throws Exception {
        teams.add(newTeam);

    }


}
