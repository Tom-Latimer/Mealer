package com.example.mealer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeAdmin extends AppCompatActivity {


    ListView listViewComplaints;
    DatabaseReference databaseComplaints;
    List<Complaint> complaints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);

        databaseComplaints = (DatabaseReference) FirebaseDatabase.getInstance().getReference("Complaints");

        listViewComplaints = (ListView) findViewById(R.id.listViewComplaints);

        complaints = new ArrayList<>();

        listViewComplaints.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int item, long l) {
                Complaint complaint = complaints.get(item);
                showActionComplaintDialog(complaint.getComplaintId(), complaint.getComplaintMessage());
                return true;
            }
        });
    }


    @Override
    protected void onStart() {

        super.onStart();


        databaseComplaints.addValueEventListener(new ValueEventListener(){

            public void onDataChange(DataSnapshot dataSnapshot){

                complaints.clear();


                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){

                    Complaint complaint = postSnapshot.getValue(Complaint.class);

                    complaints.add(complaint);
                }


                ComplaintList complaintsAdapter = new ComplaintList(HomeAdmin.this, complaints);

                listViewComplaints.setAdapter(complaintsAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void showActionComplaintDialog(final String complaintId, String complaint) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.activity_complaint_dialog, null);
        dialogBuilder.setView(dialogView);

        final TextView txtComplaint  = (TextView) dialogView.findViewById(R.id.txtComplaint);
        final Button buttonDismissComplaint = (Button) dialogView.findViewById(R.id.buttonDismissComplaint);
        final Button btnPermanentSuspension = (Button) dialogView.findViewById(R.id.btnPermanentSuspension);
        final Button btnTempSuspension = (Button) dialogView.findViewById(R.id.btnTempSuspension);

        String title = "Complaint ID: " + complaintId;
        dialogBuilder.setTitle(title);
        txtComplaint.setText(complaint);

        final AlertDialog builder = dialogBuilder.create();
        builder.show();

        buttonDismissComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismissComplaint(complaintId);
                builder.dismiss();
            }
        });

        btnPermanentSuspension.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                permanentSuspendCook(complaintId);
                builder.dismiss();
            }
        });

        btnTempSuspension.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final EditText txtSuspensionLength  = (EditText) dialogView.findViewById(R.id.txtSuspensionLength);

                tempSuspendCook(complaintId, String.valueOf(txtSuspensionLength.getText()));
                builder.dismiss();
            }
        });
    }


    private boolean dismissComplaint(String id) {

        DatabaseReference dR = (DatabaseReference) FirebaseDatabase.getInstance().getReference("Complaints").child(id);

        dR.removeValue();
        Toast.makeText(getApplicationContext(), "Complaint Dismissed", Toast.LENGTH_LONG).show();
        return true;
    }

    private void permanentSuspendCook(String id) {

        Toast.makeText(getApplicationContext(), "NOT IMPLEMENTED YET", Toast.LENGTH_LONG).show();

        String cookid="";
        for (int i=0; i<complaints.size(); i++){
            if ((complaints.get(i).getComplaintId())==id){
                cookid=(complaints.get(i).getComplaintRecipient());

            }
        }

        DatabaseReference dR=(DatabaseReference) FirebaseDatabase.getInstance().getReference("Users").child(cookid).child("_suspended");
        dR.setValue(true);


        //add code to remove complaint from list on admin home page
        //
        //
        //
        //
    }

    private void tempSuspendCook(String id, String suspensionLength) {

        Toast.makeText(getApplicationContext(), "NOT IMPLEMENTED YET", Toast.LENGTH_LONG).show();

        // check if suspension length edittext is empty/not a number
        EditText SuspensionLength = (EditText) findViewById(R.id.txtSuspensionLength);
        String txtSuspensionLength = SuspensionLength.getText().toString().trim();

        Verification_Class verify = new Verification_Class();

        String suspensionLengthError = verify.checkSuspensionLength(suspensionLength);
        if (suspensionLengthError != "") {
            SuspensionLength.setError(suspensionLengthError);
            SuspensionLength.requestFocus();
        }

        DatabaseReference dR = (DatabaseReference) FirebaseDatabase.getInstance().getReference("Complaints").child(id);

        String cookID ="";
        for (int i=0; i<complaints.size(); i++){
            if ((complaints.get(i).getComplaintId())==id){
                cookID = (complaints.get(i).getComplaintRecipient());

            }
        }

        DatabaseReference ab =(DatabaseReference) FirebaseDatabase.getInstance().getReference("Users").child(cookID).child("_suspended");
        dR.setValue(true);

        DatabaseReference cd =(DatabaseReference) FirebaseDatabase.getInstance().getReference("Users").child(cookID).child("_suspensionDate");

        int numSuspensionLength = Integer.parseInt(txtSuspensionLength);
        Date suspensionDate = new Date(new Date().getTime() + (numSuspensionLength * 24*60*60*1000));
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        String strSuspensionDate = sdfDate.format(suspensionDate);
        dR.setValue(strSuspensionDate);

        Toast.makeText(getApplicationContext(), "Cook Suspended", Toast.LENGTH_LONG).show();


        //add code to remove complaint from list on admin home page
        //
        //
        //
        //
    }
}