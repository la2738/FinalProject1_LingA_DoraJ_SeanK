package com.example.android.finalproject_linga_doraj_seank;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class UserDevelopmentActivity extends AppCompatActivity {

    RadioGroup ownershipRadioGroup;
    RadioGroup experienceRadioGroup;
    RadioButton ownershipRadioButton;
    RadioButton experienceRadioButton;
    Button saveProfile;
    User user;
    String userOwnership;
    String userExperience;
    String userInterests;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_development);

        ownershipRadioGroup = findViewById(R.id.ownership_radio_group);
        experienceRadioGroup = findViewById(R.id.experience_radio_group);
        saveProfile = (Button)findViewById(R.id.button_save_profile);

        saveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ownershipRadioId = ownershipRadioGroup.getCheckedRadioButtonId();
                ownershipRadioButton = findViewById(ownershipRadioId);
                userOwnership = ownershipRadioButton.getText().toString();

                int experienceRadioId = experienceRadioGroup.getCheckedRadioButtonId();
                experienceRadioButton = findViewById(experienceRadioId);
                userExperience = experienceRadioButton.getText().toString();
            }
        });
    }

}
