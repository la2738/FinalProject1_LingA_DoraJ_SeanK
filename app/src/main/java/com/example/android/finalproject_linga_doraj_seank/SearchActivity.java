package com.example.android.finalproject_linga_doraj_seank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        AutoCompleteTextView editText = findViewById(R.id.actv);

        AutoCompleteTopicsAdapter adapter = new AutoCompleteTopicsAdapter(this,topicsList);
        editText.setAdapter(adapter);
    }

    private void fillCountryList() {
        topicsList = new ArrayList<>();
        topicsList.add(new Topics("GARDEN", R.drawable.garden));

    }
}
