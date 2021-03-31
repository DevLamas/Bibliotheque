package com.example.bibliotheque.controleur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bibliotheque.Model.TableStatus;
import com.example.bibliotheque.Object.ObjectStatus;
import com.example.bibliotheque.R;

public class Status_form extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_form);
    }
    public void Menu(View view) {

        Intent intent = new Intent(this, Admin.class);
        startActivity(intent);
    }
    public void CreationStatus(View view)
    {
        final Context context = view.getRootView().getContext();

        EditText editTextTextPersonName = (EditText) findViewById(R.id.editTextTextPersonName);
        String textName = editTextTextPersonName.getText().toString();
        Log.d("TAG", "name:"+textName);

        if(!textName.equals("")){


        ObjectStatus objectStatus =  new ObjectStatus();
        objectStatus.setName(textName);

        boolean createSuccessful = new TableStatus(context).create(objectStatus);

        if(createSuccessful){
            Toast.makeText(context, "Statut crée.",
                    Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "erreur.",
                    Toast.LENGTH_SHORT).show();
        }
        Menu(view);
        }
        else{
        Toast.makeText(context, "erreur champs vide.",
                Toast.LENGTH_SHORT).show();
    }
    }
}