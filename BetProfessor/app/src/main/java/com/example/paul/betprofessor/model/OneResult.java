package com.example.paul.betprofessor.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "results")
public class OneResult {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @NonNull
    private String date;

    @NonNull
    private String homeTeamName;

    private String homeTeamTotal;

    private String homeHandicap;

    private String homeLine;

    @NonNull
    private String guestTeamName;

    private String guestTeamTotal;

    private String guestHandicap;

    private String guestLine;

    private String homeResult;

    private String guestResult;

    public OneResult(@NonNull String date, @NonNull String homeTeamName, String homeTeamTotal, String homeHandicap, String homeLine, @NonNull String guestTeamName, String guestTeamTotal, String guestHandicap, String guestLine, String homeResult, String guestResult) {
        this.date = date;
        this.homeTeamName = homeTeamName;
        this.homeTeamTotal = homeTeamTotal;
        this.homeHandicap = homeHandicap;
        this.homeLine = homeLine;
        this.guestTeamName = guestTeamName;
        this.guestTeamTotal = guestTeamTotal;
        this.guestHandicap = guestHandicap;
        this.guestLine = guestLine;
        this.homeResult = homeResult;
        this.guestResult = guestResult;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    @NonNull
    public int getId() {
        return id;
    }

    @NonNull
    public String getDate() {
        return date;
    }

    @NonNull
    public String getHomeTeamName() {
        return homeTeamName;
    }

    public String getHomeTeamTotal() {
        return homeTeamTotal;
    }

    public String getHomeHandicap() {
        return homeHandicap;
    }

    public String getHomeLine() {
        return homeLine;
    }

    @NonNull
    public String getGuestTeamName() {
        return guestTeamName;
    }

    public String getGuestTeamTotal() {
        return guestTeamTotal;
    }

    public String getGuestHandicap() {
        return guestHandicap;
    }

    public String getGuestLine() {
        return guestLine;
    }

    public String getHomeResult() {
        return homeResult;
    }

    public String getGuestResult() {
        return guestResult;
    }
}
