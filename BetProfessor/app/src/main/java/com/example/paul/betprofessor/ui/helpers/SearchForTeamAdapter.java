package com.example.paul.betprofessor.ui.helpers;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.paul.betprofessor.R;
import com.example.paul.betprofessor.model.OneResult;

import java.util.ArrayList;
import java.util.List;

public class SearchForTeamAdapter extends RecyclerView.Adapter<SearchForTeamAdapter.SearchViewHolder> {

    private List<OneResult> listOfAllTeamGames = new ArrayList<>();

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.one_result, viewGroup, false);

        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder searchViewHolder, int i) {

        OneResult currentResult = listOfAllTeamGames.get(i);

        searchViewHolder.date.setText(currentResult.getDate());
        searchViewHolder.homeTeamLogo.setImageResource(setRightImage(currentResult.getHomeTeamName()));
        searchViewHolder.homeTeamName.setText(currentResult.getHomeTeamName());
        searchViewHolder.homeTeamTotal.setText(String.valueOf(currentResult.getHomeTeamTotal()));
        searchViewHolder.homeLine.setText(String.valueOf(currentResult.getHomeLine()));
        searchViewHolder.homeHandicap.setText(currentResult.getHomeHandicap());
        searchViewHolder.homeResult.setText(String.valueOf(currentResult.getHomeResult()));
        searchViewHolder.guestTeamLogo.setImageResource(setRightImage(currentResult.getGuestTeamName()));
        searchViewHolder.guestTeamName.setText(currentResult.getGuestTeamName());
        searchViewHolder.guestTeamTotal.setText(String.valueOf(currentResult.getGuestTeamTotal()));
        searchViewHolder.guestLine.setText(String.valueOf(currentResult.getGuestLine()));
        searchViewHolder.guestHandicap.setText(currentResult.getGuestHandicap());
        searchViewHolder.guestResult.setText(String.valueOf(currentResult.getGuestResult()));

        if ((!currentResult.getHomeResult().isEmpty()) && (!currentResult.getGuestResult().isEmpty())) {

            if ((Double.parseDouble(currentResult.getHomeResult()) + Double.parseDouble(currentResult.getHomeHandicap())) >
                    Double.parseDouble(currentResult.getGuestResult())) {
                searchViewHolder.homeHandicap.setTextColor(Color.GREEN);
                searchViewHolder.guestHandicap.setTextColor(Color.RED);
            } else {
                searchViewHolder.homeHandicap.setTextColor(Color.RED);
                searchViewHolder.guestHandicap.setTextColor(Color.GREEN);
            }

            if ((!currentResult.getHomeTeamTotal().isEmpty()) && (!currentResult.getGuestTeamTotal().isEmpty())){

                if((Double.parseDouble(currentResult.getHomeResult()) > (Double.parseDouble(currentResult.getHomeTeamTotal())))){
                    searchViewHolder.homeResult.setTextColor(Color.GREEN);
                }else searchViewHolder.homeResult.setTextColor(Color.RED);

                if ((Double.parseDouble(currentResult.getGuestResult()) > (Double.parseDouble(currentResult.getGuestTeamTotal())))){
                    searchViewHolder.guestResult.setTextColor(Color.GREEN);
                }else searchViewHolder.guestResult.setTextColor(Color.RED);
            }
        }
    }

    private int setRightImage(String teamName) {

        int imageId = R.drawable.empty_logo;

        switch (teamName){

            case "Atlanta Hawks":
                imageId = R.drawable.atlanta_hawks;
                break;
            case "Boston Celtics":
                imageId = R.drawable.boston_celtics;
                break;
            case "Brooklyn Nets":
                imageId = R.drawable.brooklyn_nets;
                break;
            case "Charlotte Hornets":
                imageId = R.drawable.charlotte_hornets;
                break;
            case "Chicago Bulls":
                imageId = R.drawable.chicago_bulls;
                break;
            case "Cleveland Cavaliers":
                imageId = R.drawable.cleveland_cavaliers;
                break;
            case "Dallas Mavericks":
                imageId = R.drawable.dallas_mavericks;
                break;
            case "Denver Nuggets":
                imageId = R.drawable.denver_nuggets;
                break;
            case "Detroit Pistons":
                imageId = R.drawable.detroit_pistons;
                break;
            case "Golden State Warriors":
                imageId = R.drawable.golden_state_warriors;
                break;
            case "Houston Rockets":
                imageId = R.drawable.houston_rockets;
                break;
            case "Indiana Pacers":
                imageId = R.drawable.indiana_pacers;
                break;
            case "Los Angeles Lakers":
                imageId = R.drawable.la_lakers;
                break;
            case "Los Angeles Clippers":
                imageId = R.drawable.los_angeles_clippers;
                break;
            case "Memphis Grizzlies":
                imageId = R.drawable.memphis_grizzlies;
                break;
            case "Miami Heat":
                imageId = R.drawable.miami_heat;
                break;
            case "Milwaukee Bucks":
                imageId = R.drawable.milwaukee_bucks;
                break;
            case "Minnesota Timberwolves":
                imageId = R.drawable.minnesota_timberwolves;
                break;
            case "New Orleans Pelicans":
                imageId = R.drawable.new_orleans_pelicans;
                break;
            case "New York Knicks":
                imageId = R.drawable.new_york_knicks;
                break;
            case "Oklahoma City Thunder":
                imageId = R.drawable.oklahoma_city_thunder;
                break;
            case "Orlando Magic":
                imageId = R.drawable.orlando_magic;
                break;
            case "Philadelphia 76ers":
                imageId = R.drawable.philadelphia_76ers;
                break;
            case "Phoenix Suns":
                imageId = R.drawable.phoenix_suns;
                break;
            case "Portland Trail Blazers":
                imageId = R.drawable.portland_trail_blazers;
                break;
            case "Sacramento Kings":
                imageId = R.drawable.sacramento_kings;
                break;
            case "San Antonio Spurs":
                imageId = R.drawable.san_antonio_spurs;
                break;
            case "Toronto Raptors":
                imageId = R.drawable.toronto_raptors;
                break;
            case "Utah Jazz":
                imageId = R.drawable.utah_jazz;
                break;
            case "Washington Wizards":
                imageId = R.drawable.washington_wizards;
                break;
        }

        return imageId;
    }

    @Override
    public int getItemCount() {
        return listOfAllTeamGames.size();
    }

    public void setListOfAllTeamGames(List<OneResult> list){
        listOfAllTeamGames = list;
        notifyDataSetChanged();
    }

    class SearchViewHolder extends RecyclerView.ViewHolder{

        public TextView date;
        public ImageView homeTeamLogo;
        public TextView homeTeamName;
        public TextView homeTeamTotal;
        public TextView homeLine;
        public TextView homeHandicap;
        public TextView homeResult;
        public ImageView guestTeamLogo;
        public TextView guestTeamName;
        public TextView guestTeamTotal;
        public TextView guestLine;
        public TextView guestHandicap;
        public TextView guestResult;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.tv_date);
            homeTeamLogo = itemView.findViewById(R.id.iv_home_team);
            homeTeamName = itemView.findViewById(R.id.home_team_name);
            homeTeamTotal = itemView.findViewById(R.id.home_team_total);
            homeLine = itemView.findViewById(R.id.home_line);
            homeHandicap = itemView.findViewById(R.id.home_handicap);
            homeResult = itemView.findViewById(R.id.home_result);
            guestTeamLogo = itemView.findViewById(R.id.iv_guest_team);
            guestTeamName = itemView.findViewById(R.id.guest_team_name);
            guestTeamTotal = itemView.findViewById(R.id.guest_team_total);
            guestLine = itemView.findViewById(R.id.guest_line);
            guestHandicap = itemView.findViewById(R.id.guest_handicap);
            guestResult = itemView.findViewById(R.id.guest_result);
        }
    }
}
