package com.example.paul.betprofessor.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.paul.betprofessor.R;
import com.example.paul.betprofessor.ui.helpers.TeamTipsAdapter;
import com.example.paul.betprofessor.ui.helpers.TeamTipsItem;
import com.example.paul.betprofessor.viewModel.MainViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_TEAM_NAME = "betprofessor.KEY_TEAM_NAME";

    private String[] arrayOfTeamNames;
    private RecyclerView recyclerView;
    private TeamTipsAdapter adapter;

    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayOfTeamNames = getResources().getStringArray(R.array.arrayOfTeamNames);

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        recyclerView = findViewById(R.id.recycler_view_tips);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();

        adapter = new TeamTipsAdapter(getListOfTeamTips());
        recyclerView.setAdapter(adapter);

        // implement a click on tipsTeamItem
        adapter.setOnItemClickListener(new TeamTipsAdapter.onTipsTeamItemClickListener() {
            @Override
            public void onItemClick(TeamTipsItem teamTipsItem) {
                Intent intent = new Intent(MainActivity.this, TeamTipsActivity.class);
                intent.putExtra(KEY_TEAM_NAME, teamTipsItem.getTeamName());
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent;

        switch (item.getItemId()) {
            case R.id.search:
                intent = new Intent(MainActivity.this, SearchForTeamActivity.class);
                startActivity(intent);
                return true;
            case R.id.results:
                intent = new Intent(MainActivity.this, ResultActivity.class);
                startActivity(intent);
                return true;
            case R.id.input_statistic:
                intent = new Intent(MainActivity.this, InputStatisticActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private ArrayList<TeamTipsItem> getListOfTeamTips(){

        ArrayList<TeamTipsItem> listOfTeamTips = new ArrayList<>();

        listOfTeamTips.add(new TeamTipsItem(R.drawable.atlanta_hawks, arrayOfTeamNames[0], viewModel.getWinPercentageOfTeam(arrayOfTeamNames[0])));
        listOfTeamTips.add(new TeamTipsItem(R.drawable.boston_celtics, arrayOfTeamNames[1], viewModel.getWinPercentageOfTeam(arrayOfTeamNames[1])));
        listOfTeamTips.add(new TeamTipsItem(R.drawable.brooklyn_nets, arrayOfTeamNames[2], viewModel.getWinPercentageOfTeam(arrayOfTeamNames[2])));
        listOfTeamTips.add(new TeamTipsItem(R.drawable.charlotte_hornets, arrayOfTeamNames[3], viewModel.getWinPercentageOfTeam(arrayOfTeamNames[3])));
        listOfTeamTips.add(new TeamTipsItem(R.drawable.chicago_bulls, arrayOfTeamNames[4], viewModel.getWinPercentageOfTeam(arrayOfTeamNames[4])));
        listOfTeamTips.add(new TeamTipsItem(R.drawable.cleveland_cavaliers, arrayOfTeamNames[5], viewModel.getWinPercentageOfTeam(arrayOfTeamNames[5])));
        listOfTeamTips.add(new TeamTipsItem(R.drawable.dallas_mavericks, arrayOfTeamNames[6], viewModel.getWinPercentageOfTeam(arrayOfTeamNames[6])));
        listOfTeamTips.add(new TeamTipsItem(R.drawable.denver_nuggets, arrayOfTeamNames[7], viewModel.getWinPercentageOfTeam(arrayOfTeamNames[7])));
        listOfTeamTips.add(new TeamTipsItem(R.drawable.detroit_pistons, arrayOfTeamNames[8], viewModel.getWinPercentageOfTeam(arrayOfTeamNames[8])));
        listOfTeamTips.add(new TeamTipsItem(R.drawable.golden_state_warriors, arrayOfTeamNames[9], viewModel.getWinPercentageOfTeam(arrayOfTeamNames[9])));
        listOfTeamTips.add(new TeamTipsItem(R.drawable.houston_rockets, arrayOfTeamNames[10], viewModel.getWinPercentageOfTeam(arrayOfTeamNames[10])));
        listOfTeamTips.add(new TeamTipsItem(R.drawable.indiana_pacers, arrayOfTeamNames[11], viewModel.getWinPercentageOfTeam(arrayOfTeamNames[11])));
        listOfTeamTips.add(new TeamTipsItem(R.drawable.la_lakers, arrayOfTeamNames[12], viewModel.getWinPercentageOfTeam(arrayOfTeamNames[12])));
        listOfTeamTips.add(new TeamTipsItem(R.drawable.los_angeles_clippers, arrayOfTeamNames[13], viewModel.getWinPercentageOfTeam(arrayOfTeamNames[13])));
        listOfTeamTips.add(new TeamTipsItem(R.drawable.memphis_grizzlies, arrayOfTeamNames[14], viewModel.getWinPercentageOfTeam(arrayOfTeamNames[14])));
        listOfTeamTips.add(new TeamTipsItem(R.drawable.miami_heat, arrayOfTeamNames[15], viewModel.getWinPercentageOfTeam(arrayOfTeamNames[15])));
        listOfTeamTips.add(new TeamTipsItem(R.drawable.milwaukee_bucks, arrayOfTeamNames[16], viewModel.getWinPercentageOfTeam(arrayOfTeamNames[16])));
        listOfTeamTips.add(new TeamTipsItem(R.drawable.minnesota_timberwolves, arrayOfTeamNames[17], viewModel.getWinPercentageOfTeam(arrayOfTeamNames[17])));
        listOfTeamTips.add(new TeamTipsItem(R.drawable.new_orleans_pelicans, arrayOfTeamNames[18], viewModel.getWinPercentageOfTeam(arrayOfTeamNames[18])));
        listOfTeamTips.add(new TeamTipsItem(R.drawable.new_york_knicks, arrayOfTeamNames[19], viewModel.getWinPercentageOfTeam(arrayOfTeamNames[19])));
        listOfTeamTips.add(new TeamTipsItem(R.drawable.oklahoma_city_thunder, arrayOfTeamNames[20], viewModel.getWinPercentageOfTeam(arrayOfTeamNames[20])));
        listOfTeamTips.add(new TeamTipsItem(R.drawable.orlando_magic, arrayOfTeamNames[21], viewModel.getWinPercentageOfTeam(arrayOfTeamNames[21])));
        listOfTeamTips.add(new TeamTipsItem(R.drawable.philadelphia_76ers, arrayOfTeamNames[22], viewModel.getWinPercentageOfTeam(arrayOfTeamNames[22])));
        listOfTeamTips.add(new TeamTipsItem(R.drawable.phoenix_suns, arrayOfTeamNames[23], viewModel.getWinPercentageOfTeam(arrayOfTeamNames[23])));
        listOfTeamTips.add(new TeamTipsItem(R.drawable.portland_trail_blazers, arrayOfTeamNames[24], viewModel.getWinPercentageOfTeam(arrayOfTeamNames[24])));
        listOfTeamTips.add(new TeamTipsItem(R.drawable.sacramento_kings, arrayOfTeamNames[25], viewModel.getWinPercentageOfTeam(arrayOfTeamNames[25])));
        listOfTeamTips.add(new TeamTipsItem(R.drawable.san_antonio_spurs, arrayOfTeamNames[26], viewModel.getWinPercentageOfTeam(arrayOfTeamNames[26])));
        listOfTeamTips.add(new TeamTipsItem(R.drawable.toronto_raptors, arrayOfTeamNames[27], viewModel.getWinPercentageOfTeam(arrayOfTeamNames[27])));
        listOfTeamTips.add(new TeamTipsItem(R.drawable.utah_jazz, arrayOfTeamNames[28], viewModel.getWinPercentageOfTeam(arrayOfTeamNames[28])));
        listOfTeamTips.add(new TeamTipsItem(R.drawable.washington_wizards, arrayOfTeamNames[29], viewModel.getWinPercentageOfTeam(arrayOfTeamNames[29])));

        return listOfTeamTips;
    }
}
