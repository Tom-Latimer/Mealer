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

public class tempSuspendCookTest {
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
    public void testTemporarilySuspendCook() {
        //change id to a cook id in firebase
        String id = "";

        //replace with suspension date
        String expectedSuspensionDate = "";

        boolean expected = true;

        //method that suspends cook temporarily goes here

        //get cook after suspension
        Cook_Class suspendedCook = getCook(id);
        boolean actual = suspendedCook.get_suspended();
        String actualDate = suspendedCook.get_suspension_date();

        assertEquals("Cook was Successfully suspended",expected,actual);
        assertEquals("Correct suspension time was administered", expectedSuspensionDate, actualDate);
    }
}
