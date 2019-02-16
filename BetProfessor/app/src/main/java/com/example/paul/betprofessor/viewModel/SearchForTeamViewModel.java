package com.example.paul.betprofessor.viewModel;

import android.app.Application;

import com.example.paul.betprofessor.model.OneResult;
import com.example.paul.betprofessor.model.Repository;

import java.util.Collections;
import java.util.List;

public class SearchForTeamViewModel {

    private Repository repository;
    private List<OneResult> findAllTeamGames;

    public SearchForTeamViewModel(Application application){

        repository = new Repository(application);
    }

    public void setValue(String teamName) {

        findAllTeamGames = repository.findAllTeamGames(teamName);
    }

    public List<OneResult> getAllTeamGames() {

        Collections.reverse(findAllTeamGames);
        return findAllTeamGames;
    }
}
