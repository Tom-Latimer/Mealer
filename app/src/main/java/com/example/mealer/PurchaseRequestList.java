package com.example.mealer;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class PurchaseRequestList extends ArrayAdapter<PurchaseRequest> {

    private Activity context;
    List<PurchaseRequest> purchaseRequests;

    public PurchaseRequestList(Activity context, List<PurchaseRequest> purchaseRequests) {
        super(context, R.layout.activity_purchase_request_list, purchaseRequests);
        this.context = context;
        this.purchaseRequests = purchaseRequests;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_purchase_request_list, null, true);

        TextView txtMealName = (TextView) listViewItem.findViewById(R.id.txtMealName);
        TextView txtClientName = (TextView) listViewItem.findViewById(R.id.txtClientName);
        TextView txtPickUpTime = (TextView) listViewItem.findViewById(R.id.txtPickUpTime);
        TextView txtStatus = (TextView) listViewItem.findViewById(R.id.txtStatus);

        Button btnApprove = (Button) listViewItem.findViewById(R.id.btnApprove);
        Button btnReject = (Button) listViewItem.findViewById(R.id.btnReject);

        PurchaseRequest purchaseRequest = purchaseRequests.get(position);

        DatabaseReference dBR = FirebaseDatabase.getInstance().getReference("Users").child(purchaseRequest.getClientID());
        dBR.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Client_Class client = snapshot.getValue(Client_Class.class);
                String clientName = client.get_name();
                txtClientName.setText(clientName);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("TAG",error.getMessage());
            }
        });

        String mealName = (purchaseRequest.getMeal()).get_name();
        String pickUpTime = purchaseRequest.getPickUpTime();
        String status = purchaseRequest.getStatus();

        txtMealName.setText(mealName);
        txtPickUpTime.setText(pickUpTime);
        txtStatus.setText(status);

        btnApprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View parentRow = (View) v.getParent();
                ListView listView = (ListView) parentRow.getParent();
                final int position = listView.getPositionForView(parentRow);

                PurchaseRequest purchaseRequest = purchaseRequests.get(position);
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                DatabaseReference dB = (DatabaseReference) FirebaseDatabase.getInstance().getReference("PurchaseRequests");
                dB.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        snapshot.getRef().child(purchaseRequest.getPurchaseRequestID()).child("status").setValue("Approved");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.d("TAG",error.getMessage());
                    }
                });
            }
        });

        btnReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View parentRow = (View) v.getParent();
                ListView listView = (ListView) parentRow.getParent();
                final int position = listView.getPositionForView(parentRow);

                PurchaseRequest purchaseRequest = purchaseRequests.get(position);
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                DatabaseReference dB = (DatabaseReference) FirebaseDatabase.getInstance().getReference("PurchaseRequests");
                dB.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        snapshot.getRef().child(purchaseRequest.getPurchaseRequestID()).child("status").setValue("Rejected");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.d("TAG",error.getMessage());
                    }
                });
            }
        });

        return listViewItem;
    }
}