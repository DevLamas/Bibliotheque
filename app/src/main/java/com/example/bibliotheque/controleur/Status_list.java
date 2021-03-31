package com.example.bibliotheque.controleur;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bibliotheque.Model.TableStatus;
import com.example.bibliotheque.Object.ObjectStatus;
import com.example.bibliotheque.R;

import java.util.List;

public class Status_list extends AppCompatActivity {
    Context context;
    String idOpt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_list);
        readRecords();


    }
    public void Menu(View view) {

        Intent intent = new Intent(this, Admin.class);
        startActivity(intent);
    }
    public void readRecords() {

        LinearLayout linearLayoutRecords = (LinearLayout)
                findViewById(R.id.ListStatut);

        List<ObjectStatus> Status = new TableStatus(this).read();

        if (Status.size() > 0) {
            for (ObjectStatus obj : Status) {
                int id = obj.getId();
                String studentNom = obj.getName();
                TextView textViewStudentItem= new TextView(this);
                textViewStudentItem.setPadding(0, 10, 0, 10);
                textViewStudentItem.setText(studentNom);
                textViewStudentItem.setTag(Integer.toString(id));
                textViewStudentItem.setGravity(Gravity.CENTER);
                textViewStudentItem.setTextColor(ContextCompat.getColor(this,R.color.colorText));


                textViewStudentItem.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        context = v.getContext();
                        idOpt = v.getTag().toString();
                        final CharSequence[] items = { "Edit", "Delete" };
                        new AlertDialog.Builder(context).setTitle("Student Record")
                                .setItems(items, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int item) {
                                        if (item == 0) {
                                            editRecord(Integer.parseInt(idOpt));
                                        }
                                        else if (item == 1) {
                                            boolean deleteSuccessful = new
                                                    TableStatus(context).delete(Integer.parseInt(idOpt));
                                            if (deleteSuccessful){
                                                Toast.makeText(context, "Student record was deleted.",
                                                        Toast.LENGTH_SHORT).show();
                                            }else{
                                                Toast.makeText(context, "Unable to delete student record.",
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                            readRecords();
                                        }
                                        dialog.dismiss();
                                    }
                                }).show();
                        return false;
                    }
                });


                linearLayoutRecords.addView(textViewStudentItem);
            }
        }
        else {
            TextView locationItem = new TextView(this);
            locationItem.setPadding(8, 8, 8, 8);
            locationItem.setText("No records yet.");
            linearLayoutRecords.addView(locationItem);
        }
    }



    public void editRecord(final int studentId) {
        final TableStatus TableStatus = new
                TableStatus(context);
        ObjectStatus ObjectStatus = TableStatus.readSingleRecord(studentId);
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.activity_status_form, null,
                false);
        final EditText editTextStudentName = (EditText)
                formElementsView.findViewById(R.id.editTextTextPersonName);

        editTextStudentName.setText(ObjectStatus.getName());

        new android.app.AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Edit Record")
                .setPositiveButton("Save Changes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                ObjectStatus ObjectStatus = new ObjectStatus();
                                ObjectStatus.setId(studentId);
                                ObjectStatus.setName( editTextStudentName.getText().toString());

                                boolean updateSuccessful = TableStatus.update(ObjectStatus);
                                if(updateSuccessful){
                                    Toast.makeText(context, "Student record was updated.", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(context, "Unable to update student record.",
                                            Toast.LENGTH_SHORT).show();
                                }
                                readRecords();
                                dialog.cancel();
                            }
                        }).show();

    }


}