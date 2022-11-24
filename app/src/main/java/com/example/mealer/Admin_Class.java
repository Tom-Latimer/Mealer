package com.example.mealer;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Admin_Class extends User_Class {

    public Admin_Class() {}
    public Admin_Class(String name , String last_name, String email , String password ,  String address , String type ){
        super(name , last_name, email , password ,  address , type );
    }

    public boolean dismissComplaint(Complaint com) {
        String id = com.getComplaintId();
        DatabaseReference dR = (DatabaseReference) FirebaseDatabase.getInstance().getReference("Complaints").child(id);

        dR.removeValue();
        //Toast.makeText(getApplicationContext(), "Complaint Dismissed", Toast.LENGTH_LONG).show();
        return true;
    }

    public void permanentSuspendCook(Complaint com) {
        String id = com.getComplaintId();
        DatabaseReference complaintDb = (DatabaseReference) FirebaseDatabase.getInstance().getReference("Complaints");
        complaintDb.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Complaint complaint = (Complaint) snapshot.getValue(Complaint.class);
                DatabaseReference dR=(DatabaseReference) FirebaseDatabase.getInstance().getReference("Users").child(complaint.getComplaintRecipient());

                dR.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        snapshot.getRef().child("_suspended").setValue("true");
                        snapshot.getRef().child("_suspension_date").setValue("");
                        complaintDb.child(id).removeValue();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.d("TAG",error.getMessage());
                    }
                });

                //Toast.makeText(getApplicationContext(), "Cook Permanently Suspended", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("TAG",error.getMessage());
            }
        });


    }

    public void tempSuspendCook(String id, int suspensionLength) {

        DatabaseReference complaintDb = (DatabaseReference) FirebaseDatabase.getInstance().getReference("Complaints");
        complaintDb.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Complaint complaint = (Complaint) snapshot.getValue(Complaint.class);
                DatabaseReference dR=(DatabaseReference) FirebaseDatabase.getInstance().getReference("Users").child(complaint.getComplaintRecipient());

                dR.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        Calendar suspensionDate = Calendar.getInstance();
                        suspensionDate.add(Calendar.DATE, suspensionLength);

                        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
                        String strSuspensionDate = sdfDate.format(suspensionDate.getTime());

                        snapshot.getRef().child("_suspended").setValue("true");
                        snapshot.getRef().child("_suspension_date").setValue(strSuspensionDate);
                        complaintDb.child(id).removeValue();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.d("TAG",error.getMessage());
                    }
                });

                //Toast.makeText(getApplicationContext(), "Cook Temporarily Suspended", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("TAG",error.getMessage());
            }
        });

    }
}
