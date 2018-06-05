package com.example.carlosparra.exercise01.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.carlosparra.exercise01.R;
import com.example.carlosparra.exercise01.models.User;

public class LoginActivity extends AppCompatActivity {

    private AppCompatButton buttonLogin;
    private EditText editTextEmail;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonLogin = findViewById(R.id.buttonLogIn);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = editTextEmail.getText().toString();
                final String password = editTextPassword.getText().toString();

                Login(email, password);
            }
        });
    }

    private void Login(String email, String password) {
        if (email != "carlos@unapec.edu.do" && password != "strongpass") {
            User user = new User();

            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra("user_data", user);
            startActivity(intent);
        }

        Toast.makeText(this, "Email/Password cannot be empty or invalid", Toast.LENGTH_SHORT);
    }
}
