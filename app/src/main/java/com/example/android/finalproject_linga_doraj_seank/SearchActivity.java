package com.example.android.finalproject_linga_doraj_seank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;

import java.util.ArrayList;
import java.util.List;


public class SearchActivity extends AppCompatActivity {
    private List<Topics> topicsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Intent intent = getIntent();


        fillCountryList();
        final AutoCompleteTextView editText = findViewById(R.id.actv);

        AutoCompleteTopicsAdapter adapter = new AutoCompleteTopicsAdapter(this,topicsList);
        editText.setAdapter(adapter);

        editText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(SearchActivity.this, MakePencilBoxActivity.class);
                startActivity(i);
            }
        });


    }

    private void fillCountryList() {
        topicsList = new ArrayList<>();
        topicsList.add(new Topics("Making A Cat Tree", R.drawable.cattree));
        topicsList.add(new Topics("Cleaning A Burnt Pan", R.drawable.cleanburntpan));
        topicsList.add(new Topics("Fixing A Clogged Sink", R.drawable.fixcloggedsink));
        topicsList.add(new Topics("Building A Deck", R.drawable.builddeck));
        topicsList.add(new Topics("Changing Door Handlers", R.drawable.fixdoorhandler));
        topicsList.add(new Topics("Updating Kitchen CounterTop", R.drawable.countertop));
        topicsList.add(new Topics("Installing SmartBidet Toilet Seat Cover", R.drawable.smartbidet));
        topicsList.add(new Topics("Installing iSpring Under Sink Water Filtration System", R.drawable.ispringwaterfilter));
        topicsList.add(new Topics("Replacing iPhone 7 Plus Screen", R.drawable.screeniphone));
        topicsList.add(new Topics("DIY Clothes Drying Rack", R.drawable.dryingrack));
        topicsList.add(new Topics("DIY Pencil Box", R.drawable.pencilbox));


    }
}
