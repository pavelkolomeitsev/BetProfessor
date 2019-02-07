package com.example.paul.betprofessor.ui.helpers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.paul.betprofessor.R;

import java.util.ArrayList;

public class TeamAdapter extends ArrayAdapter<TeamItem> {

    public TeamAdapter(Context context, ArrayList<TeamItem> teamList){
        super(context, 0, teamList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent){
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.spinner_team, parent, false);
        }

        ImageView teamLogo = convertView.findViewById(R.id.team_logo);
        TextView teamName = convertView.findViewById(R.id.team_name);

        TeamItem teamItem = getItem(position);

        if (teamItem != null){
            teamLogo.setImageResource(teamItem.getLogoImage());
            teamName.setText(teamItem.getTeamName());
        }

        return convertView;
    }

}
