package com.example.android.finalproject_linga_doraj_seank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class UserDevelopmentActivity extends AppCompatActivity {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference profileDatabaseReference;
    private FirebaseAuth mFirebaseAuth;

    RadioGroup ownershipRadioGroup;
    RadioGroup experienceRadioGroup;
    RadioButton ownershipRadioButton;
    RadioButton experienceRadioButton;
    Button saveProfile;
    CheckBox kitchenCheckbox, schoolCheckbox, gardenCheckbox, applianceCheckbox,
             craftsCheckbox, paintingCheckbox, bathroomCheckbox, organizationCheckbox;
    User user;
    String userOwnership;
    String userExperience;
    ArrayList<String> userInterests = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_development);

        Intent intent = getIntent();

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        profileDatabaseReference = mFirebaseDatabase.getReference().child("User Profiles");

        ownershipRadioGroup = findViewById(R.id.ownership_radio_group);
        experienceRadioGroup = findViewById(R.id.experience_radio_group);

        kitchenCheckbox = (CheckBox)findViewById(R.id.kitchen_checkbox);
        schoolCheckbox = (CheckBox)findViewById(R.id.school_checkbox);
        gardenCheckbox = (CheckBox)findViewById(R.id.garden_checkbox);
        applianceCheckbox = (CheckBox)findViewById(R.id.appliance_checkbox);
        craftsCheckbox = (CheckBox)findViewById(R.id.crafts_checkbox);
        paintingCheckbox = (CheckBox)findViewById(R.id.painting_checkbox);
        bathroomCheckbox = (CheckBox)findViewById(R.id.bathroom_checkbox);
        organizationCheckbox = (CheckBox)findViewById(R.id.organization_checkbox);

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

                boolean kitchenChecked = kitchenCheckbox.isChecked();
                boolean schoolChecked = schoolCheckbox.isChecked();
                boolean gardenChecked = gardenCheckbox.isChecked();
                boolean applianceChecked = applianceCheckbox.isChecked();
                boolean craftsChecked = craftsCheckbox.isChecked();
                boolean paintingChecked = paintingCheckbox.isChecked();
                boolean bathroomChecked = bathroomCheckbox.isChecked();
                boolean organizationChecked = organizationCheckbox.isChecked();

                if (kitchenChecked){
                    userInterests.add("Kitchen");
                }
                if (schoolChecked){
                    userInterests.add("School");
                }
                if (gardenChecked){
                    userInterests.add("Garden");
                }
                if (applianceChecked){
                    userInterests.add("Appliance");
                }
                if (craftsChecked){
                    userInterests.add("Crafts");
                }
                if (paintingChecked){
                    userInterests.add("Painting");
                }
                if (bathroomChecked){
                    userInterests.add("Bathroom");
                }
                if (organizationChecked){
                    userInterests.add("Organization");
                }

                user = new User(userOwnership, userExperience, userInterests);
                FirebaseUser currentUser = mFirebaseAuth.getCurrentUser();
                profileDatabaseReference.child(currentUser.getDisplayName()).setValue(user);
                Toast.makeText(UserDevelopmentActivity.this, "User Profile Saved!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(UserDevelopmentActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
