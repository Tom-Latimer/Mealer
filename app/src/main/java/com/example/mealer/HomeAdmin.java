package com.example.mealer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

                // check if suspension length edittext is empty/not a number
                String strSuspensionLength = txtSuspensionLength.getText().toString().trim();

                Verification_Class verify = new Verification_Class();
                String suspensionLengthError = verify.checkSuspensionLength(strSuspensionLength);
                if (suspensionLengthError != "") {
                    txtSuspensionLength.setError(suspensionLengthError);
                    txtSuspensionLength.requestFocus();
                }

                int suspensionLength = Integer.parseInt(strSuspensionLength);

                tempSuspendCook(complaintId, suspensionLength);
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
        DatabaseReference complaintDb = (DatabaseReference) FirebaseDatabase.getInstance().getReference("Complaints");
        complaintDb.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Complaint complaint = (Complaint) snapshot.getValue(Complaint.class);
                DatabaseReference dR=(DatabaseReference) FirebaseDatabase.getInstance().getReference("Users").child(complaint.getComplaintRecipient());

                dR.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Cook_Class suspendedCook = (Cook_Class) snapshot.getValue(Cook_Class.class);
                        suspendedCook.set_suspended(true);
                        dR.setValue(suspendedCook);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.d("TAG",error.getMessage());
                    }
                });

                Toast.makeText(getApplicationContext(), "Cook Permanently Suspended", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("TAG",error.getMessage());
            }
        });


    }

    private void tempSuspendCook(String id, int suspensionLength) {

        DatabaseReference complaintDb = (DatabaseReference) FirebaseDatabase.getInstance().getReference("Complaints");
        complaintDb.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Complaint complaint = (Complaint) snapshot.getValue(Complaint.class);
                DatabaseReference dR=(DatabaseReference) FirebaseDatabase.getInstance().getReference("Users").child(complaint.getComplaintRecipient());

                dR.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Cook_Class suspendedCook = (Cook_Class) snapshot.getValue(Cook_Class.class);
                        suspendedCook.set_suspended(true);

                        Calendar suspensionDate = Calendar.getInstance();
                        suspensionDate.add(Calendar.DATE, suspensionLength);

                        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
                        String strSuspensionDate = sdfDate.format(suspensionDate);
                        suspendedCook.set_suspension_date(strSuspensionDate);

                        dR.setValue(suspendedCook);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.d("TAG",error.getMessage());
                    }
                });

                Toast.makeText(getApplicationContext(), "Cook Temporarily Suspended", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("TAG",error.getMessage());
            }
        });

    }
}