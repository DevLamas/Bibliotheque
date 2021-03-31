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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bibliotheque.Model.TableUser;
import com.example.bibliotheque.Object.ObjectUser;
import com.example.bibliotheque.R;

import java.util.List;

public class Users_list extends AppCompatActivity {
    Context context;
    String idOpt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);
        countRecords();
        readRecords();
        Button buttonCreateStudent = (Button) findViewById(R.id.buttonCreateUser);
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
                        countRecords();
                        readRecords();
                    }
                }).show();
            }
        });
    }

    public void countRecords() {
        int recordCount = new TableUser(this).count();
        TextView textViewRecordCount = (TextView) findViewById(R.id.textViewRecordCount);
        textViewRecordCount.setText(recordCount + " utilisateur(s).");
    }

    public void readRecords() {
        LinearLayout linearLayoutRecords = (LinearLayout) findViewById(R.id.linearLayoutRecords);
        linearLayoutRecords.removeAllViews();
        List<ObjectUser> users = new TableUser(this).read();
        if (users.size() > 0) {
            for (ObjectUser obj : users) {
                int id = obj.getId();
                String userFirstname = obj.getFirstname();
                String userLastname = obj.getFirstname();
                String textViewContents = userFirstname + " - " + userLastname;

                TextView textViewUserItem = new TextView(this);
                textViewUserItem.setPadding(0,10,0,10);
                textViewUserItem.setText(textViewContents);
                textViewUserItem.setTag(Integer.toString(id));
                textViewUserItem.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        context = v.getContext();
                        idOpt = v.getTag().toString();
                        final CharSequence[] items = { "Delete" };
                        new AlertDialog.Builder(context).setTitle("Student Record").setItems(items, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                dialog.dismiss();
                                if (item == 0) {
                                    boolean deleteSuccessful = new TableUser(context).delete(Integer.parseInt(idOpt));
                                    if (deleteSuccessful){
                                        Toast.makeText(context, "Student record was deleted.", Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(context, "Unable to delete student record.", Toast.LENGTH_SHORT).show();
                                    }
                                    countRecords();
                                    readRecords();
                                }
                            }
                        }).show();
                        return false;
                    }
                });

                linearLayoutRecords.addView(textViewUserItem);
            }
        } else {
            TextView locationItem = new TextView(this);
            locationItem.setPadding(8,8,8,8);
            locationItem.setText("No records yet.");

            linearLayoutRecords.addView(locationItem);
        }
    }

    public void Menu(View view) {
        Intent intent = new Intent(this, Admin.class);
        startActivity(intent);
    }
}