package com.example.android.finalproject_linga_doraj_seank;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Project> projects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SearchFragment()).commit();

        initialData();
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ProjectsAdapter(projects, this));

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment = null;

            switch (menuItem.getItemId()){
                case R.id.nav_search:
                    selectedFragment = new SearchFragment();
                    break;
                case R.id.nav_community:
                    selectedFragment = new CommunityFragment();
                    break;
                case R.id.nav_nearby:
                    selectedFragment = new NearbyFragment();
                    break;
                case R.id.nav_more:
                    selectedFragment = new MoreFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
            return true;
        }
    };

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
