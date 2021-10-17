package model.objects;

import model.data_structures.DefaultHashTable;

import java.io.Serializable;

public class Fiba implements Serializable {

    private DefaultHashTable<String, Team> teams;
    private DefaultHashTable<String, Player> players;

    public Fiba(DefaultHashTable<String, Team> teams, DefaultHashTable<String, Player> players) {
        this.teams = teams;
        this.players = players;
    }

    public Fiba() {

    }

    public DefaultHashTable<String, Team> getTeams() {
        return teams;
    }

    public void setTeams(DefaultHashTable<String, Team> teams) {
        this.teams = teams;
    }

    public DefaultHashTable<String, Player> getPlayers() {
        return players;
    }

    public void setPlayers(DefaultHashTable<String, Player> players) {
        this.players = players;
    }
}
