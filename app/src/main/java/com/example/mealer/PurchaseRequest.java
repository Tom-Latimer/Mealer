package com.example.mealer;

public class PurchaseRequest {

    private Meal_Class meal;
    private String clientID;
    private String cookID;
    private String pickUpTime;
    private String status;
    private float rating;
    private String purchaseRequestID;

    public PurchaseRequest(){}

    public PurchaseRequest(Meal_Class meal, String clientID, String cookID, String pickUpTime, String status) {
        this.meal = meal;
        this.clientID = clientID;
        this.cookID = cookID;
        this.pickUpTime = pickUpTime;
        this.status = status;
        this.rating = -1; // currently unknown (not rated yet)
    }

    public PurchaseRequest(Meal_Class meal, String clientID, String cookID, String pickUpTime, String status, float rating) {
        this.meal = meal;
        this.clientID = clientID;
        this.cookID = cookID;
        this.pickUpTime = pickUpTime;
        this.status = status;
        this.rating = rating;
    }

    public Meal_Class getMeal() { return meal; }
    public void setMeal(Meal_Class meal) {this.meal = meal;}

    public String getClientID(){ return clientID; }
    public void setClientID(String clientID){ this.clientID = clientID; }

    public String getCookID(){ return cookID; }
    public void setCookID(String cookID){ this.cookID = cookID; }

    public String getPickUpTime() { return pickUpTime; }
    public void setPickUpTime(String pickUpTime){}

    public String getStatus(){ return status; }
    public void setStatus(String status){ this.status = status; }

    public float getRating(){ return rating; }
    public void setRating(float rating){ this.rating = rating; }

    public String getPurchaseRequestID() {
        return purchaseRequestID;
    }

    public void setPurchaseRequestID(String purchaseRequestID) {
        this.purchaseRequestID = purchaseRequestID;
    }
}
