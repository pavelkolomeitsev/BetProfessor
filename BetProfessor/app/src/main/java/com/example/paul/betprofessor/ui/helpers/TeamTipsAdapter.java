package com.example.paul.betprofessor.ui.helpers;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.paul.betprofessor.R;

import java.util.ArrayList;

public class TeamTipsAdapter extends RecyclerView.Adapter<TeamTipsAdapter.TeamTipsViewHolder>{

    private ArrayList<TeamTipsItem> mList;
    private onTipsTeamItemClickListener listener;

    public class TeamTipsViewHolder extends RecyclerView.ViewHolder {

        public ImageView teamLogo;
        public TextView teamName;
        public TextView winPercentage;

        public TeamTipsViewHolder(@NonNull View itemView) {
            super(itemView);

            teamLogo = itemView.findViewById(R.id.iv_team_tips);
            teamName = itemView.findViewById(R.id.tv_team_name_tips);
            winPercentage = itemView.findViewById(R.id.tv_team_win_percentage);

            // here we implement a click on tipsTeamItem
            // by helping a reference on a special interface
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(mList.get(position));
                    }
                }
            });
        }
    }

    public TeamTipsAdapter(ArrayList<TeamTipsItem> listOfTeams) {

        mList = listOfTeams;
    }

    @NonNull
    @Override
    public TeamTipsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.one_tip_card_view, viewGroup, false);
        TeamTipsViewHolder viewHolder = new TeamTipsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TeamTipsViewHolder viewHolder, int i) {

        TeamTipsItem currentItem = mList.get(i);

        viewHolder.teamLogo.setImageResource(currentItem.getImageResource());
        viewHolder.teamName.setText(currentItem.getTeamName());
        viewHolder.winPercentage.setText(currentItem.getWinPercentage().toString());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    // it`s a special interface for handling a click on tipsTeamItem
    public interface onTipsTeamItemClickListener {
        void onItemClick(TeamTipsItem teamTipsItem);
    }

    // it`s a function which takes as a param a reference on a special listener
    public void setOnItemClickListener(onTipsTeamItemClickListener listener) {
        this.listener = listener;
    }
}
