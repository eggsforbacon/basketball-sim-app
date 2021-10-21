package model.objects;

import java.io.Serializable;

public class Team implements Serializable {

    private String name;
    private String country;
    private int players;

    public Team(String name) {
        this.name = name;
    }

    public Team(String name, String country) {
        this.name = name;
        this.country = country;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void addPlayer() {
        players++;
    }

    public void removePlayer() {
        players--;
    }
}
