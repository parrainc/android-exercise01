package com.example.carlosparra.exercise01.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.widget.TextView;

import com.example.carlosparra.exercise01.R;
import com.example.carlosparra.exercise01.models.User;

public class ProfileActivity extends AppCompatActivity {

    private TextView textViewName;
    private TextView textViewDescription;
    private TextView textViewRepo;
    private TextView textViewStars;
    private TextView textViewContribs;
    private AppCompatButton buttonShareProfile;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        textViewName = findViewById(R.id.textViewName);
        textViewDescription = findViewById(R.id.textViewDescription);
        textViewContribs = findViewById(R.id.textViewContributions);
        textViewRepo = findViewById(R.id.textViewRepositories);
        textViewStars = findViewById(R.id.textViewStars);
        buttonShareProfile = findViewById(R.id.buttonShareProfile);

        user = (User) getIntent().getExtras().get("user_data");

        if (user != null) {
            textViewName.setText(user.name);
            textViewDescription.setText(user.description);
            textViewRepo.setText(user.attributes.get("Repositories").toString());
            textViewContribs.setText(user.attributes.get("Collaborations").toString());
            textViewStars.setText(user.attributes.get("Stars").toString());
        }

    }
}