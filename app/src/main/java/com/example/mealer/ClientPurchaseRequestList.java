package com.example.mealer;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ClientPurchaseRequestList extends ArrayAdapter<PurchaseRequest> {

    private Activity context;
    List<PurchaseRequest> purchaseRequests;
    PurchaseRequest purchaseRequest;

    public ClientPurchaseRequestList(Activity context, List<PurchaseRequest> purchaseRequests) {

        super(context, R.layout.activity_home_client, purchaseRequests);
        this.context = context;
        this.purchaseRequests = purchaseRequests;
    }

    private static class ViewHolder {
//        TextView txtName;
//        TextView txtStatus;
//        TextView txtPickup;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_client_purchase_request_list, null, true);

        TextView txtMealName = (TextView) listViewItem.findViewById(R.id.textViewMeal);
        TextView txtCookName = (TextView) listViewItem.findViewById(R.id.textViewCook);
        TextView txtStatusName = (TextView) listViewItem.findViewById(R.id.textViewStatus);

        purchaseRequest = purchaseRequests.get(position);
        String mealName = (purchaseRequest.getMeal()).get_name();
        String cookName = purchaseRequest.getCookName();
        String status = purchaseRequest.getStatus();
        String pickUpTime = purchaseRequest.getPickUpTime();

        txtMealName.setText(mealName);
        txtCookName.setText(cookName);
        txtStatusName.setText(status);

        Button btnRate = (Button) listViewItem.findViewById(R.id.btnRate);
        Button btnComplain = (Button) listViewItem.findViewById(R.id.btnComplain);

        btnRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // startActivity(new Intent(MainActivity.this, MyOtherActivity.class));
            }
        });

        btnComplain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // startActivity(new Intent(MainActivity.this, MyOtherActivity.class));
            }
        });

        return listViewItem;


    }



}
