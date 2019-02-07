package com.example.paul.betprofessor.ui.helpers;

public class TeamTipsItem {

    private int mImageResource;
    private String mTeamName;
    private Double mWinPercentage;

    public TeamTipsItem(int mImageResource, String mTeamName, Double mWinPercentage) {
        this.mImageResource = mImageResource;
        this.mTeamName = mTeamName;
        this.mWinPercentage = mWinPercentage;
    }

    public int getImageResource() {
        return mImageResource;
    }

    public String getTeamName() {
        return mTeamName;
    }

    public Double getWinPercentage() {
        return mWinPercentage;
    }
}
