package model.objects;

import java.io.Serializable;

public class Team implements Serializable {

    private String name;
    private int players;
    private double minutesPlayed;
    private int goalsAttempted;
    private int goalsScored;
    private int freeThrowsAttempted;
    private double turnovers;
    private double rebounds;

    private double totalShootingAttempts; //GA + BIAS * FTA (TSA)

    public Team(String name) {
        this.name = name;
    }

    public Team(String name, int players, double minutesPlayed, int goalsAttempted, int goalsScored,
                int freeThrowsAttempted, double turnovers, double rebounds) {
        this.name = name;
        this.players = players;
        this.minutesPlayed = minutesPlayed;
        this.goalsAttempted = goalsAttempted;
        this.goalsScored = goalsScored;
        this.freeThrowsAttempted = freeThrowsAttempted;
        this.turnovers = turnovers;
        this.rebounds = rebounds;
        totalShootingAttempts = goalsAttempted + 0.44 * freeThrowsAttempted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlayers() {
        return players;
    }

    public void setPlayers(int players) {
        this.players = players;
    }

    public double getMinutesPlayed() {
        return minutesPlayed;
    }

    public void setMinutesPlayed(double minutesPlayed) {
        this.minutesPlayed = minutesPlayed;
    }

    public int getGoalsAttempted() {
        return goalsAttempted;
    }

    public void setGoalsAttempted(int goalsAttempted) {
        this.goalsAttempted = goalsAttempted;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

    public int getFreeThrowsAttempted() {
        return freeThrowsAttempted;
    }

    public void setFreeThrowsAttempted(int freeThrowsAttempted) {
        this.freeThrowsAttempted = freeThrowsAttempted;
    }

    public double getTurnovers() {
        return turnovers;
    }

    public void setTurnovers(double turnovers) {
        this.turnovers = turnovers;
    }

    public double getRebounds() {
        return rebounds;
    }

    public void setRebounds(double rebounds) {
        this.rebounds = rebounds;
    }

    public double getTotalShootingAttempts() {
        return totalShootingAttempts;
    }

    public void setTotalShootingAttempts(double totalShootingAttempts) {
        this.totalShootingAttempts = totalShootingAttempts;
    }
}
