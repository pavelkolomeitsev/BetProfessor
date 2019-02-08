package com.example.paul.betprofessor.ui;

import android.app.DatePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.button.MaterialButton;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paul.betprofessor.R;
import com.example.paul.betprofessor.model.OneResult;
import com.example.paul.betprofessor.ui.helpers.AutoCompleteAdapter;
import com.example.paul.betprofessor.ui.helpers.AutoCompleteTeamItem;
import com.example.paul.betprofessor.viewModel.OneResultViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EditResultActivity extends AppCompatActivity {

    private List<AutoCompleteTeamItem> teams;

    private TextView tvDate;
    private MaterialButton btnDate;
    private AutoCompleteTextView editTextHomeTeam, editTextGuestTeam;
    private TextInputEditText homeTeamSpread, homeLine, homeTeamTotal, homeTeamResult;
    private TextInputEditText guestTeamResult, guestTeamSpread, guestLine, guestTeamTotal;
    private FloatingActionButton fabEditResult;

    private AutoCompleteAdapter adapterHomeTeams, adapterGuestTeams;

    private OneResultViewModel viewModel;

    private String date, homeTeamName, homeSpread, guestTeamName, guestSpread, mHomeLine, mHomeTeamTotal, homeResult, mGuestLine, mGuestTeamTotal, guestResult;

    private Calendar currentDate;
    private int day, month, year;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_result);

        initAllElements();

        fillTeamList();

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(EditResultActivity.this
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

        adapterHomeTeams = new AutoCompleteAdapter(this, teams);
        editTextHomeTeam.setAdapter(adapterHomeTeams);

        adapterGuestTeams = new AutoCompleteAdapter(this, teams);
        editTextGuestTeam.setAdapter(adapterGuestTeams);

        fabEditResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (id == -1){
                    Toast.makeText(EditResultActivity.this, "Result can`t be updated", Toast.LENGTH_SHORT).show();
                    return;
                }

                date = tvDate.getText().toString();
                homeTeamName = editTextHomeTeam.getText().toString();
                mHomeTeamTotal = homeTeamTotal.getText().toString();
                homeSpread = homeTeamSpread.getText().toString();
                mHomeLine = homeLine.getText().toString();
                guestTeamName = editTextGuestTeam.getText().toString();
                mGuestTeamTotal = guestTeamTotal.getText().toString();
                guestSpread = guestTeamSpread.getText().toString();
                mGuestLine = guestLine.getText().toString();
                homeResult = homeTeamResult.getText().toString();
                guestResult = guestTeamResult.getText().toString();

                if (homeTeamName.isEmpty() || guestTeamName.isEmpty()){
                    Toast.makeText(EditResultActivity.this, "Teams` names have to be filled correctly", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (homeTeamName.equals(guestTeamName)){
                    Toast.makeText(EditResultActivity.this, "Teams` names have to be filled correctly", Toast.LENGTH_SHORT).show();
                    return;
                }

                OneResult editedResult = new OneResult(date, homeTeamName, mHomeTeamTotal, homeSpread, mHomeLine, guestTeamName
                        , mGuestTeamTotal, guestSpread, mGuestLine, homeResult, guestResult);

                editedResult.setId(id);

                viewModel.update(editedResult);

                finish();
            }
        });
    }

    private void initAllElements() {

        tvDate = findViewById(R.id.tv_edit_date);
        btnDate = findViewById(R.id.btn_edit_date);
        editTextHomeTeam = findViewById(R.id.home_team);
        editTextGuestTeam = findViewById(R.id.guest_team);
        homeTeamSpread = findViewById(R.id.home_team_edit_spread);
        homeLine = findViewById(R.id.home_edit_line);
        homeTeamTotal = findViewById(R.id.home_team_edit_total);
        homeTeamResult = findViewById(R.id.home_team_edit_result);
        guestTeamResult = findViewById(R.id.guest_team_edit_result);
        guestTeamSpread = findViewById(R.id.guest_team_edit_spread);
        guestLine = findViewById(R.id.guest_edit_line);
        guestTeamTotal = findViewById(R.id.guest_team_edit_total);
        fabEditResult = findViewById(R.id.fab_edit_result);

        currentDate = Calendar.getInstance();
        day = currentDate.get(Calendar.DAY_OF_MONTH);
        month = currentDate.get(Calendar.MONTH);
        year = currentDate.get(Calendar.YEAR);

        viewModel = ViewModelProviders.of(this).get(OneResultViewModel.class);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        setTitle(R.string.edit_result);

        Intent intent = getIntent();
        id = intent.getIntExtra(ResultActivity.KEY_ID, -1);
        tvDate.setText(intent.getStringExtra(ResultActivity.KEY_DATE));
        editTextHomeTeam.setText(intent.getStringExtra(ResultActivity.KEY_HOME_NAME));
        homeTeamSpread.setText(intent.getStringExtra(ResultActivity.KEY_HOME_HANDICAP));
        homeLine.setText(intent.getStringExtra(ResultActivity.KEY_HOME_LINE));
        homeTeamTotal.setText(intent.getStringExtra(ResultActivity.KEY_HOME_TEAM_TOTAL));
        homeTeamResult.setText(intent.getStringExtra(ResultActivity.KEY_HOME_RESULT));
        editTextGuestTeam.setText(intent.getStringExtra(ResultActivity.KEY_GUEST_TEAM_NAME));
        guestTeamResult.setText(intent.getStringExtra(ResultActivity.KEY_GUEST_RESULT));
        guestTeamSpread.setText(intent.getStringExtra(ResultActivity.KEY_GUEST_HANDICAP));
        guestLine.setText(intent.getStringExtra(ResultActivity.KEY_GUEST_LINE));
        guestTeamTotal.setText(intent.getStringExtra(ResultActivity.KEY_GUEST_TEAM_TOTAL));
    }

    private void fillTeamList() {

        final String[] listOfTeams = getApplication().getResources().getStringArray(R.array.arrayOfTeamNames);

        teams = new ArrayList<>();
        teams.add(new AutoCompleteTeamItem(listOfTeams[0], R.drawable.atlanta_hawks));
        teams.add(new AutoCompleteTeamItem(listOfTeams[1], R.drawable.boston_celtics));
        teams.add(new AutoCompleteTeamItem(listOfTeams[2], R.drawable.brooklyn_nets));
        teams.add(new AutoCompleteTeamItem(listOfTeams[3], R.drawable.charlotte_hornets));
        teams.add(new AutoCompleteTeamItem(listOfTeams[4], R.drawable.chicago_bulls));
        teams.add(new AutoCompleteTeamItem(listOfTeams[5], R.drawable.cleveland_cavaliers));
        teams.add(new AutoCompleteTeamItem(listOfTeams[6], R.drawable.dallas_mavericks));
        teams.add(new AutoCompleteTeamItem(listOfTeams[7], R.drawable.denver_nuggets));
        teams.add(new AutoCompleteTeamItem(listOfTeams[8], R.drawable.detroit_pistons));
        teams.add(new AutoCompleteTeamItem(listOfTeams[9], R.drawable.golden_state_warriors));
        teams.add(new AutoCompleteTeamItem(listOfTeams[10], R.drawable.houston_rockets));
        teams.add(new AutoCompleteTeamItem(listOfTeams[11], R.drawable.indiana_pacers));
        teams.add(new AutoCompleteTeamItem(listOfTeams[12], R.drawable.la_lakers));
        teams.add(new AutoCompleteTeamItem(listOfTeams[13], R.drawable.los_angeles_clippers));
        teams.add(new AutoCompleteTeamItem(listOfTeams[14], R.drawable.memphis_grizzlies));
        teams.add(new AutoCompleteTeamItem(listOfTeams[15], R.drawable.miami_heat));
        teams.add(new AutoCompleteTeamItem(listOfTeams[16], R.drawable.milwaukee_bucks));
        teams.add(new AutoCompleteTeamItem(listOfTeams[17], R.drawable.minnesota_timberwolves));
        teams.add(new AutoCompleteTeamItem(listOfTeams[18], R.drawable.new_orleans_pelicans));
        teams.add(new AutoCompleteTeamItem(listOfTeams[19], R.drawable.new_york_knicks));
        teams.add(new AutoCompleteTeamItem(listOfTeams[20], R.drawable.oklahoma_city_thunder));
        teams.add(new AutoCompleteTeamItem(listOfTeams[21], R.drawable.orlando_magic));
        teams.add(new AutoCompleteTeamItem(listOfTeams[22], R.drawable.philadelphia_76ers));
        teams.add(new AutoCompleteTeamItem(listOfTeams[23], R.drawable.phoenix_suns));
        teams.add(new AutoCompleteTeamItem(listOfTeams[24], R.drawable.portland_trail_blazers));
        teams.add(new AutoCompleteTeamItem(listOfTeams[25], R.drawable.sacramento_kings));
        teams.add(new AutoCompleteTeamItem(listOfTeams[26], R.drawable.san_antonio_spurs));
        teams.add(new AutoCompleteTeamItem(listOfTeams[27], R.drawable.toronto_raptors));
        teams.add(new AutoCompleteTeamItem(listOfTeams[28], R.drawable.utah_jazz));
        teams.add(new AutoCompleteTeamItem(listOfTeams[29], R.drawable.washington_wizards));
    }
}
