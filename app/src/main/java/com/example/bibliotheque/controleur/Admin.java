package com.example.bibliotheque.controleur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.bibliotheque.R;

public class Admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }


    public void layout_users(View view) {
        Intent intent = new Intent(this, Users_list.class);
        startActivity(intent);


    }
    public void layout_user_form(View view) {

        Intent intent = new Intent(this, User_form.class);
        startActivity(intent);
    }
    public void layout_books_list(View view) {
        Intent intent = new Intent(this, Books_list.class);
        startActivity(intent);

    }
    public void layout_book_form(View view) {

        Intent intent = new Intent(this, Book_form.class);
        startActivity(intent);
    }
    public void layout_status_list(View view) {
        Intent intent = new Intent(this, Status_list.class);
        startActivity(intent);

    }
    public void layout_status_form(View view) {
        Intent intent = new Intent(this, Status_form.class);
        startActivity(intent);

    }
}