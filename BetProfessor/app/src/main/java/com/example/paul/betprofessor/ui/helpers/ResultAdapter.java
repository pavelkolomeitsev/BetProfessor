package com.example.paul.betprofessor.ui.helpers;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.paul.betprofessor.R;
import com.example.paul.betprofessor.model.OneResult;

public class ResultAdapter extends ListAdapter<OneResult, ResultAdapter.ViewHolder> {

    private onItemClickListener listener;

    // for nice animation we use ListAdapter class
    public ResultAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<OneResult> DIFF_CALLBACK = new DiffUtil.ItemCallback<OneResult>() {

        // oldItem - from the old list, newItem - from the new list
        // in this way animation happened
        @Override
        public boolean areItemsTheSame(@NonNull OneResult oldItem, @NonNull OneResult newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull OneResult oldItem, @NonNull OneResult newItem) {
            return oldItem.getDate().equals(newItem.getDate()) &&
                    oldItem.getHomeTeamName().equals(newItem.getHomeTeamName()) &&
                    oldItem.getHomeHandicap().equals(newItem.getHomeHandicap()) &&
                    oldItem.getHomeLine().equals(newItem.getHomeLine()) &&
                    oldItem.getHomeTeamTotal().equals(newItem.getHomeTeamTotal()) &&
                    oldItem.getHomeResult().equals(newItem.getHomeResult()) &&
                    oldItem.getGuestTeamName().equals(newItem.getGuestTeamName()) &&
                    oldItem.getGuestHandicap().equals(newItem.getGuestHandicap()) &&
                    oldItem.getGuestLine().equals(newItem.getGuestLine()) &&
                    oldItem.getGuestTeamTotal().equals(newItem.getGuestTeamTotal()) &&
                    oldItem.getGuestResult().equals(newItem.getGuestResult());
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.one_result, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        OneResult currentResult = getItem(i);

        viewHolder.date.setText(currentResult.getDate());
        viewHolder.homeTeamName.setText(currentResult.getHomeTeamName());
        viewHolder.homeTeamTotal.setText(String.valueOf(currentResult.getHomeTeamTotal()));
        viewHolder.homeLine.setText(String.valueOf(currentResult.getHomeLine()));
        viewHolder.homeHandicap.setText(currentResult.getHomeHandicap());
        viewHolder.homeResult.setText(String.valueOf(currentResult.getHomeResult()));
        viewHolder.guestTeamName.setText(currentResult.getGuestTeamName());
        viewHolder.guestTeamTotal.setText(String.valueOf(currentResult.getGuestTeamTotal()));
        viewHolder.guestLine.setText(String.valueOf(currentResult.getGuestLine()));
        viewHolder.guestHandicap.setText(currentResult.getGuestHandicap());
        viewHolder.guestResult.setText(String.valueOf(currentResult.getGuestResult()));

        if ((!currentResult.getHomeResult().isEmpty()) && (!currentResult.getGuestResult().isEmpty())) {

            if ((Double.parseDouble(currentResult.getHomeResult()) + Double.parseDouble(currentResult.getHomeHandicap())) >
                    Double.parseDouble(currentResult.getGuestResult())) {
                viewHolder.homeHandicap.setTextColor(Color.GREEN);
                viewHolder.guestHandicap.setTextColor(Color.RED);
            } else {
                viewHolder.homeHandicap.setTextColor(Color.RED);
                viewHolder.guestHandicap.setTextColor(Color.GREEN);
            }
        }
    }

    public OneResult getResultAtPosition(int position) {
        return getItem(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

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

        public ViewHolder(@NonNull View itemView) {
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // here we implement a click on recyclerview`s item
                    // by helping a reference on a special interface
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));  // we pass exact item of result to a listener
                    }
                }
            });
        }
    }

    // it`s a special interface for handling a click on recyclerview`s item
    public interface onItemClickListener {

        void onItemClick(OneResult oneResult);
    }

    // it`s a function which takes as a param a reference on a special listener
    public void setOnItemClickListener(onItemClickListener listener) {
        this.listener = listener; // assign a listener to method
    }
}
