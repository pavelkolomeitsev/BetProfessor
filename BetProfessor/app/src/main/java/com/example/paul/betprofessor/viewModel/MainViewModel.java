package com.example.paul.betprofessor.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.paul.betprofessor.model.OneResult;
import com.example.paul.betprofessor.model.Repository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private Repository repository;

    public MainViewModel(@NonNull Application application) {
        super(application);

        repository = new Repository(application);
    }

    public Double getWinPercentageOfTeam(String teamName){

        Double wins = 0.0;
        Double games = 0.0;

        List<OneResult> findAllTeamGames = repository.findAllTeamGames(teamName);

        for (OneResult item: findAllTeamGames) {
            if ((item.getHomeTeamName().equals(teamName) || item.getGuestTeamName().equals(teamName)) &&
                    (!item.getHomeResult().equals("") || !item.getGuestResult().equals(""))){
                games++;

                if (item.getHomeTeamName().equals(teamName) &&
                        (Integer.parseInt(item.getHomeResult()) > Integer.parseInt(item.getGuestResult()))){
                    wins++;
                }
                else if (item.getGuestTeamName().equals(teamName) &&
                        (Integer.parseInt(item.getGuestResult()) > Integer.parseInt(item.getHomeResult()))){
                    wins++;
                }
            }
        }

        if (games == 0.0) return 0.0;
        else{
            int temp = (int) ((wins/games) * 1000);
            return temp / 10.0;
        }
    }
}
