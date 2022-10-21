package com.example.mealer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class Home extends AppCompatActivity {

    private DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        //dbRef = FirebaseDatabase.getInstance().getReference("Users")
        //        .child(userId);
        //welcomeMessage();
    }

    public void btnLogOutClick(View view){
        startActivity(new Intent(Home.this, MainActivity.class));
    }
    /*
    public void welcomeMessage() {
        dbRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    User_Class user = (User_Class) task.getResult().getValue();
                    String type = user.get_type();
                    //.setText("You are logged in as " + type)
                }
            }
        });
    }

    public void logout() {
        FirebaseAuth.getInstance().signOut();
    }

     */
}