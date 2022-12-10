package com.example.mealer;

public class PurchaseRequest {

    public Meal_Class meal;
    public String clientID;
    public String cookID;
    public String pickUpTime;
    public String status;
    public float rating;
    public String purchaseRequestID;

    public PurchaseRequest(){}

    public PurchaseRequest(Meal_Class meal, String clientID, String cookID, String pickUpTime, String statusD) {
        this.meal = meal;
        this.clientID = clientID;
        this.cookID = cookID;
        this.pickUpTime = pickUpTime;
        this.status = status;
        this.rating = -1; // currently unknown (not rated yet)
    }

    public PurchaseRequest(Meal_Class meal, String clientID, String cookID, String pickUpTime, String status, String purchaseRequestID) {
        this.meal = meal;
        this.clientID = clientID;
        this.cookID = cookID;
        this.pickUpTime = pickUpTime;
        this.status = status;
        this.rating = -1; // currently unknown (not rated yet)
        this.purchaseRequestID = purchaseRequestID;
    }

    public PurchaseRequest(Meal_Class meal, float rating, String purchaseRequestID, String pickUpTime, String cookID, String status, String clientID) {
        this.meal = meal;
        this.clientID = clientID;
        this.cookID = cookID;
        this.pickUpTime = pickUpTime;
        this.status = status;
        this.rating = rating;
        this.purchaseRequestID = purchaseRequestID;
    }

    public Meal_Class getMeal() {
        return meal;
    }

    public void setMeal(Meal_Class meal) {
        this.meal = meal;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getCookID() {
        return cookID;
    }

    public void setCookID(String cookID) {
        this.cookID = cookID;
    }

    public String getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(String pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getPurchaseRequestID() {
        return purchaseRequestID;
    }

    public void setPurchaseRequestID(String purchaseRequestID) {
        this.purchaseRequestID = purchaseRequestID;
    }

}
