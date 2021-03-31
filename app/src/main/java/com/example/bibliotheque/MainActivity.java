package com.example.bibliotheque;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bibliotheque.Model.TableUser;
import com.example.bibliotheque.Object.ObjectUser;
import com.example.bibliotheque.controleur.Admin;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonConnexion = (Button) findViewById(R.id.buttonConnexion);
        //buttonConnexion.setOnClickListener();
    }


    public void authentification(View view){
        final EditText textLogin = (EditText) findViewById(R.id.identifiant);
        final EditText textPassword = (EditText) findViewById(R.id.password);

        String login = textLogin.getText().toString();
        String password = textPassword.getText().toString();

        TableUser tableUser = new TableUser(this);
        ObjectUser objectUser = tableUser.findLoginAndPassword(login, password);

        if(objectUser == null){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Identifiant ou mot de passe invalide!", Toast.LENGTH_SHORT).show();
        } else {
            switch (objectUser.getStatusid()){
                case 1 :
                    //Partie Admin
                    Intent intent = new Intent(this, Admin.class);
                    startActivity(intent);
                    break;
                case 2 :
                    //Partie Intervenant
                    /*
                    Intent intent = new Intent(this, User.class);
                    startActivity(intent);*/
                    break;
                default:
                    //Partie alternant
                    /*
                    Intent intent = new Intent(this, User.class);
                    startActivity(intent);*/
                    break;
            }
        }
    }
}