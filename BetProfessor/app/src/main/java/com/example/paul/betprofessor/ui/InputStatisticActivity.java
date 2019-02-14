package com.example.paul.betprofessor.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.paul.betprofessor.R;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class InputStatisticActivity extends AppCompatActivity {

    public static final String TEAM_TOTAL = "team total statistic";
    public static final String HANDICAP = "handicap statistic";
    public static final String LINE = "line statistic";

    private RadioGroup rGroup;
    private EditText etInputStatistic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_statistic);

        onInitialize();
    }

    private void onInitialize() {

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        setTitle(R.string.input_statistic_title);

        rGroup = findViewById(R.id.rGroup);
        etInputStatistic = findViewById(R.id.et_input_statistic);

    }

    public void onClick(View view){

        String savedText = etInputStatistic.getText().toString();
        FileOutputStream fos = null;

        switch (rGroup.getCheckedRadioButtonId()){

            case R.id.rTeamTotal:
                try {
                    fos = openFileOutput(TEAM_TOTAL, MODE_PRIVATE);
                    fos.write(savedText.getBytes());
                    etInputStatistic.getText().clear();
                    Toast.makeText(this, "Team total statistic was saved", Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (fos != null){
                        try {
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
            case R.id.rHandicap:
                try {
                    fos = openFileOutput(HANDICAP, MODE_PRIVATE);
                    fos.write(savedText.getBytes());
                    etInputStatistic.getText().clear();
                    Toast.makeText(this, "Handicap statistic was saved", Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (fos != null){
                        try {
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
            case R.id.rLine:
                try {
                    fos = openFileOutput(LINE, MODE_PRIVATE);
                    fos.write(savedText.getBytes());
                    etInputStatistic.getText().clear();
                    Toast.makeText(this, "Line statistic was saved", Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (fos != null){
                        try {
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
        }
    }
}
