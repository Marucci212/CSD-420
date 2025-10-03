// Justin Marucci 
// Assignment 10
// 10-1-2025

package com.meco.fans.model;

public class Fan {
    private int id;
    private String firstName;
    private String lastName;
    private String favoriteTeam;

    public Fan() {}
    public Fan(int id, String firstName, String lastName, String favoriteTeam) {
        this.id = id; this.firstName = firstName; this.lastName = lastName; this.favoriteTeam = favoriteTeam;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getFavoriteTeam() { return favoriteTeam; }
    public void setFavoriteTeam(String favoriteTeam) { this.favoriteTeam = favoriteTeam; }
}
