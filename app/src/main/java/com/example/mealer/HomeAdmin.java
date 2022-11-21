package com.example.mealer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HomeAdmin extends AppCompatActivity {

    ListView listViewComplaints;
    DatabaseReference databaseComplaints;
    List<Complaint> complaints;

    Admin_Class administrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);
        getWindow().setStatusBarColor(getResources().getColor(R.color.button_blue));
        administrator = new Admin_Class();

        databaseComplaints = (DatabaseReference) FirebaseDatabase.getInstance().getReference("Complaints");

        listViewComplaints = (ListView) findViewById(R.id.listViewComplaints);

        complaints = new ArrayList<>();

        listViewComplaints.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int item, long l) {
                Complaint complaint = complaints.get(item);
                showActionComplaintDialog(complaint);
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


    private void showActionComplaintDialog(Complaint complaint) {

        final String complaintId = complaint.getComplaintId();
        String complaintMessage = complaint.getComplaintMessage();

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
        txtComplaint.setText(complaintMessage);

        final AlertDialog builder = dialogBuilder.create();
        builder.show();

        buttonDismissComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                administrator.dismissComplaint(complaint);
                builder.dismiss();
            }
        });

        btnPermanentSuspension.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                administrator.permanentSuspendCook(complaint);
                Toast.makeText(getApplicationContext(), "Cook Permanently Suspended", Toast.LENGTH_LONG).show();
                builder.dismiss();
            }
        });

        btnTempSuspension.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final EditText txtSuspensionLength  = (EditText) dialogView.findViewById(R.id.txtSuspensionLength);

                // check if suspension length edittext is empty/not a number
                String strSuspensionLength = txtSuspensionLength.getText().toString().trim();

                Verification_Class verify = new Verification_Class();
                String suspensionLengthError = verify.checkSuspensionLength(strSuspensionLength);
                if (!suspensionLengthError.equals("")) {
                    txtSuspensionLength.setError(suspensionLengthError);
                    txtSuspensionLength.requestFocus();
                }

                int suspensionLength = Integer.parseInt(strSuspensionLength);

                administrator.tempSuspendCook(complaintId, suspensionLength);
                Toast.makeText(getApplicationContext(), "Cook Temporarily Suspended", Toast.LENGTH_LONG).show();
                builder.dismiss();
            }
        });
    }
    public void btnLogOutClick(View view){
        startActivity(new Intent(HomeAdmin.this, MainActivity.class));

        FirebaseAuth.getInstance().signOut();
        //finish();
    }

}