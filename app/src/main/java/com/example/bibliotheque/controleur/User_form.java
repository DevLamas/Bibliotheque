package com.example.bibliotheque.controleur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.bibliotheque.R;

public class User_form extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_form);
    }
    public void Menu(View view) {

        Intent intent = new Intent(this, Admin.class);
        startActivity(intent);
    }
}