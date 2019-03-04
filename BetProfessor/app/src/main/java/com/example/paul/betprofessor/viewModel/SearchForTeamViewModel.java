package com.example.paul.betprofessor.viewModel;

import android.app.Application;

import com.example.paul.betprofessor.model.OneResult;
import com.example.paul.betprofessor.model.Repository;

import java.util.List;

public class SearchForTeamViewModel {

    private Repository repository;
    private List<OneResult> findAllTeamGames;
    private List<OneResult> faceToFaceMeetings;

    public SearchForTeamViewModel(Application application){

        repository = new Repository(application);
    }

    public void setValue(String teamName) {

        findAllTeamGames = repository.findAllTeamGames(teamName);
    }

    public List<OneResult> getAllTeamGames() {

        return findAllTeamGames;
    }

    public List<OneResult> getFaceToFaceMeetings(String firstTeam, String secondTeam) {

        faceToFaceMeetings = repository.faceToFaceMeetings(firstTeam, secondTeam);

        return faceToFaceMeetings;
    }
}
