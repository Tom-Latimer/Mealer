package com.example.mealer;
import static org.junit.Assert.*;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

public class permSuspendCookTest {

    public Cook_Class getCook (String id) {
        DatabaseReference dbUsers = (DatabaseReference) FirebaseDatabase.getInstance().getReference("Users");

        final Cook_Class[] cook = new Cook_Class[1];

        dbUsers.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Cook_Class currUser = snapshot.getValue(Cook_Class.class);
                cook[0] = currUser;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("TAG",error.getMessage());
            }
        });
        return cook[0];
    }

    @Test
    public void testPermanentlySuspendCook() {
        //change id to a cook id in firebase
        String id = "";

        boolean expected = false;

        //method that suspends cook goes here

        //get cook after suspension
        Cook_Class suspendedCook = getCook(id);
        boolean actual = suspendedCook.get_suspended();

        assertEquals("Cook was Successfully suspended",expected,actual);
    }
}
