package com.example.bibliotheque.controleur;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bibliotheque.Model.TableUser;
import com.example.bibliotheque.Object.ObjectUser;
import com.example.bibliotheque.R;

public class Admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Button buttonCreateStudent = (Button) findViewById(R.id.layout_user_form);
        buttonCreateStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Context context = v.getRootView().getContext();
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View formElementsView = inflater.inflate(R.layout.activity_form_add_user, null, false);
                final EditText editTextUserLastName = (EditText) formElementsView.findViewById(R.id.nom);
                final EditText editTextUserFirstName = (EditText) formElementsView.findViewById(R.id.prenom);
                final EditText editTextUserEmail = (EditText) formElementsView.findViewById(R.id.email);
                final EditText editTextUserPassword = (EditText) formElementsView.findViewById(R.id.password);
                final EditText editTextUserConfirmPassword = (EditText) formElementsView.findViewById(R.id.confirmPassword);
                new AlertDialog.Builder(context).setView(formElementsView).setTitle("Création utilisateur").setPositiveButton("Ajouter",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String userLastname = editTextUserLastName.getText().toString();
                        String userFirstname = editTextUserFirstName.getText().toString();
                        String userEmail = editTextUserEmail.getText().toString();
                        String userPassword = editTextUserPassword.getText().toString();
                        String userConfirmepassword = editTextUserConfirmPassword.getText().toString();
                        if(userPassword.equals(userConfirmepassword)) {
                            ObjectUser objectUser = new ObjectUser();
                            objectUser.setLastname(userLastname);
                            objectUser.setFirstname(userFirstname);
                            objectUser.setEmail(userEmail);
                            objectUser.setPassword(userPassword);
                            boolean createSuccessful = new TableUser(context).create(objectUser);
                            if(createSuccessful){
                                Toast.makeText(context, "Les informations sont enregistrées.", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(context, "Les informations ne sont pas enregistrées.", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(context, "Mot de passe non conforme", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).show();
            }
        });
    }


    public void layout_users(View view) {
        Intent intent = new Intent(this, Users_list.class);
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