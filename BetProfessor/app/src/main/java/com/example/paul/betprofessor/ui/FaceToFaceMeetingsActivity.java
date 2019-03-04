package com.example.paul.betprofessor.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.paul.betprofessor.R;
import com.example.paul.betprofessor.model.OneResult;
import com.example.paul.betprofessor.ui.helpers.SearchForTeamAdapter;
import com.example.paul.betprofessor.ui.helpers.TeamAdapter;
import com.example.paul.betprofessor.ui.helpers.TeamItem;
import com.example.paul.betprofessor.viewModel.SearchForTeamViewModel;

import java.util.ArrayList;
import java.util.List;

public class FaceToFaceMeetingsActivity extends AppCompatActivity {

    private AppCompatSpinner firstTeamSpinner, secondTeamSpinner;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private SearchForTeamAdapter adapterForRecyclerView;
    private ArrayList<TeamItem> firstTeamList, secondTeamList;
    private TeamAdapter adapterForFirstTeam, adapterForSecondTeam;
    private SearchForTeamViewModel viewModel;
    private String firstTeam = "", secondTeam = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_to_face_meetings);

        initAllElements();

        showRequest();
    }

    private void initAllElements() {

        firstTeamSpinner = findViewById(R.id.first_team_spinner);
        secondTeamSpinner = findViewById(R.id.second_team_spinner);
        recyclerView = findViewById(R.id.face_to_face_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapterForRecyclerView = new SearchForTeamAdapter();
        recyclerView.setAdapter(adapterForRecyclerView);
        fab = findViewById(R.id.fab_face_to_face);

        viewModel = new SearchForTeamViewModel(getApplication());

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        setTitle(R.string.Face_To_Face_Meetings);

        final String[] listOfTeams = getApplication().getResources().getStringArray(R.array.arrayOfTeamNames);

        firstTeamList = new ArrayList<>();
        firstTeamList.add(new TeamItem(getString(R.string.first_team), R.drawable.empty_logo));

        secondTeamList = new ArrayList<>();
        secondTeamList.add(new TeamItem(getString(R.string.second_team), R.drawable.empty_logo));

        ArrayList<TeamItem> temp = new ArrayList<>();
        temp.add(new TeamItem(listOfTeams[0], R.drawable.atlanta_hawks));
        temp.add(new TeamItem(listOfTeams[1], R.drawable.boston_celtics));
        temp.add(new TeamItem(listOfTeams[2], R.drawable.brooklyn_nets));
        temp.add(new TeamItem(listOfTeams[3], R.drawable.charlotte_hornets));
        temp.add(new TeamItem(listOfTeams[4], R.drawable.chicago_bulls));
        temp.add(new TeamItem(listOfTeams[5], R.drawable.cleveland_cavaliers));
        temp.add(new TeamItem(listOfTeams[6], R.drawable.dallas_mavericks));
        temp.add(new TeamItem(listOfTeams[7], R.drawable.denver_nuggets));
        temp.add(new TeamItem(listOfTeams[8], R.drawable.detroit_pistons));
        temp.add(new TeamItem(listOfTeams[9], R.drawable.golden_state_warriors));
        temp.add(new TeamItem(listOfTeams[10], R.drawable.houston_rockets));
        temp.add(new TeamItem(listOfTeams[11], R.drawable.indiana_pacers));
        temp.add(new TeamItem(listOfTeams[12], R.drawable.la_lakers));
        temp.add(new TeamItem(listOfTeams[13], R.drawable.los_angeles_clippers));
        temp.add(new TeamItem(listOfTeams[14], R.drawable.memphis_grizzlies));
        temp.add(new TeamItem(listOfTeams[15], R.drawable.miami_heat));
        temp.add(new TeamItem(listOfTeams[16], R.drawable.milwaukee_bucks));
        temp.add(new TeamItem(listOfTeams[17], R.drawable.minnesota_timberwolves));
        temp.add(new TeamItem(listOfTeams[18], R.drawable.new_orleans_pelicans));
        temp.add(new TeamItem(listOfTeams[19], R.drawable.new_york_knicks));
        temp.add(new TeamItem(listOfTeams[20], R.drawable.oklahoma_city_thunder));
        temp.add(new TeamItem(listOfTeams[21], R.drawable.orlando_magic));
        temp.add(new TeamItem(listOfTeams[22], R.drawable.philadelphia_76ers));
        temp.add(new TeamItem(listOfTeams[23], R.drawable.phoenix_suns));
        temp.add(new TeamItem(listOfTeams[24], R.drawable.portland_trail_blazers));
        temp.add(new TeamItem(listOfTeams[25], R.drawable.sacramento_kings));
        temp.add(new TeamItem(listOfTeams[26], R.drawable.san_antonio_spurs));
        temp.add(new TeamItem(listOfTeams[27], R.drawable.toronto_raptors));
        temp.add(new TeamItem(listOfTeams[28], R.drawable.utah_jazz));
        temp.add(new TeamItem(listOfTeams[29], R.drawable.washington_wizards));

        firstTeamList.addAll(1, temp);
        secondTeamList.addAll(1, temp);

        adapterForFirstTeam = new TeamAdapter(FaceToFaceMeetingsActivity.this, firstTeamList);
        firstTeamSpinner.setAdapter(adapterForFirstTeam);

        adapterForSecondTeam = new TeamAdapter(FaceToFaceMeetingsActivity.this, secondTeamList);
        secondTeamSpinner.setAdapter(adapterForSecondTeam);
    }

    private void showRequest() {

        firstTeamSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                TeamItem clickedItem = (TeamItem) adapterView.getItemAtPosition(i);

                firstTeam = clickedItem.getTeamName();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        secondTeamSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                TeamItem clickedItem = (TeamItem) adapterView.getItemAtPosition(i);

                secondTeam = clickedItem.getTeamName();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<OneResult> selectedGames;
                selectedGames = viewModel.getFaceToFaceMeetings(firstTeam, secondTeam);

                if (firstTeam.isEmpty() || secondTeam.isEmpty()) {
                    Toast.makeText(FaceToFaceMeetingsActivity.this, R.string.choose_the_teams, Toast.LENGTH_SHORT).show();
                    return;
                } else if (firstTeam.contains(secondTeam)) {
                    Toast.makeText(FaceToFaceMeetingsActivity.this, R.string.wrong_choice, Toast.LENGTH_SHORT).show();
                    return;
                } else if (firstTeam.contains("First team?") || firstTeam.contains("Second team?")
                        || secondTeam.contains("First team?") || secondTeam.contains("Second team?")) {
                    Toast.makeText(FaceToFaceMeetingsActivity.this, R.string.wrong_choice, Toast.LENGTH_SHORT).show();
                    return;
                }else if (selectedGames == null){
                    Toast.makeText(FaceToFaceMeetingsActivity.this, "Any results!", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    adapterForRecyclerView.setListOfAllTeamGames(selectedGames);
                }
            }
        });
    }
}
