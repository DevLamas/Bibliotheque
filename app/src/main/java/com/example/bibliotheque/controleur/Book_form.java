package com.example.bibliotheque.controleur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.bibliotheque.R;

public class Book_form extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_form);
    }
    public void Menu(View view) {

        Intent intent = new Intent(this, Admin.class);
        startActivity(intent);
    }
}