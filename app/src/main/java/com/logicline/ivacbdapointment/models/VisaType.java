package com.logicline.ivacbdapointment.models;

public class VisaType {
    private String title;
    private int imageDrawable;

    public VisaType(String title, int imageDrawable) {
        this.title = title;
        this.imageDrawable = imageDrawable;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageDrawable() {
        return imageDrawable;
    }

    public void setImageDrawable(int imageDrawable) {
        this.imageDrawable = imageDrawable;
    }
}
