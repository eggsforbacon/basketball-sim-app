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
    private double minutesPlayed; //(MP)
    private int assists; //(AST) <- LOWER THAN GS
    private double offensiveRebounds; //(ORB)
    private double defensiveRebounds; //(DRB)
    private double defensiveBPM; //(DBPM) <- WITHIN 10 AND -10
    private double offensiveBPM; //(OBPM) <- WITHIN 10 AND -10

    private double totalRebounds; // = offensiveRebounds + defensiveRebounds (TRB)
    private double totalShootingAttempts; //GA + 0.44 * FTA (TSA)

    //Calculated Stats

    private double turnoverPercentage; //FORMULA: 100 * TOV / (TSA + TOV)
    private double usagePercentage; //FORMULA: 100 * ((TSA + TOV) * (Tm MP / 5)) / (MP * (Tm TSA + Tm TOV))
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
                  double turnovers, double minutesPlayed, int assists, double offensiveRebounds,
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
        this.minutesPlayed = minutesPlayed;
        this.assists = assists;
        this.offensiveRebounds = offensiveRebounds;
        this.defensiveRebounds = defensiveRebounds;
        totalRebounds = offensiveRebounds + defensiveRebounds;
        this.defensiveBPM = defensiveBPM;
        this.offensiveBPM = offensiveBPM;
        double BIAS = 0.44;
        totalShootingAttempts = goalsAttempted + BIAS * freeThrowsAttempted;
        team = new Team(teamName, teamPlayerNumber, teamMinutePlayed, teamGoalsAttempted, teamGoalsScored, teamFreeThrowsAttempted, teamTurnovers, teamRebounds);
        calculateTurnoverPercentage();
        calculateUsagePercentage();
        calculateAssistPercentage();
        calculateReboundPercentage(opponentRebounds);
    }

    private void calculateTurnoverPercentage() {
        double numerator = 100 * turnovers;
        double denominator = totalShootingAttempts + turnovers;
        turnoverPercentage = numerator / denominator;
    }

    private void calculateUsagePercentage() {
        double numerator = 100 * ((totalShootingAttempts + turnovers) * team.getMinutesPlayed() / 5);
        double denominator = minutesPlayed * (team.getTotalShootingAttempts() + team.getTurnovers());
        usagePercentage = numerator / denominator;
    }

    private void calculateAssistPercentage() {
        double numerator = 100 * assists;
        double denominator = (((minutesPlayed / (team.getMinutesPlayed() / 5)) * team.getGoalsScored()) - goalsScored);
        assistPercentage = numerator / denominator;
    }

    private void calculateReboundPercentage(double opponentRebounds) {
        double numerator = 100 * (totalRebounds * (team.getMinutesPlayed() / 5));
        double denominator = minutesPlayed * (team.getRebounds() + opponentRebounds);
        reboundPercentage = numerator / denominator;
    }

    /*Getters*/

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Team getTeam() {
        return team;
    }

    public int getNumber() {
        return number;
    }

    public String getPosition() {
        return position;
    }

    public String getClassification() {
        return classification;
    }

    public boolean isActive() {
        return active;
    }

    public int getPoints() {
        return points;
    }

    public int getGoalsAttempted() {
        return goalsAttempted;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public int getFreeThrowsAttempted() {
        return freeThrowsAttempted;
    }

    public double getTurnovers() {
        return turnovers;
    }

    public double getMinutesPlayed() {
        return minutesPlayed;
    }

    public int getAssists() {
        return assists;
    }

    public double getOffensiveRebounds() {
        return offensiveRebounds;
    }

    public double getDefensiveRebounds() {
        return defensiveRebounds;
    }

    public double getDefensiveBPM() {
        return defensiveBPM;
    }

    public double getOffensiveBPM() {
        return offensiveBPM;
    }

    public double getTotalRebounds() {
        return totalRebounds;
    }

    public double getTotalShootingAttempts() {
        return totalShootingAttempts;
    }

    public double getTurnoverPercentage() {
        return turnoverPercentage;
    }

    public double getUsagePercentage() {
        return usagePercentage;
    }

    public double getAssistPercentage() {
        return assistPercentage;
    }

    public double getReboundPercentage() {
        return reboundPercentage;
    }

    /*Setters*/

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setGoalsAttempted(int goalsAttempted) {
        this.goalsAttempted = goalsAttempted;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

    public void setFreeThrowsAttempted(int freeThrowsAttempted) {
        this.freeThrowsAttempted = freeThrowsAttempted;
    }

    public void setTurnovers(double turnovers) {
        this.turnovers = turnovers;
    }

    public void setMinutesPlayed(double minutesPlayed) {
        this.minutesPlayed = minutesPlayed;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public void setOffensiveRebounds(double offensiveRebounds) {
        this.offensiveRebounds = offensiveRebounds;
    }

    public void setDefensiveRebounds(double defensiveRebounds) {
        this.defensiveRebounds = defensiveRebounds;
    }

    public void setDefensiveBPM(double defensiveBPM) {
        this.defensiveBPM = defensiveBPM;
    }

    public void setOffensiveBPM(double offensiveBPM) {
        this.offensiveBPM = offensiveBPM;
    }

    public void setTotalRebounds(double totalRebounds) {
        this.totalRebounds = totalRebounds;
    }

    public void setTotalShootingAttempts(double totalShootingAttempts) {
        this.totalShootingAttempts = totalShootingAttempts;
    }

    public void setTurnoverPercentage(double turnoverPercentage) {
        this.turnoverPercentage = turnoverPercentage;
    }

    public void setUsagePercentage(double usagePercentage) {
        this.usagePercentage = usagePercentage;
    }

    public void setAssistPercentage(double assistPercentage) {
        this.assistPercentage = assistPercentage;
    }

    public void setReboundPercentage(double reboundPercentage) {
        this.reboundPercentage = reboundPercentage;
    }
}
