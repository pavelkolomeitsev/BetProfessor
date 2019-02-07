package com.example.paul.betprofessor.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.paul.betprofessor.R;
import com.example.paul.betprofessor.model.Repository;
import com.example.paul.betprofessor.ui.helpers.TeamTipsItem;

import java.util.ArrayList;

public class MainViewModel extends AndroidViewModel {

    private static final String TAG = "MyLog";

    private Repository repository;
    private String mTeamName;
    private final String[] arrayOfTeamNames;
    public Double[] arrayOfWinPercentage;

    public MainViewModel(@NonNull Application application) {
        super(application);

        repository = new Repository(application);
        arrayOfTeamNames = application.getResources().getStringArray(R.array.arrayOfTeamNames);
    }

    public ArrayList<TeamTipsItem> getListOfTeamTips(){
        ArrayList<TeamTipsItem> listOfTeamTips = new ArrayList<>();



        return listOfTeamTips;
    }
}
