package com.example.retrofitandroidexample.Model;

public class Entries {
    private String API;
    private String Description;
    private String Auth;
    private boolean HTTPS;
    private String Cors;
    private String Link;
    private String Category;

    public Entries(String API, String description, String auth, boolean HTTPS, String cors, String link, String category) {
        this.API = API;
        this.Description = description;
        this.Auth = auth;
        this.HTTPS = HTTPS;
        this.Cors = cors;
        this.Link = link;
        this.Category = category;
    }

    public String getAPI() {
        return API;
    }

    public void setAPI(String API) {
        this.API = API;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getAuth() {
        return Auth;
    }

    public void setAuth(String auth) {
        Auth = auth;
    }

    public boolean isHTTPS() {
        return HTTPS;
    }

    public void setHTTPS(boolean HTTPS) {
        this.HTTPS = HTTPS;
    }

    public String getCors() {
        return Cors;
    }

    public void setCors(String cors) {
        Cors = cors;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }
}
