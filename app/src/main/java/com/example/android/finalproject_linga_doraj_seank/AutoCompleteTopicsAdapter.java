package com.example.android.finalproject_linga_doraj_seank;

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

import com.example.android.finalproject_linga_doraj_seank.R;
import com.example.android.finalproject_linga_doraj_seank.Topics;

import java.util.ArrayList;
import java.util.List;

public class AutoCompleteTopicsAdapter extends ArrayAdapter<Topics> {
    private List<Topics> topicsListFull;

    public AutoCompleteTopicsAdapter(@NonNull Context context, @NonNull List<Topics> topicsList) {
        super(context, 0, topicsList);
        topicsListFull = new ArrayList<>(topicsList);
    }
    @NonNull
    @Override
    public Filter getFilter() {
        return countryFilter;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.autocomplete_row, parent, false
            );
        }

        TextView textViewName = convertView.findViewById(R.id.text_view_name);
        ImageView imageViewFlag = convertView.findViewById(R.id.image_view_flag);

        Topics topics = getItem(position);

        if (topics != null) {
            textViewName.setText(topics.getTopicName());
            imageViewFlag.setImageResource(topics.getFlagImage());
        }

        return convertView;
    }

    private Filter countryFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<Topics> suggestions = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                suggestions.addAll(topicsListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Topics topics : topicsListFull) {
                    if (topics.getTopicName().toLowerCase().contains(filterPattern)) {
                        suggestions.add(topics);
                    }
                }
            }

            results.values = suggestions;
            results.count = suggestions.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            clear();
            addAll((List) results.values);
            notifyDataSetChanged();
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((Topics) resultValue).getTopicName();
        }
    };

}

