package com.example.mealer;

public class PurchaseRequest {

    private Meal_Class meal;
    private String clientName;
    private String cookName;
    private String pickUpTime;
    private String status;

    public PurchaseRequest(){}

    public PurchaseRequest(Meal_Class meal, String clientName, String cookName, String pickUpTime, String status) {
        this.meal = meal;
        this.clientName = clientName;
        this.cookName = cookName;
        this.pickUpTime = pickUpTime;
        this.status = status;
    }

        public Meal_Class getMeal() { return meal; }
    public void setMeal(Meal_Class meal) {this.meal = meal;}

    public String getClientName(){ return clientName; }
    public void setClientName(String clientName){ this.clientName = clientName; }

    public String getCookName(){ return cookName; }
    public void setCookName(String cookName){ this.cookName = cookName; }

    public String getPickUpTime() { return pickUpTime; }
    public void setPickUpTime(String pickUpTime){}

    public String getStatus(){ return status; }
    public void setStatus(String status){ this.status = status; }

}
