package com.example.paul.betprofessor.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paul.betprofessor.R;
import com.example.paul.betprofessor.ui.helpers.DialogOnSetNewMeasure;
import com.example.paul.betprofessor.viewModel.SetMeasureAsyncTask;
import com.example.paul.betprofessor.viewModel.TeamTipsAsyncTask;
import com.example.paul.betprofessor.viewModel.TeamTipsViewModel;

public class TeamTipsActivity extends AppCompatActivity implements DialogOnSetNewMeasure.DialogSetNewMeasureListener {

    public TextView numberOfGames, averageTeamTotal, serialNumberOfResult1, serialNumberOfResult2;
    public TextView middlemostTeamTotal1, middlemostTeamTotal2, middlemostMeasure;
    public TextView firstResult, secondResult, thirdResult, fourthResult, fifthResult, sixthResult, seventhResult, eighthResult, ninthResult, tenthResult;
    public TextView firstGameHandicap, secondGameHandicap, thirdGameHandicap, fourthGameHandicap, fifthGameHandicap, sixthGameHandicap, seventhGameHandicap, eighthGameHandicap, ninthGameHandicap, tenthGameHandicap;

    private String teamName;

    private TeamTipsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_tips);

        onInitialize();
        onSetValues();
    }

    private void onInitialize(){

        Intent intent = getIntent();
        teamName = intent.getStringExtra(MainActivity.KEY_TEAM_NAME);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        setTitle(teamName);

        viewModel = new TeamTipsViewModel(getApplication(), teamName);

        numberOfGames = findViewById(R.id.number_of_games);
        averageTeamTotal = findViewById(R.id.average_team_total);
        serialNumberOfResult1 = findViewById(R.id.serial_number_of_result1);
        serialNumberOfResult2 = findViewById(R.id.serial_number_of_result2);
        middlemostTeamTotal1 = findViewById(R.id.middlemost_team_total1);
        middlemostTeamTotal2 = findViewById(R.id.middlemost_team_total2);
        middlemostMeasure = findViewById(R.id.measure);
        firstResult = findViewById(R.id.first_result);
        secondResult = findViewById(R.id.second_result);
        thirdResult = findViewById(R.id.third_result);
        fourthResult = findViewById(R.id.fourth_result);
        fifthResult = findViewById(R.id.fifth_result);
        sixthResult = findViewById(R.id.sixth_result);
        seventhResult = findViewById(R.id.seventh_result);
        eighthResult = findViewById(R.id.eighth_result);
        ninthResult = findViewById(R.id.ninth_result);
        tenthResult = findViewById(R.id.tenth_result);
        firstGameHandicap = findViewById(R.id.first_game_handicap);
        secondGameHandicap = findViewById(R.id.second_game_handicap);
        thirdGameHandicap = findViewById(R.id.third_game_handicap);
        fourthGameHandicap = findViewById(R.id.fourth_game_handicap);
        fifthGameHandicap = findViewById(R.id.fifth_game_handicap);
        sixthGameHandicap = findViewById(R.id.sixth_game_handicap);
        seventhGameHandicap = findViewById(R.id.seventh_game_handicap);
        eighthGameHandicap = findViewById(R.id.eight_game_handicap);
        ninthGameHandicap = findViewById(R.id.ninth_game_handicap);
        tenthGameHandicap = findViewById(R.id.tenth_game_handicap);
    }

    private void onSetValues(){

        TeamTipsAsyncTask task = new TeamTipsAsyncTask(this);
        task.execute(viewModel);
    }

    public void onClick(View view){

        switch (view.getId()){
            case R.id.btn_set_measures:
                DialogOnSetNewMeasure dialog = new DialogOnSetNewMeasure();
                dialog.show(getSupportFragmentManager(), "dialogOnSetNewMeasure");
                break;
            case R.id.btn_statistic_team_total:
                Toast.makeText(this, "team total", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_statistic_handicap:
                Toast.makeText(this, "handicap", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_get_team_results:
                Toast.makeText(this, "team results", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_statistic_line:
                Toast.makeText(this, "lines", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void applyNewMeasure(String measure) {
        // here implement asyncTask to correct measure and last ten results
        middlemostMeasure.setText(measure);

        SetMeasureAsyncTask task = new SetMeasureAsyncTask(this, measure);
        task.execute(viewModel);
    }
}
