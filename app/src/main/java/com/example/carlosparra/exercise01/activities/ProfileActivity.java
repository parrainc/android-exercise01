package com.example.carlosparra.exercise01.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.TextView;

import com.example.carlosparra.exercise01.R;
import com.example.carlosparra.exercise01.models.User;

public class ProfileActivity extends AppCompatActivity {

    TextView textViewName;
    TextView textViewDescription;
    TextView textViewRepo;
    TextView textViewStars;
    TextView textViewContribs;
    AppCompatButton buttonShareProfile;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setTitle(R.string.profile_title);

        textViewName = findViewById(R.id.textViewName);
        textViewDescription = findViewById(R.id.textViewDescription);
        textViewContribs = findViewById(R.id.textViewContributions);
        textViewRepo = findViewById(R.id.textViewRepositories);
        textViewStars = findViewById(R.id.textViewStars);
        buttonShareProfile = findViewById(R.id.buttonShareProfile);

        if (getIntent() != null && getIntent().getExtras() != null) {
            user = (User) getIntent().getSerializableExtra("user_data");

            if (user != null) {
                textViewName.setText(user.name);
                textViewDescription.setText(user.description);
                textViewRepo.setText(user.attributes.get("Repositories").toString());
                textViewContribs.setText(user.attributes.get("Collaborations").toString());
                textViewStars.setText(user.attributes.get("Stars").toString());
            }
        }

        buttonShareProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareProfile(user);
            }
        });
    }

    private void shareProfile(User user) {
        String shareableText =
                String.format("Sharing profile of: %s, He's a %s. He has %d+ Repositories on GitHub.",
                user.name,
                user.description,
                user.attributes.get("Repositories"));

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, shareableText);
        intent.setType("text/plain");

        String intentTitle = getResources().getString(R.string.intent_share_title);
        Intent chooser = Intent.createChooser(intent, intentTitle);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(chooser);
        }
    }
}