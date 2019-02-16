package com.example.paul.betprofessor.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

import com.example.paul.betprofessor.R;
import com.example.paul.betprofessor.ui.helpers.SearchForTeamAdapter;
import com.example.paul.betprofessor.ui.helpers.TeamAdapter;
import com.example.paul.betprofessor.ui.helpers.TeamItem;
import com.example.paul.betprofessor.viewModel.SearchForTeamViewModel;

import java.util.ArrayList;

public class SearchForTeamActivity extends AppCompatActivity {

    private AppCompatSpinner spinner;
    private RecyclerView recyclerView;
    private SearchForTeamAdapter adapterForRecyclerView;

    private ArrayList<TeamItem> teamsList;
    private TeamAdapter adapterForSpinner;

    private SearchForTeamViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_for_team);

        initAllElements();

        showRequest();
    }

    private void initAllElements(){

        spinner = findViewById(R.id.search_spinner);
        recyclerView = findViewById(R.id.search_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapterForRecyclerView = new SearchForTeamAdapter();
        recyclerView.setAdapter(adapterForRecyclerView);

        viewModel = new SearchForTeamViewModel(getApplication());

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);

        final String[] listOfTeams = getApplication().getResources().getStringArray(R.array.arrayOfTeamNames);
        teamsList = new ArrayList<>();
        teamsList.add(new TeamItem(getString(R.string.search_for_team), R.drawable.empty_logo));
        teamsList.add(new TeamItem(listOfTeams[0], R.drawable.atlanta_hawks));
        teamsList.add(new TeamItem(listOfTeams[1], R.drawable.boston_celtics));
        teamsList.add(new TeamItem(listOfTeams[2], R.drawable.brooklyn_nets));
        teamsList.add(new TeamItem(listOfTeams[3], R.drawable.charlotte_hornets));
        teamsList.add(new TeamItem(listOfTeams[4], R.drawable.chicago_bulls));
        teamsList.add(new TeamItem(listOfTeams[5], R.drawable.cleveland_cavaliers));
        teamsList.add(new TeamItem(listOfTeams[6], R.drawable.dallas_mavericks));
        teamsList.add(new TeamItem(listOfTeams[7], R.drawable.denver_nuggets));
        teamsList.add(new TeamItem(listOfTeams[8], R.drawable.detroit_pistons));
        teamsList.add(new TeamItem(listOfTeams[9], R.drawable.golden_state_warriors));
        teamsList.add(new TeamItem(listOfTeams[10], R.drawable.houston_rockets));
        teamsList.add(new TeamItem(listOfTeams[11], R.drawable.indiana_pacers));
        teamsList.add(new TeamItem(listOfTeams[12], R.drawable.la_lakers));
        teamsList.add(new TeamItem(listOfTeams[13], R.drawable.los_angeles_clippers));
        teamsList.add(new TeamItem(listOfTeams[14], R.drawable.memphis_grizzlies));
        teamsList.add(new TeamItem(listOfTeams[15], R.drawable.miami_heat));
        teamsList.add(new TeamItem(listOfTeams[16], R.drawable.milwaukee_bucks));
        teamsList.add(new TeamItem(listOfTeams[17], R.drawable.minnesota_timberwolves));
        teamsList.add(new TeamItem(listOfTeams[18], R.drawable.new_orleans_pelicans));
        teamsList.add(new TeamItem(listOfTeams[19], R.drawable.new_york_knicks));
        teamsList.add(new TeamItem(listOfTeams[20], R.drawable.oklahoma_city_thunder));
        teamsList.add(new TeamItem(listOfTeams[21], R.drawable.orlando_magic));
        teamsList.add(new TeamItem(listOfTeams[22], R.drawable.philadelphia_76ers));
        teamsList.add(new TeamItem(listOfTeams[23], R.drawable.phoenix_suns));
        teamsList.add(new TeamItem(listOfTeams[24], R.drawable.portland_trail_blazers));
        teamsList.add(new TeamItem(listOfTeams[25], R.drawable.sacramento_kings));
        teamsList.add(new TeamItem(listOfTeams[26], R.drawable.san_antonio_spurs));
        teamsList.add(new TeamItem(listOfTeams[27], R.drawable.toronto_raptors));
        teamsList.add(new TeamItem(listOfTeams[28], R.drawable.utah_jazz));
        teamsList.add(new TeamItem(listOfTeams[29], R.drawable.washington_wizards));

        adapterForSpinner = new TeamAdapter(SearchForTeamActivity.this, teamsList);
        spinner.setAdapter(adapterForSpinner);
    }

    private void showRequest(){

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                TeamItem clickedItem = (TeamItem) adapterView.getItemAtPosition(i);

                if (clickedItem.getTeamName().contains("Team?")){
                    setTitle(R.string.search_for_team);
                    return;
                }else {
                    setTitle(clickedItem.getTeamName());
                    viewModel.setValue(clickedItem.getTeamName());
                    adapterForRecyclerView.setListOfAllTeamGames(viewModel.getAllTeamGames());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
