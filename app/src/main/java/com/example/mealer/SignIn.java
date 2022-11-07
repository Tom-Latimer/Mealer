package com.example.mealer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity implements View.OnClickListener {
    private EditText edittxt_email, edittxt_password;
    private Button btn_SignIn;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edittxt_email = findViewById(R.id.Email_Field3);
        edittxt_password = findViewById(R.id.Password_Field3);

        btn_SignIn = (Button) findViewById(R.id.SignIn_btn);
        btn_SignIn.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        databaseUsers = (DatabaseReference) FirebaseDatabase.getInstance().getReference("Users");
    }

    public void backbtnSignIn(View view){ //goes back to main activity
        startActivity(new Intent(SignIn.this, MainActivity.class));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.SignIn_btn:
                signIn();
                break;
        }
    }
    private void signIn(){
        String email = edittxt_email.getText().toString().trim();
        String password = edittxt_password.getText().toString().trim();
        if(email.isEmpty()){
            edittxt_email.setError("Email is required");
            edittxt_email.requestFocus();
            return;
        }

        if(password.isEmpty()){
            edittxt_password.setError("Password is required");
            edittxt_password.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {//login function
                if(task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    databaseUsers.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            User_Class currUser = (User_Class) snapshot.getValue(User_Class.class);
                            String type = currUser.get_type();


                             Intent homeIntent = new Intent();

                            if (type.equals("ADMIN")) {
                                homeIntent = new Intent(SignIn.this,HomeAdmin.class);
                            } else if (type.equals("CLIENT")) {
                                homeIntent = new Intent(SignIn.this, HomeClient.class);
                            } else if (type.equals("COOK")) {
                                Cook_Class cookClass=(Cook_Class) snapshot.getValue(Cook_Class.class);
                                Boolean isSuspended=cookClass.get_suspended();
                                if(isSuspended==false) {
                                    homeIntent = new Intent(SignIn.this, HomeCook.class);
                                }
                                else{
                                    homeIntent=new Intent(SignIn.this,SuspendedScreen.class);

                            }
                            }
                            startActivity(homeIntent);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Log.d("TAG",error.getMessage());
                        }
                    });


                    //startActivity(new Intent(SignIn.this, HomeClient.class));
                }
                else{
                    Toast.makeText(SignIn.this, "Failed to login! Please check your credentials", Toast.LENGTH_LONG).show();

                }

            }
        });

    }

}