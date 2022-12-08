package com.example.mealer;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class PurchaseRequestList extends ArrayAdapter<PurchaseRequest> {

    private Activity context;
    List<PurchaseRequest> purchaseRequests;
    PurchaseRequest purchaseRequest;

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

        Button btnApprove = (Button) listViewItem.findViewById(R.id.btnApprove);
        Button btnReject = (Button) listViewItem.findViewById(R.id.btnReject);

        purchaseRequest = purchaseRequests.get(position);
        String mealName = (purchaseRequest.getMeal()).get_name();
        String clientID = purchaseRequest.getClientID();
        String pickUpTime = purchaseRequest.getPickUpTime();

        txtMealName.setText(mealName);

        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("Users").child(clientID);
        dR.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    DataSnapshot snapshot = task.getResult();
                    String clientName = (snapshot.child("_name").getValue().toString()) + " " + (snapshot.child("_last_name").getValue().toString());

                    txtClientName.setText(clientName);

                }
            }
        });


        txtPickUpTime.setText(pickUpTime);


        btnApprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View parentRow = (View) v.getParent();
                ListView listView = (ListView) parentRow.getParent();
                final int position = listView.getPositionForView(parentRow);

                PurchaseRequest purchaseRequest = purchaseRequests.get(position);
                purchaseRequest.setStatus("Approved");
            }
        });

        btnReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View parentRow = (View) v.getParent();
                ListView listView = (ListView) parentRow.getParent();
                final int position = listView.getPositionForView(parentRow);

                PurchaseRequest purchaseRequest = purchaseRequests.get(position);
                purchaseRequest.setStatus("Rejected");
            }
        });

        return listViewItem;
    }
}