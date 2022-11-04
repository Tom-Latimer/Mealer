package com.example.mealer;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ComplaintList extends ArrayAdapter<Complaint> {

    private Activity context;
    List<Complaint> complaints;

    public ComplaintList(Activity context, List<Complaint> complaints) {
        super(context, R.layout.activity_complaint_list, complaints);
        this.context = context;
        this.complaints = complaints;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_complaint_list, null, true);

        TextView txtComplaintName2 = (TextView) listViewItem.findViewById(R.id.txtComplaintName2);
        TextView txtClientComplaint2 = (TextView) listViewItem.findViewById(R.id.txtClientComplaint2);

        Complaint complaint = complaints.get(position);
        txtComplaintName2.setText(complaint.getComplaintId());
        txtClientComplaint2.setText(String.valueOf(complaint.getComplaintMessage()));
        return listViewItem;
    }

}
