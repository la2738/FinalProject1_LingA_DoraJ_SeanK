package com.example.android.finalproject_linga_doraj_seank;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import android.widget.AutoCompleteTextView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;

public class SearchFragment extends Fragment {
    private SearchView searchView;

    private List<Project> projects;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);

        initialData();

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new ProjectsAdapter(projects, getContext()));



        searchView = (SearchView)rootView.findViewById(R.id.search_view);
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),SearchActivity.class);
                startActivity(intent);
            }
        });
        return rootView;
    }


    private void initialData(){
        projects = new ArrayList<>();
        projects.add(new Project(R.string.counter_top_name,R.string.level_a1, R.string.counter_top_time,R.string.rating_4, R.drawable.countertop));
        projects.add(new Project(R.string.ispring_name, R.string.level_i3, R.string.ispring_time, R.string.rating_5,R.drawable.ispringwaterfilter));
        projects.add(new Project(R.string.smart_bidet_name, R.string.level_i1, R.string.smart_bidet_time,R.string.rating_4, R.drawable.smartbidet));
        projects.add(new Project(R.string.screen_iphone_name, R.string.level_b2, R.string.screen_iphone_time,R.string.rating_5, R.drawable.screeniphone));
        projects.add(new Project(R.string.drying_rack_name, R.string.level_m1, R.string.drying_rack_time,R.string.rating_5, R.drawable.dryingrack));
        projects.add(new Project(R.string.pencil_box_name, R.string.level_b1, R.string.pencil_box_time,R.string.rating_4, R.drawable.pencilbox));
    }


}
