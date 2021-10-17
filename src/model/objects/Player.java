package model.objects;

import java.io.Serializable;

public class Player implements Serializable {

    //Basic Info

    private String name;
    private int age;
    private Team team; //(Tm)
    private int number;
    private String position;
    private String classification; //Depends on DBPM and OBPM
    private boolean active;

    //Raw Data

    private int points; //The sum of POINTS (PTS) <- MUST BE GREATER THAN GS
    private int goalsAttempted; //The amount of times player THREW the ball (GA)
    private int goalsScored; //The amount of THROWS which resulted in POINTS (GS) <- MUST BE LOWER THAN GA
    private int freeThrowsAttempted; //(FTA) <- MUST BE AT MOST HALF OF GA, AND ALWAYS LOWER THAN GS
    private double turnovers; //(TOV)
    private double minutesPLayed; //(MP)
    private int assists; //(AST) <- LOWER THAN GS
    private double offensiveRebounds; //(ORB)
    private double defensiveRebounds; //(DRB)
    private double defensiveBPM; //(DBPM) <- WITHIN 10 AND -10
    private double offensiveBPM; //(OBPM) <- WITHIN 10 AND -10

    //Sample raw data string:
    // NAME; AGE; NUM; POS; ACTIVE; 2PTS; 3PTS; GA; GS; FTA; TOV; MP; AST; ORB; DRB; DBPM; OBPM; TM NAME; TM PLAYERS NUM; TM MP; TM GA; TM GS; TM FTA; TM TOV; TM TRB; Opponent Rebounds

    private double totalRebounds; // = offensiveRebounds + defensiveRebounds (TRB)
    private final double BIAS = 0.44;

    //Calculated Stats

    private double turnoverPercentage; //FORMULA: 100 * TOV / (GA + BIAS * FTA + TOV)
    private double usagePercentage; //FORMULA: 100 * ((GA + BIAS * FTA + TOV) * (Tm MP / 5)) / (MP * (Tm GA + BIAS * Tm FTA + Tm TOV))
    private double assistPercentage; //FORMULA: 100 * AST / (((MP / (Tm MP / 5)) * Tm GS) - GS)
    private double reboundPercentage; //FORMULA: 100 * (TRB * (Tm MP / 5)) / (MP * (Tm TRB + Opp RBD))

    public Player(String name) {
        this.name = name;
    }

    public Player(String name, int age, int number, String position, boolean active,
                  int points, int goalsAttempted, int goalsScored, int freeThrowsAttempted,
                  double turnovers, double minutesPLayed, int assists, double offensiveRebounds,
                  double defensiveRebounds, double defensiveBPM, double offensiveBPM, String teamName,
                  int teamPlayerNumber, double teamMinutePlayed, int teamGoalsAttempted, int teamGoalsScored,
                  int teamFreeThrowsAttempted, double teamTurnovers, double teamRebounds, double opponentRebounds) {
        this.name = name;
        this.age = age;
        this.number = number;
        this.position = position;
        this.active = active;
        this.points = points;
        this.goalsAttempted = goalsAttempted;
        this.goalsScored = goalsScored;
        this.freeThrowsAttempted = freeThrowsAttempted;
        this.turnovers = turnovers;
        this.minutesPLayed = minutesPLayed;
        this.assists = assists;
        this.offensiveRebounds = offensiveRebounds;
        this.defensiveRebounds = defensiveRebounds;
        totalRebounds = offensiveRebounds + defensiveRebounds;
        this.defensiveBPM = defensiveBPM;
        this.offensiveBPM = offensiveBPM;
        team = new Team(teamName, teamPlayerNumber, teamMinutePlayed, teamGoalsAttempted, teamGoalsScored, teamFreeThrowsAttempted, teamTurnovers, teamRebounds);
        calculateReboundPercentage(opponentRebounds);
    }

    private void calculateReboundPercentage(double opponentRebounds) {

    }
}
