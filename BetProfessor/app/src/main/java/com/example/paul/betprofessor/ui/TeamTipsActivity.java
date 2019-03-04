package com.example.paul.betprofessor.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.paul.betprofessor.R;
import com.example.paul.betprofessor.ui.helpers.DialogOnSetNewMeasure;
import com.example.paul.betprofessor.viewModel.SetMeasureAsyncTask;
import com.example.paul.betprofessor.viewModel.TeamTipsAsyncTask;
import com.example.paul.betprofessor.viewModel.TeamTipsViewModel;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class TeamTipsActivity extends AppCompatActivity implements DialogOnSetNewMeasure.DialogSetNewMeasureListener {

    public static final String KEY_TEAM_NAME = "betprofessor.KEY_TEAM_NAME";

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

    private void onInitialize() {

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

    private void onSetValues() {

        TeamTipsAsyncTask task = new TeamTipsAsyncTask(this);
        task.execute(viewModel);
    }

    public void onClick(View view) {

        Intent intent;
        FileInputStream fis = null;
        StringBuilder sb = null;
        AlertDialog alertDialog;

        switch (view.getId()) {
            case R.id.btn_set_measures:
                DialogOnSetNewMeasure dialog = new DialogOnSetNewMeasure();
                dialog.show(getSupportFragmentManager(), "dialogOnSetNewMeasure");
                break;
            case R.id.btn_statistic_team_total:
                try {
                    fis = openFileInput(InputStatisticActivity.TEAM_TOTAL);
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader br = new BufferedReader(isr);
                    sb = new StringBuilder();
                    sb.append("");
                    String text;

                    while ((text = br.readLine()) != null) {
                        sb.append(text).append("\n");
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                alertDialog = new AlertDialog.Builder(this)
                        .setTitle(R.string.team_total_statistic)
                        .setMessage(sb)
                        .setPositiveButton(R.string.btn_dialog_ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).create();
                alertDialog.show();
                break;
            case R.id.btn_statistic_handicap:
                try {
                    fis = openFileInput(InputStatisticActivity.HANDICAP);
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader br = new BufferedReader(isr);
                    sb = new StringBuilder();
                    sb.append("");
                    String text;

                    while ((text = br.readLine()) != null) {
                        sb.append(text).append("\n");
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                alertDialog = new AlertDialog.Builder(this)
                        .setTitle(R.string.handicap_statistic)
                        .setMessage(sb)
                        .setPositiveButton(R.string.btn_dialog_ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).create();
                alertDialog.show();
                break;
            case R.id.btn_get_team_results:
                intent = new Intent(TeamTipsActivity.this, TeamResultsActivity.class);
                intent.putExtra(KEY_TEAM_NAME, teamName);
                startActivity(intent);
                break;
            case R.id.btn_statistic_line:
                try {
                    fis = openFileInput(InputStatisticActivity.LINE);
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader br = new BufferedReader(isr);
                    sb = new StringBuilder();
                    sb.append("");
                    String text;

                    while ((text = br.readLine()) != null) {
                        sb.append(text).append("\n");
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                alertDialog = new AlertDialog.Builder(this)
                        .setTitle(R.string.line_statistic)
                        .setMessage(sb)
                        .setPositiveButton(R.string.btn_dialog_ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).create();
                alertDialog.show();
                break;
            case R.id.btn_ftf_meetings:
                intent = new Intent(TeamTipsActivity.this, FaceToFaceMeetingsActivity.class);
                startActivity(intent);
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
