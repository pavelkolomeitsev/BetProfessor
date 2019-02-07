package com.example.paul.betprofessor.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.paul.betprofessor.R;
import com.example.paul.betprofessor.ui.helpers.TeamTipsAdapter;
import com.example.paul.betprofessor.viewModel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MyLog";
    public static final String KEY_TEAM_NAME = "betprofessor.KEY_TEAM_NAME";

    private RecyclerView recyclerView;
    private TeamTipsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.search:
                Toast.makeText(this, "search was clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.results:
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                startActivity(intent);
                return true;
            case R.id.input_statistic:
                Toast.makeText(this, "statistic was clicked", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
