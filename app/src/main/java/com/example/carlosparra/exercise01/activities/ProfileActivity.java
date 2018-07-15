package com.example.carlosparra.exercise01.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.carlosparra.exercise01.R;
import com.example.carlosparra.exercise01.models.User;
import com.example.carlosparra.exercise01.utils.SharedPreferencesUtil;

public class ProfileActivity extends AppCompatActivity {

    TextView textViewName;
    TextView textViewDescription;
    TextView textViewRepo;
    TextView textViewStars;
    TextView textViewContribs;
    AppCompatButton buttonShareProfile;
    User user;

    SharedPreferencesUtil mSharedPreferenceUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setTitle(R.string.profile_title);

        mSharedPreferenceUtil = new SharedPreferencesUtil(this);

        textViewName = findViewById(R.id.textViewName);
        textViewDescription = findViewById(R.id.textViewDescription);
        textViewContribs = findViewById(R.id.textViewContributions);
        textViewRepo = findViewById(R.id.textViewRepositories);
        textViewStars = findViewById(R.id.textViewStars);
        buttonShareProfile = findViewById(R.id.buttonShareProfile);

        if (getIntent() != null && getIntent().getExtras() != null) {
            user = (User) getIntent().getSerializableExtra(LoginActivity.EXTRA_USER_DATA);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_logout:
                logOut();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
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

    private void logOut() {
        mSharedPreferenceUtil.clearAll();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        this.finish();
    }
}