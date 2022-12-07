package com.example.mealer;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

}
