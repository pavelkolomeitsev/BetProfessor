package com.example.paul.betprofessor.ui.helpers;

public class TeamItem {

    private String mTeamName;
    private int mLogoImage;

    public TeamItem(String mTeamName, int mLogoImage) {
        this.mTeamName = mTeamName;
        this.mLogoImage = mLogoImage;
    }

    public String getTeamName() {
        return mTeamName;
    }

    public int getLogoImage() {
        return mLogoImage;
    }
}
