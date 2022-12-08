package com.example.mealer;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Complaint {

    String _complaintDate;
    String _complaintMessage;
    String _complaintRecipient;
    String _complaintId;
//    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//    String uid = user.getUid();

    public Complaint(){

    }

    public Complaint(String id, String message, String date, String recipient){
        _complaintId = id;
        _complaintMessage = message;
        _complaintDate = date;
        _complaintRecipient = recipient;
    }

    public void setComplaintId(String id){
        _complaintId = id;
    }

    public String getComplaintId(){
        return _complaintId;
    }

    public void setComplaintMessage(String complaint){
        _complaintMessage = complaint;
    }

    public String getComplaintMessage(){
        return _complaintMessage;
    }

    public String getComplaintRecipient(){return _complaintRecipient;}

    public void setComplaintRecipient(String recipient) {_complaintRecipient = recipient; }

    public void add_complaint(Complaint complaint){
        //check if meal is null

        // DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Complaint").child(uid);

//        String id = ref.push().getKey();
//        complaint.setComplaintId(id);
//        complaint.setComplaintMessage();
//        complaint.set_cookID(uid);
//        ref.child(id).setValue(meal);


    }
