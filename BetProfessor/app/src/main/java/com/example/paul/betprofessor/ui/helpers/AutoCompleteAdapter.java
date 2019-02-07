package com.example.paul.betprofessor.ui.helpers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.paul.betprofessor.R;

import java.util.ArrayList;
import java.util.List;

public class AutoCompleteAdapter extends ArrayAdapter {

    private List<AutoCompleteTeamItem> list;

    public AutoCompleteAdapter(@NonNull Context context, @NonNull List<AutoCompleteTeamItem> teamList) {
        super(context, 0, teamList);
        list = new ArrayList<>(teamList);
    }

    public Filter getFilter(){
        return teamFilter;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.spinner_team, parent, false);
        }

        TextView textView = convertView.findViewById(R.id.team_name);
        ImageView logoImage = convertView.findViewById(R.id.team_logo);

        AutoCompleteTeamItem autoCompleteTeamItem = (AutoCompleteTeamItem) getItem(position);

        if (autoCompleteTeamItem != null){
            textView.setText(autoCompleteTeamItem.getTeamName());
            logoImage.setImageResource(autoCompleteTeamItem.getLogoImage());
        }

        return convertView;
    }

    private Filter teamFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults results = new FilterResults();
            List<AutoCompleteTeamItem> suggestions = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0 ){
                suggestions.addAll(list);
            }else {
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for (AutoCompleteTeamItem item : list){
                    if (item.getTeamName().toLowerCase().contains(filterPattern)){
                        suggestions.add(item);
                    }
                }
            }

            results.values = suggestions;
            results.count = suggestions.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

            clear();
            addAll((List)filterResults.values);
            notifyDataSetChanged();
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((AutoCompleteTeamItem) resultValue).getTeamName();
        }
    };
}
