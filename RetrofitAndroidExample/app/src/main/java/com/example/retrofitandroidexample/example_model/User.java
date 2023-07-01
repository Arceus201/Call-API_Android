package com.example.retrofitandroidexample.example_model;

import java.util.List;

public class User {

    private int id;
    private String name;
    private boolean isActive;
    private Job job;
    private List<Favotite> favotite;

    public User(int id, String name, boolean isActive, Job job, List<Favotite> favotite) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
        this.job = job;
        this.favotite = favotite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public List<Favotite> getFavotite() {
        return favotite;
    }

    public void setFavotite(List<Favotite> favotite) {
        this.favotite = favotite;
    }
}
