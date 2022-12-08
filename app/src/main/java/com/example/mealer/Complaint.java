package com.example.mealer;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Complaint {

    String _complaintDate;
    String _complaintMessage;
    String _complaintRecipient;
    String _complaintId;

    public Complaint(){

    }

    public Complaint(String id, String message, String date, String recipient) {
        _complaintId = id;
        _complaintMessage = message;
        _complaintDate = date;
        _complaintRecipient = recipient;
    }

    public void setComplaintId(String id) {
        _complaintId = id;
    }

    public String getComplaintId() {
        return _complaintId;
    }

    public void setComplaintMessage(String complaint) {
        _complaintMessage = complaint;
    }

    public String getComplaintMessage() {
        return _complaintMessage;
    }

    public String getComplaintRecipient() {
        return _complaintRecipient;
    }

    public void setComplaintRecipient(String recipient) {
        _complaintRecipient = recipient;
    }

    public void add_complaint(String message, String date, String recipient) {

        // DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Complaint");
        String id = ref.push().getKey();

        // get cook id
        ref.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                PurchaseRequest purchaseRequest = (PurchaseRequest) snapshot.getValue(PurchaseRequest.class);
                DatabaseReference dR = (DatabaseReference) FirebaseDatabase.getInstance().getReference("Users").child(purchaseRequest.getCookID());
                String complainant = dR.getKey();
                ref.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(new Complaint(id, message, date, recipient));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("TAG", error.getMessage());
            }
        });

//        String id = ref.push().getKey();
//        complaint.set_cookID(uid);
//        ref.child(id).setValue(meal);


    }
}
