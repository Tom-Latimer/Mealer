package com.example.mealer;

public class PurchaseRequest {

    private Meal_Class meal;
    private String clientName;
    private String pickUpTime;
    private boolean approved;

    public PurchaseRequest(){}

    public PurchaseRequest(Meal_Class meal, String clientName, String pickUpTime, boolean approved) {
        this.meal = meal;
        this.clientName = clientName;
        this.pickUpTime = pickUpTime;
        this.approved = approved;
    }

        public Meal_Class getMeal() { return meal; }
    public void setMeal(Meal_Class meal) {this.meal = meal;}

    public String getClientName(){ return clientName; }
    public void setClientName(String clientName){ this.clientName = clientName; }

    public String getPickUpTime() { return pickUpTime; }
    public void setPickUpTime(String pickUpTime){}


    public boolean getApproved(){ return approved; }
    public void setApproved(boolean approved){ this.approved = approved; }

}
