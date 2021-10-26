package model.objects;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Serializable;
import java.util.ArrayList;
import javafx.stage.FileChooser;
import model.data_structures.BST;
import model.data_structures.RedBlackTree;
import threads.FileUpload;

public class Fiba implements Serializable {

    private ArrayList<Team> teams;
    private RedBlackTree<String, Player> redBlackTreePlayers;
    private BST<String, Player> BSTPlayers;

    public Fiba() {
        this.teams = new ArrayList<>();
        this.redBlackTreePlayers = new RedBlackTree<>();
        this.BSTPlayers = new BST<>();

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

    public RedBlackTree<String, Player> getRedBlackTreePlayers() {
        return redBlackTreePlayers;
    }

    public void setRedBlackTreePlayers(RedBlackTree<String, Player> redBlackTreePlayers) {
        this.redBlackTreePlayers = redBlackTreePlayers;
    }

    public BST<String, Player> getBSTPlayers() {
        return BSTPlayers;
    }

    public void setBSTPlayers(BST<String, Player> BSTPlayers) {
        this.BSTPlayers = BSTPlayers;
    }

    public void addPlayer(Player newPlayer) throws Exception {
        redBlackTreePlayers.add(newPlayer.getName(), newPlayer);

    }

    public void addTeam(Team newTeam) throws Exception {
        teams.add(newTeam);

    }


}
