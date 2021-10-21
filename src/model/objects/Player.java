package model.objects;

import java.io.Serializable;

public class Player implements Serializable {

    //Basic Info

    private String name;
    private int age;
    private Team team;
    private int number;
    private String position;
    private String classification;
    private boolean active;

    //Calculated Stats

    private int points;
    private double turnoverPercentage;
    private double usagePercentage;
    private double assistPercentage;
    private double reboundPercentage;
    private double defensiveBPM;
    private double offensiveBPM;

    public Player(String name) {
        this.name = name;
    }

    public Player(String name, int age, int number, String position, boolean active,
                  int points, double turnoverPercentage, double usagePercentage, double assistPercentage,
                  double reboundPercentage, double defensiveBPM, double offensiveBPM, String teamName, String teamCountry ) {
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
        team = new Team(teamName, teamCountry);
    }

    // Setters and Getters


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
}
