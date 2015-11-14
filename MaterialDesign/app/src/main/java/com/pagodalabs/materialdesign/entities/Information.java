package com.pagodalabs.materialdesign.entities;

/**
 * Created by Ajit Kumar Baral on 6/10/2015.
 */
public class Information {
    private int iconId;
    private String title;

    public Information() {
    }

    public Information(int iconId, String title) {
        this.iconId = iconId;
        this.title = title;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
