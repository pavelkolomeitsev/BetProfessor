package com.example.paul.betprofessor.ui;

import android.app.DatePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.button.MaterialButton;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paul.betprofessor.R;
import com.example.paul.betprofessor.model.OneResult;
import com.example.paul.betprofessor.ui.helpers.TeamAdapter;
import com.example.paul.betprofessor.ui.helpers.TeamItem;
import com.example.paul.betprofessor.viewModel.OneResultViewModel;

import java.util.ArrayList;
import java.util.Calendar;

public class AddResultActivity extends AppCompatActivity {

    private TextView tvDate;
    private MaterialButton btnDate;
    private AppCompatSpinner homeTeamSpinner;
    private TextInputEditText homeTeamSpread, homeLine, homeTeamTotal, homeTeamResult;
    private TextInputEditText guestTeamResult, guestTeamSpread, guestLine, guestTeamTotal;
    private AppCompatSpinner guestTeamSpinner;
    private FloatingActionButton fabAddResult;

    private OneResultViewModel viewModel;

    private String date, homeTeamName, homeSpread, guestTeamName, guestSpread, mHomeLine, mHomeTeamTotal, homeResult, mGuestLine, mGuestTeamTotal, guestResult;

    private Calendar currentDate;
    private int day, month, year;

    private ArrayList<TeamItem> homeTeamsList, guestTeamsList;
    private TeamAdapter adapterForHomeTeams, adapterForGuestTeams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_result);

        initAllElements();

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(AddResultActivity.this // here maybe a mistake!!!
                        , new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                        month += 1;

                        tvDate.setText(day + "-" + month + "-" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        adapterForHomeTeams = new TeamAdapter(AddResultActivity.this, homeTeamsList);
        homeTeamSpinner.setAdapter(adapterForHomeTeams);

        adapterForGuestTeams = new TeamAdapter(AddResultActivity.this, guestTeamsList);
        guestTeamSpinner.setAdapter(adapterForGuestTeams);

        homeTeamSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TeamItem clickedItem = (TeamItem) adapterView.getItemAtPosition(i);
                homeTeamName = clickedItem.getTeamName();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        guestTeamSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TeamItem clickedItem = (TeamItem) adapterView.getItemAtPosition(i);
                guestTeamName = clickedItem.getTeamName();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        fabAddResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                date = tvDate.getText().toString();
                homeSpread = homeTeamSpread.getText().toString();
                mHomeLine = homeLine.getText().toString();
                mHomeTeamTotal = homeTeamTotal.getText().toString();
                homeResult = homeTeamResult.getText().toString();
                guestSpread = guestTeamSpread.getText().toString();
                mGuestLine = guestLine.getText().toString();
                mGuestTeamTotal = guestTeamTotal.getText().toString();
                guestResult = guestTeamResult.getText().toString();

                if (date.contentEquals("Date?") || homeTeamName.contentEquals("Home team?") || guestTeamName.contentEquals("Guest team?")){
                    Toast.makeText(AddResultActivity.this, "Date and/or teams` names are uncorrected", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (homeTeamName.equals(guestTeamName)){
                    Toast.makeText(AddResultActivity.this, "Teams` names have to be filled correctly", Toast.LENGTH_SHORT).show();
                    return;
                }

                OneResult oneResult = new OneResult(date, homeTeamName, mHomeTeamTotal, homeSpread, mHomeLine
                        , guestTeamName, mGuestTeamTotal, guestSpread, mGuestLine, homeResult, guestResult);
                viewModel.insert(oneResult);

                finish();
            }
        });
    }

    private void initAllElements() {

        tvDate = findViewById(R.id.tv_date);
        btnDate = findViewById(R.id.btn_date);
        homeTeamSpinner = findViewById(R.id.home_team_spinner);
        homeTeamSpread = findViewById(R.id.home_team_spread);
        homeLine = findViewById(R.id.home_line);
        homeTeamTotal = findViewById(R.id.home_team_total);
        homeTeamResult = findViewById(R.id.home_team_result);
        guestTeamResult = findViewById(R.id.guest_team_result);
        guestTeamSpread = findViewById(R.id.guest_team_spread);
        guestLine = findViewById(R.id.guest_line);
        guestTeamTotal = findViewById(R.id.guest_team_total);
        guestTeamSpinner = findViewById(R.id.guest_team_spinner);
        fabAddResult = findViewById(R.id.fab_add_result);

        currentDate = Calendar.getInstance();
        day = currentDate.get(Calendar.DAY_OF_MONTH);
        month = currentDate.get(Calendar.MONTH);
        year = currentDate.get(Calendar.YEAR);

        viewModel = ViewModelProviders.of(this).get(OneResultViewModel.class);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        setTitle(R.string.add_result);

        homeTeamsList = new ArrayList<>();
        homeTeamsList.add(new TeamItem("Home team?", R.drawable.empty_logo));

        guestTeamsList = new ArrayList<>();
        guestTeamsList.add(new TeamItem("Guest team?", R.drawable.empty_logo));

        final String[] listOfTeams = getApplication().getResources().getStringArray(R.array.arrayOfTeamNames);

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

        homeTeamsList.addAll(1, temp);
        guestTeamsList.addAll(1, temp);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
