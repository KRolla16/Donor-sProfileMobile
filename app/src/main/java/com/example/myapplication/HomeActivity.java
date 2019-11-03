package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.Model.Survey;
import com.example.myapplication.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class HomeActivity extends AppCompatActivity {

    MaterialEditText edtNewImie, edtNewQue1, edtNewQue5, edtNewQue6, edtNewQue10,edtNewQue16,edtNewQue31;

    Button btn_survey;

    Spinner SpinnerAnswer2,SpinnerAnswer3,SpinnerAnswer4,SpinnerAnswer7,SpinnerAnswer8,SpinnerAnswer9,SpinnerAnswer11,SpinnerAnswer12,
            SpinnerAnswer13,
            SpinnerAnswer14, SpinnerAnswer15, SpinnerAnswer17, SpinnerAnswer18,SpinnerAnswer19,SpinnerAnswer20, SpinnerAnswer21,
            SpinnerAnswer22, SpinnerAnswer23, SpinnerAnswer24, SpinnerAnswer25, SpinnerAnswer26, SpinnerAnswer27, SpinnerAnswer28,
            SpinnerAnswer29, SpinnerAnswer30, SpinnerAnswer32;


    FirebaseDatabase database;
    DatabaseReference answers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        //Firebase
        database = FirebaseDatabase.getInstance();
        answers = database.getReference("Answers");


        btn_survey = (Button) findViewById(R.id.btn_survey);


        btn_survey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSignUpDialog();
            }
        });



    }




    private void showSignUpDialog(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(HomeActivity.this);
        alertDialog.setTitle("Ankieta");

        LayoutInflater inflater = this.getLayoutInflater();
        View survey_question_layout = inflater.inflate(R.layout.survey_question_layout,null);

        edtNewImie = (MaterialEditText)survey_question_layout.findViewById(R.id.edtNewImie);
        edtNewQue1 = (MaterialEditText)survey_question_layout.findViewById(R.id.edtNewQue1);
        edtNewQue5 = (MaterialEditText)survey_question_layout.findViewById(R.id.edtNewQue5);
        edtNewQue6 = (MaterialEditText)survey_question_layout.findViewById(R.id.edtNewQue6);
        edtNewQue10 = (MaterialEditText)survey_question_layout.findViewById(R.id.edtNewQue10);
        edtNewQue16 = (MaterialEditText)survey_question_layout.findViewById(R.id.edtNewQue16);
        edtNewQue31 = (MaterialEditText)survey_question_layout.findViewById(R.id.edtNewQue31);
        SpinnerAnswer2 = (Spinner)survey_question_layout.findViewById(R.id.SpinnerAnswer2);
        SpinnerAnswer3 = (Spinner)survey_question_layout.findViewById(R.id.SpinnerAnswer3);
        SpinnerAnswer4 = (Spinner)survey_question_layout.findViewById(R.id.SpinnerAnswer4);
        SpinnerAnswer7 = (Spinner)survey_question_layout.findViewById(R.id.SpinnerAnswer7);
        SpinnerAnswer8 = (Spinner)survey_question_layout.findViewById(R.id.SpinnerAnswer8);
        SpinnerAnswer9 = (Spinner)survey_question_layout.findViewById(R.id.SpinnerAnswer9);
        SpinnerAnswer11 = (Spinner)survey_question_layout.findViewById(R.id.SpinnerAnswer11);
        SpinnerAnswer12 = (Spinner)survey_question_layout.findViewById(R.id.SpinnerAnswer12);
        SpinnerAnswer13 = (Spinner)survey_question_layout.findViewById(R.id.SpinnerAnswer13);
        SpinnerAnswer14 = (Spinner)survey_question_layout.findViewById(R.id.SpinnerAnswer14);
        SpinnerAnswer15 = (Spinner)survey_question_layout.findViewById(R.id.SpinnerAnswer15);
        SpinnerAnswer17 = (Spinner)survey_question_layout.findViewById(R.id.SpinnerAnswer17);
        SpinnerAnswer18 = (Spinner)survey_question_layout.findViewById(R.id.SpinnerAnswer18);
        SpinnerAnswer19 = (Spinner)survey_question_layout.findViewById(R.id.SpinnerAnswer19);
        SpinnerAnswer20 = (Spinner)survey_question_layout.findViewById(R.id.SpinnerAnswer20);
        SpinnerAnswer21 = (Spinner)survey_question_layout.findViewById(R.id.SpinnerAnswer21);
        SpinnerAnswer22 = (Spinner)survey_question_layout.findViewById(R.id.SpinnerAnswer22);
        SpinnerAnswer23 = (Spinner)survey_question_layout.findViewById(R.id.SpinnerAnswer23);
        SpinnerAnswer24 = (Spinner)survey_question_layout.findViewById(R.id.SpinnerAnswer24);
        SpinnerAnswer25 = (Spinner)survey_question_layout.findViewById(R.id.SpinnerAnswer25);
        SpinnerAnswer26 = (Spinner)survey_question_layout.findViewById(R.id.SpinnerAnswer26);
        SpinnerAnswer27 = (Spinner)survey_question_layout.findViewById(R.id.SpinnerAnswer27);
        SpinnerAnswer28 = (Spinner)survey_question_layout.findViewById(R.id.SpinnerAnswer28);
        SpinnerAnswer29 = (Spinner)survey_question_layout.findViewById(R.id.SpinnerAnswer29);
        SpinnerAnswer30 = (Spinner)survey_question_layout.findViewById(R.id.SpinnerAnswer30);
        SpinnerAnswer32 = (Spinner)survey_question_layout.findViewById(R.id.SpinnerAnswer32);



        alertDialog.setView(survey_question_layout);


        alertDialog.setNegativeButton("Odrzuć", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });


        alertDialog.setPositiveButton("Wyślij", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final Survey user = new Survey(edtNewImie.getText().toString(),
                        edtNewQue1.getText().toString(),
                        SpinnerAnswer2.getSelectedItem().toString(),
                        SpinnerAnswer3.getSelectedItem().toString(),
                        SpinnerAnswer4.getSelectedItem().toString(),
                        edtNewQue5.getText().toString(),
                        edtNewQue6.getText().toString(),
                        SpinnerAnswer7.getSelectedItem().toString(),
                        SpinnerAnswer8.getSelectedItem().toString(),
                        SpinnerAnswer9.getSelectedItem().toString(),
                        edtNewQue10.getText().toString(),
                        SpinnerAnswer11.getSelectedItem().toString(),
                        SpinnerAnswer12.getSelectedItem().toString(),
                        SpinnerAnswer13.getSelectedItem().toString(),
                        SpinnerAnswer14.getSelectedItem().toString(),
                        SpinnerAnswer15.getSelectedItem().toString(),
                        edtNewQue16.getText().toString(),
                        SpinnerAnswer17.getSelectedItem().toString(),
                        SpinnerAnswer18.getSelectedItem().toString(),
                        SpinnerAnswer19.getSelectedItem().toString(),
                        SpinnerAnswer20.getSelectedItem().toString(),
                        SpinnerAnswer21.getSelectedItem().toString(),
                        SpinnerAnswer22.getSelectedItem().toString(),
                        SpinnerAnswer23.getSelectedItem().toString(),
                        SpinnerAnswer24.getSelectedItem().toString(),
                        SpinnerAnswer25.getSelectedItem().toString(),
                        SpinnerAnswer26.getSelectedItem().toString(),
                        SpinnerAnswer27.getSelectedItem().toString(),
                        SpinnerAnswer28.getSelectedItem().toString(),
                        SpinnerAnswer29.getSelectedItem().toString(),
                        SpinnerAnswer30.getSelectedItem().toString(),
                        edtNewQue31.getText().toString(),
                        SpinnerAnswer32.getSelectedItem().toString());


                answers.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {


                            answers.child(user.getNameiName())
                                    .setValue(user);
                            Toast.makeText(HomeActivity.this, "Ankieta wysłana", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }
}
