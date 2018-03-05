package com.khairulcreation.loginfirebase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.khairulcreation.loginfirebase.Common.Common;
import com.khairulcreation.loginfirebase.Model.User;

public class SignIn extends AppCompatActivity {

    Button btnSignIn;
    EditText edtUsername, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edtUsername= (EditText) findViewById(R.id.edtUsername);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);

        // Init Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user =  database.getReference("User");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog mDialog = new ProgressDialog(SignIn.this);
                mDialog.setMessage("Please Waiting...");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {


                        // Check if user not existed in DataBase
                        if (dataSnapshot.child(edtUsername.getText().toString()).exists()) {

                            //Get user Information
                            mDialog.dismiss();
                            User user = dataSnapshot.child(edtUsername.getText().toString()).getValue(User.class);
                            if (user.getPassword().equals(edtPassword.getText().toString()))

                            {

                                Toast.makeText(SignIn.this, "Sign In Successfully ! ", Toast.LENGTH_SHORT).show();

                                // after success sign in direct to new xml
                                Intent homeIntent = new Intent(SignIn.this, MainActivity.class);
                                Common.currentUser = user;
                                startActivity(homeIntent);
                                finish();

                            } else {
                                Toast.makeText(SignIn.this, "Wrong Username or Password ! Please try again.", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(SignIn.this, "User Not Exist in Database !", Toast.LENGTH_SHORT).show();
                            finish();

                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}
