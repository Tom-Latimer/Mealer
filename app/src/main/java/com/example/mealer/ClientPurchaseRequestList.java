package com.example.mealer;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.nio.file.attribute.FileTime;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ClientPurchaseRequestList extends ArrayAdapter<PurchaseRequest> {

    private Activity context;
    List<PurchaseRequest> purchaseRequests;
    PurchaseRequest purchaseRequest;

    private boolean isAcceptable = true;

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
                View parentRow = (View) v.getParent();
                ListView listView = (ListView) parentRow.getParent();
                final int position = listView.getPositionForView(parentRow);

                PurchaseRequest purchaseRequest = purchaseRequests.get(position);
                showSubmitComplaintDialog(purchaseRequest);
            }
        });

        return listViewItem;

    }

    private void showSubmitComplaintDialog(PurchaseRequest purchaseRequest) {

        // final String complaintId = purchaseRequest.getMeal();
        String meal = String.valueOf(purchaseRequest.getMeal());
        String cook = String.valueOf(purchaseRequest.getCookID());

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        FileTime getLayoutInflater;
        LayoutInflater inflater = LayoutInflater.from(getContext());
        final View dialogView = inflater.inflate(R.layout.activity_submit_complaint, null);
        dialogBuilder.setView(dialogView);

        final TextView txtMeal  = (TextView) dialogView.findViewById(R.id.textViewMealItem);
        final TextView txtCook  = (TextView) dialogView.findViewById(R.id.textViewCookItem);
        final EditText txtComplaintMessage = (EditText) dialogView.findViewById(R.id.editTextComplaintMessage);
        final Button btnSubmit = (Button) dialogView.findViewById(R.id.btnSubmit);

        txtMeal.setText(meal);
        txtCook.setText(cook);

        final AlertDialog builder = dialogBuilder.create();
        builder.show();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String strDescription = txtComplaintMessage.getText().toString();

                // verify
                boolean isAcceptable = true;
                Verification_Class verify = new Verification_Class();
                String descriptionError = verify.checkFirstName(strDescription);

                if (descriptionError != "") {
                    txtComplaintMessage.setError(descriptionError);
                    txtComplaintMessage.requestFocus();
                    isAcceptable = false;
                }
                if (isAcceptable) {
                    Date currentDate = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    String strCurrentDate = formatter.format(currentDate);

                    // get cook and client uid

                    Complaint Complaint = new Complaint();
                    Complaint.add_complaint(txtComplaintMessage.getText().toString(), strCurrentDate, purchaseRequest.getCookID());

                    builder.dismiss();
                }


            }
        });
    }


}