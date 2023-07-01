package com.example.retrofitandroidexample.example_model;

public class Favotite {
    private int id;
    private String favotite;

    public Favotite(int id, String favotite) {
        this.id = id;
        this.favotite = favotite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFavotite() {
        return favotite;
    }

    public void setFavotite(String favotite) {
        this.favotite = favotite;
    }
}
