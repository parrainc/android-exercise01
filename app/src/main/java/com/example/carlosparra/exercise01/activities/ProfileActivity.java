package com.example.carlosparra.exercise01.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.carlosparra.exercise01.R;
import com.example.carlosparra.exercise01.models.User;

public class ProfileActivity extends AppCompatActivity {

    private TextView textViewName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        textViewName = findViewById(R.id.textViewName);
        User user = (User) getIntent().getExtras().get("user_data");

        textViewName.setText(user.name);

    }
}