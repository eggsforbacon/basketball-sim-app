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
                  int points, double turnoverPercentage, double usagePercentage, double assistPercentage,
                  double reboundPercentage, double defensiveBPM, double offensiveBPM, String teamName) {
        this.name = name;
        this.age = age;
        this.number = number;
        this.position = position;
        this.active = active;
        this.points = points;
        this.turnoverPercentage = turnoverPercentage;
        this.usagePercentage = usagePercentage;
        this.assistPercentage = assistPercentage;
        this.reboundPercentage = reboundPercentage;
        this.defensiveBPM = defensiveBPM;
        this.offensiveBPM = offensiveBPM;
        team = new Team(teamName);
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

    //*****************************************************************************************************************************
    // Setters and Getter


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
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

    public double getMinutesPLayed() {
        return minutesPLayed;
    }

    public void setMinutesPLayed(double minutesPLayed) {
        this.minutesPLayed = minutesPLayed;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public double getOffensiveRebounds() {
        return offensiveRebounds;
    }

    public void setOffensiveRebounds(double offensiveRebounds) {
        this.offensiveRebounds = offensiveRebounds;
    }

    public double getDefensiveRebounds() {
        return defensiveRebounds;
    }

    public void setDefensiveRebounds(double defensiveRebounds) {
        this.defensiveRebounds = defensiveRebounds;
    }

    public double getDefensiveBPM() {
        return defensiveBPM;
    }

    public void setDefensiveBPM(double defensiveBPM) {
        this.defensiveBPM = defensiveBPM;
    }

    public double getOffensiveBPM() {
        return offensiveBPM;
    }

    public void setOffensiveBPM(double offensiveBPM) {
        this.offensiveBPM = offensiveBPM;
    }

    public double getTotalRebounds() {
        return totalRebounds;
    }

    public void setTotalRebounds(double totalRebounds) {
        this.totalRebounds = totalRebounds;
    }
}
