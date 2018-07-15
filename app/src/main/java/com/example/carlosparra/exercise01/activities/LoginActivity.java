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
import com.example.carlosparra.exercise01.utils.SharedPreferencesUtil;

public class LoginActivity extends AppCompatActivity {

    public static final String EXTRA_USER_DATA = "user_data";

    AppCompatButton buttonLogin;
    private EditText editTextEmail;
    private EditText editTextPassword;

    private SharedPreferencesUtil mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedPreferences = new SharedPreferencesUtil(this);
        if (mSharedPreferences.isLogedIn()) {
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra(EXTRA_USER_DATA, mSharedPreferences.getUserData());
            startActivity(intent);
            this.finish();
        }

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
        if (email.equals("carlos@unapec.edu.do") && password.equals("strongpass")) {
            User user = new User();

            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra(EXTRA_USER_DATA, user);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            mSharedPreferences.saveLogIn(true);

            startActivity(intent);
        }else {
            displayMessage();
        }
    }

    private void displayMessage(){
        Toast.makeText(this,"Email/Password cannot be empty or invalid",
                Toast.LENGTH_SHORT).show();
    }
}
