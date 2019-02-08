package com.example.paul.betprofessor.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.paul.betprofessor.R;
import com.example.paul.betprofessor.model.OneResult;
import com.example.paul.betprofessor.ui.helpers.DialogOnDeleteAll;
import com.example.paul.betprofessor.ui.helpers.OneResultAdapter;
import com.example.paul.betprofessor.viewModel.OneResultViewModel;

import java.util.List;

public class ResultActivity extends AppCompatActivity implements DialogOnDeleteAll.DialogOnDeleteAllListener {

    public static final String KEY_ID = "betprofessor.KEY_ID";
    public static final String KEY_DATE = "betprofessor.KEY_DATE";
    public static final String KEY_HOME_NAME = "betprofessor.KEY_HOME_NAME";
    public static final String KEY_HOME_TEAM_TOTAL = "betprofessor.KEY_HOME_TEAM_TOTAL";
    public static final String KEY_HOME_LINE = "betprofessor.HOME_LINE";
    public static final String KEY_HOME_HANDICAP = "betprofessor.HOME_HANDICAP";
    public static final String KEY_HOME_RESULT = "betprofessor.HOME_RESULT";
    public static final String KEY_GUEST_TEAM_NAME = "betprofessor.GUEST_TEAM_NAME";
    public static final String KEY_GUEST_TEAM_TOTAL = "betprofessor.GUEST_TEAM_TOTAL";
    public static final String KEY_GUEST_LINE = "betprofessor.GUEST_LINE";
    public static final String KEY_GUEST_HANDICAP = "betprofessor.GUEST_HANDICAP";
    public static final String KEY_GUEST_RESULT = "betprofessor.GUEST_RESULT";

    private CoordinatorLayout rootLayout;

    private RecyclerView recyclerView;
    private OneResultAdapter adapter;

    private FloatingActionButton fab;

    private OneResultViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        setTitle(R.string.result_activity_title);

        onInitialize();

        onSetValues();
    }

    private void onInitialize() {

        rootLayout = findViewById(R.id.activity_result);
        recyclerView = findViewById(R.id.recycler_view_result);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new OneResultAdapter();
        recyclerView.setAdapter(adapter);

        // assign it to exact activity and indicate the class of our ViewModel
        viewModel = ViewModelProviders.of(this).get(OneResultViewModel.class);
        viewModel.getLastTwentyResults().observe(this, new Observer<List<OneResult>>() {
            @Override
            public void onChanged(@Nullable List<OneResult> list) {
                // update RecyclerView
                adapter.submitList(list);
            }
        });

        fab = findViewById(R.id.fab_add_result);
    }

    private void onSetValues() {

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivity.this, AddResultActivity.class);
                startActivity(intent);
            }
        });


        // swipe recyclerView`s item to the right and delete it
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

                OneResult recoveredResult = adapter.getResultAtPosition(viewHolder.getAdapterPosition());
                final String date = recoveredResult.getDate();
                final String homeTeamName = recoveredResult.getHomeTeamName();
                final String homeHandicap = recoveredResult.getHomeHandicap();
                final String homeLine = recoveredResult.getHomeLine();
                final String homeTeamTotal = recoveredResult.getHomeTeamTotal();
                final String homeResult = recoveredResult.getHomeResult();
                final String guestTeamName = recoveredResult.getGuestTeamName();
                final String guestHandicap = recoveredResult.getGuestHandicap();
                final String guestLine = recoveredResult.getGuestLine();
                final String guestTeamTotal = recoveredResult.getGuestTeamTotal();
                final String guestResult = recoveredResult.getGuestResult();

                viewModel.delete(adapter.getResultAtPosition(viewHolder.getAdapterPosition()));
                Snackbar snackbar = Snackbar.make(rootLayout, R.string.result_deleted, Snackbar.LENGTH_LONG);
                snackbar.setAction(R.string.undo_operation, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        viewModel.insert(new OneResult(date, homeTeamName, homeTeamTotal, homeHandicap, homeLine
                                , guestTeamName, guestTeamTotal, guestHandicap, guestLine, homeResult, guestResult));
                        Snackbar.make(rootLayout, R.string.result_recovered, Snackbar.LENGTH_SHORT).show();
                    }
                });
                snackbar.show();
            }
        }).attachToRecyclerView(recyclerView);


        // implement a click on item with results
        adapter.setOnItemClickListener(new OneResultAdapter.onItemClickListener() {
            @Override
            public void onItemClick(OneResult oneResult) {
                Intent intent = new Intent(ResultActivity.this, EditResultActivity.class);
                intent.putExtra(KEY_ID, oneResult.getId());
                intent.putExtra(KEY_DATE, oneResult.getDate());
                intent.putExtra(KEY_HOME_NAME, oneResult.getHomeTeamName());
                intent.putExtra(KEY_HOME_HANDICAP, oneResult.getHomeHandicap());
                intent.putExtra(KEY_HOME_LINE, oneResult.getHomeLine());
                intent.putExtra(KEY_HOME_TEAM_TOTAL, oneResult.getHomeTeamTotal());
                intent.putExtra(KEY_HOME_RESULT, oneResult.getHomeResult());
                intent.putExtra(KEY_GUEST_TEAM_NAME, oneResult.getGuestTeamName());
                intent.putExtra(KEY_GUEST_HANDICAP, oneResult.getGuestHandicap());
                intent.putExtra(KEY_GUEST_LINE, oneResult.getGuestLine());
                intent.putExtra(KEY_GUEST_TEAM_TOTAL, oneResult.getGuestTeamTotal());
                intent.putExtra(KEY_GUEST_RESULT, oneResult.getGuestResult());
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.result_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.delete_all_results:
                DialogOnDeleteAll dialog = new DialogOnDeleteAll();
                dialog.show(getSupportFragmentManager(), "Dialog on delete all results");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onYesClickedButton() {
        viewModel.deleteAllResults();
    }
}
