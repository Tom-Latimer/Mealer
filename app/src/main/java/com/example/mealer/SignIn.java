package com.example.mealer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignIn extends AppCompatActivity implements View.OnClickListener {
    private EditText edittxt_email, edittxt_password;
    private Button btn_SignIn;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        edittxt_email = findViewById(R.id.Email_Field3);
        edittxt_password = findViewById(R.id.Password_Field3);
        btn_SignIn = (Button) findViewById(R.id.SignIn_btn);
        btn_SignIn.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
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
                    startActivity(new Intent(SignIn.this, Home.class));
                }
                else{
                    Toast.makeText(SignIn.this, "Failed to login! Please check your credentials", Toast.LENGTH_LONG).show();

                }

            }
        });

    }

}