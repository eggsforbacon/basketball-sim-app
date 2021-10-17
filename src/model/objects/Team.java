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
    }
}
