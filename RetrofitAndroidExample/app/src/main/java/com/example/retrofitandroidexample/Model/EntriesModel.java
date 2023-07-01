package com.example.retrofitandroidexample.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EntriesModel {
    private int count;
//    @SerializedName("entries")
    private List<Entries> entries;

    public EntriesModel(int count, List<Entries> entries) {
        this.count = count;
        this.entries = entries;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Entries> getEntries() {
        return entries;
    }

    public void setEntries(List<Entries> entries) {
        this.entries = entries;
    }
}
