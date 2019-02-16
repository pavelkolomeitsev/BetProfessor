package com.example.paul.betprofessor.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.paul.betprofessor.R;
import com.example.paul.betprofessor.ui.helpers.SearchForTeamAdapter;
import com.example.paul.betprofessor.viewModel.SearchForTeamViewModel;

public class TeamResultsActivity extends AppCompatActivity {

    private String teamName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_results);

        SearchForTeamViewModel viewModel = new SearchForTeamViewModel(getApplication());

        RecyclerView recyclerView = findViewById(R.id.recycler_view_team_results);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SearchForTeamAdapter adapter = new SearchForTeamAdapter();
        recyclerView.setAdapter(adapter);

        Intent intent = getIntent();
        teamName = intent.getStringExtra(TeamTipsActivity.KEY_TEAM_NAME);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        setTitle(teamName);

        viewModel.setValue(teamName);
        adapter.setListOfAllTeamGames(viewModel.getAllTeamGames());
    }
}
