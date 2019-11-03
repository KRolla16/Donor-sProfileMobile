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

import com.example.myapplication.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class MainActivity extends AppCompatActivity {

    MaterialEditText edtNewUser, edtNewPassword, edtNewEmail; //rejestracja
    MaterialEditText edtUser, edtPassword;

    Button btn_signUp, btn_signIn;

    Spinner SpinnerType,SpinnerPlec;

    FirebaseDatabase database;
    DatabaseReference users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Firebase
        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");

        edtUser = (MaterialEditText) findViewById(R.id.edtUser);
        edtPassword = (MaterialEditText) findViewById(R.id.edtPassword);


        btn_signIn = (Button) findViewById(R.id.btn_sign_in);
        btn_signUp = (Button) findViewById(R.id.btn_sign_up);

        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSignUpDialog();
            }
        });


        btn_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn(edtUser.getText().toString(),edtPassword.getText().toString());
            }
        });
    }

    private void signIn(final String user, final String pwd) {
        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(user).exists())
                {
                    if (!user.isEmpty())
                    {
                        User login = dataSnapshot.child(user).getValue(User.class);
                        if(login.getPassword().equals(pwd)) {
                            Toast.makeText(MainActivity.this, "Login ok", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                            startActivity(intent);
                        }else
                            Toast.makeText(MainActivity.this,"Wrong password",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(MainActivity.this,"Enter Username",Toast.LENGTH_SHORT).show();
                    }
                }
                else
                    Toast.makeText(MainActivity.this,"User is not exists",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    private void showSignUpDialog(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("Sign Up");
        //alertDialog.setMessage("Please fill full information");

        LayoutInflater inflater = this.getLayoutInflater();
        View sign_up_layout = inflater.inflate(R.layout.sign_up_layout,null);

        edtNewUser = (MaterialEditText)sign_up_layout.findViewById(R.id.edtNewUserName);
        edtNewEmail = (MaterialEditText)sign_up_layout.findViewById(R.id.edtNewEmail);
        edtNewPassword = (MaterialEditText)sign_up_layout.findViewById(R.id.edtNewPassword);
        SpinnerType = (Spinner)sign_up_layout.findViewById(R.id.SpinnerType);
        SpinnerPlec = (Spinner)sign_up_layout.findViewById(R.id.SpinnerPlec);




        alertDialog.setView(sign_up_layout);


        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final User user = new User(edtNewUser.getText().toString(),
                        edtNewPassword.getText().toString(),
                        edtNewEmail.getText().toString(),
                        SpinnerType.getSelectedItem().toString(),
                        SpinnerPlec.getSelectedItem().toString());


                users.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(user.getUserName()).exists())
                            Toast.makeText(MainActivity.this, "User exists", Toast.LENGTH_SHORT).show();
                        else {
                            users.child(user.getUserName())
                                    .setValue(user);
                            Toast.makeText(MainActivity.this, "User registration completed", Toast.LENGTH_SHORT).show();
                        }
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
