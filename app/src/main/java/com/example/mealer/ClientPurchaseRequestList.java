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

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_purchase_request_list, null, true);

        TextView txtMealName = (TextView) listViewItem.findViewById(R.id.txtMealName);
        TextView txtCookName = (TextView) listViewItem.findViewById(R.id.txtCookName);
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


    private OnClickListener mOnTitleClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            final int position = mListView.getPositionForView((View) v.getParent());
            Log.v(TAG, "Title clicked, row %d", position);
        }
    };

    private OnClickListener mOnTextClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            final int position = mListView.getPositionForView((View) v.getParent());
            Log.v(TAG, "Text clicked, row %d", position);
        }
    };

}
