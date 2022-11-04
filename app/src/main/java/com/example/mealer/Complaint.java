package com.example.mealer;

public class Complaint {

    String _complaintDate;
    String _complaintMessage;
    String _complaintRecipient;
    String _complaintId;

    public Complaint(){

    }

    public Complaint(String id, String complaint, String date, String recipient){
        _complaintId = id;
        _complaintMessage = complaint;
        _complaintDate = date;
        _complaintRecipient = recipient;
    }

    public void setComplaintId(String id){
        _complaintId = id;
    }

    public String getComplaintId(){
        return _complaintId;
    }

    public void setComplaintMessage(String complaint){
        _complaintMessage = complaint;
    }

    public String getComplaintMessage(){
        return _complaintMessage;
    }

}
