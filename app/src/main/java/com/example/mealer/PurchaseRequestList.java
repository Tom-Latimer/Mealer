package com.example.mealer;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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

        purchaseRequest = purchaseRequests.get(position);
        String mealName = (purchaseRequest.getMeal()).get_name();
        String clientName = purchaseRequest.getClientName();
        String pickUpTime = purchaseRequest.getPickUpTime();

        txtMealName.setText(mealName);
        txtClientName.setText(clientName);
        txtPickUpTime.setText(pickUpTime);

        return listViewItem;
    }


    public void btnApproveClick(View view){
        purchaseRequest.setStatus("Approved");
    }

    public void btnRejectClick(View view){
        purchaseRequest.setStatus("Rejected");
    }
}