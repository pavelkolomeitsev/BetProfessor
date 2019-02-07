package com.example.paul.betprofessor.ui.helpers;

public class AutoCompleteTeamItem {

    private String mTeamName;
    private int mLogoImage;

    public AutoCompleteTeamItem(String mTeamName, int mLogoImage) {
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
