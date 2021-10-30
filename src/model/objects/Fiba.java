package model.objects;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Serializable;
import java.util.ArrayList;
import javafx.stage.FileChooser;
import model.data_structures.AVL;
import model.data_structures.BST;
import model.data_structures.RBT;
import threads.FileUpload;

public class Fiba implements Serializable {

    private ArrayList<Team> teams;
    private BST<String, Player> BSTPlayersName;
    private RBT<Double, Player> RBTPlayersPoints;
    private RBT<Double, Player> RBTPlayersTurnoverPercentage;
    private RBT<Double, Player> RBTFPlayersUsagePercentage;
    private RBT<Double, Player> RBTPlayersAssistPercentage;
    private AVL<Double, Player> AVLPlayersReboundPercentage;
    private AVL<Double, Player> AVLPlayersDefensiveBPM;
    private AVL<Double, Player> AVLPlayersOffensiveBPM;
    private AVL<String, Player> AVLPlayersTeamName;

    public Fiba() {
        BSTPlayersName = new BST<>();
        RBTPlayersPoints = new RBT<>();
        RBTPlayersTurnoverPercentage = new RBT<>();
        RBTFPlayersUsagePercentage = new RBT<>();
        RBTPlayersAssistPercentage = new RBT<>();
        AVLPlayersReboundPercentage = new AVL<>();
        AVLPlayersDefensiveBPM = new AVL<>();
        AVLPlayersOffensiveBPM = new AVL<>();
        AVLPlayersTeamName = new AVL<>();

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

    public BST<String, Player> getBSTPlayersName() {
        return BSTPlayersName;
    }

    public void setBSTPlayersName(BST<String, Player> BSTPlayersName) {
        this.BSTPlayersName = BSTPlayersName;
    }

    public RBT<Double, Player> getRBTPlayersPoints() {
        return RBTPlayersPoints;
    }

    public void setRBTPlayersPoints(RBT<Double, Player> RBTPlayersPoints) {
        this.RBTPlayersPoints = RBTPlayersPoints;
    }

    public RBT<Double, Player> getRBTPlayersTurnoverPercentage() {
        return RBTPlayersTurnoverPercentage;
    }

    public void setRBTPlayersTurnoverPercentage(RBT<Double, Player> RBTPlayersTurnoverPercentage) {
        this.RBTPlayersTurnoverPercentage = RBTPlayersTurnoverPercentage;
    }

    public RBT<Double, Player> getRBTFPlayersUsagePercentage() {
        return RBTFPlayersUsagePercentage;
    }

    public void setRBTFPlayersUsagePercentage(RBT<Double, Player> RBTFPlayersUsagePercentage) {
        this.RBTFPlayersUsagePercentage = RBTFPlayersUsagePercentage;
    }

    public RBT<Double, Player> getRBTPlayersAssistPercentage() {
        return RBTPlayersAssistPercentage;
    }

    public void setRBTPlayersAssistPercentage(RBT<Double, Player> RBTPlayersAssistPercentage) {
        this.RBTPlayersAssistPercentage = RBTPlayersAssistPercentage;
    }

    public AVL<Double, Player> getAVLPlayersReboundPercentage() {
        return AVLPlayersReboundPercentage;
    }

    public void setAVLPlayersReboundPercentage(AVL<Double, Player> AVLPlayersReboundPercentage) {
        this.AVLPlayersReboundPercentage = AVLPlayersReboundPercentage;
    }

    public AVL<Double, Player> getAVLPlayersDefensiveBPM() {
        return AVLPlayersDefensiveBPM;
    }

    public void setAVLPlayersDefensiveBPM(AVL<Double, Player> AVLPlayersDefensiveBPM) {
        this.AVLPlayersDefensiveBPM = AVLPlayersDefensiveBPM;
    }

    public AVL<Double, Player> getAVLPlayersOffensiveBPM() {
        return AVLPlayersOffensiveBPM;
    }

    public void setAVLPlayersOffensiveBPM(AVL<Double, Player> AVLPlayersOffensiveBPM) {
        this.AVLPlayersOffensiveBPM = AVLPlayersOffensiveBPM;
    }

    public AVL<String, Player> getAVLPlayersTeamName() {
        return AVLPlayersTeamName;
    }

    public void setAVLPlayersTeamName(AVL<String, Player> AVLPlayersTeamName) {
        this.AVLPlayersTeamName = AVLPlayersTeamName;
    }

    public void addPlayer(Player player) {
        getAVLPlayersDefensiveBPM().insert(player.getDefensiveBPM(), player);
        getAVLPlayersOffensiveBPM().insert(player.getOffensiveBPM(), player);
        getBSTPlayersName().insert(player.getName(), player);
        getAVLPlayersReboundPercentage().insert(player.getReboundPercentage(), player);
        getAVLPlayersTeamName().insert(player.getTeam().getName(), player);
        getRBTFPlayersUsagePercentage().insert(player.getUsagePercentage(), player);
        getRBTPlayersAssistPercentage().insert(player.getAssistPercentage(), player);
        getRBTPlayersPoints().insert(player.getPoints(), player);
        getRBTPlayersTurnoverPercentage().insert(player.getTurnoverPercentage(), player);

    }

    public void addTeam(Team team) {
        teams.add(team);

    }
}
