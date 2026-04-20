package org.keyin.serviceplans;

import org.keyin.user.User;

import java.time.LocalDate;

public class ServicePlan extends User {

    // Instance variables
    private int planId;
    private String planType;
    private String planDescription;
    private Double planPrice;
    LocalDate datePurchased = LocalDate.now();
    private int userId;

    //Constructors
    public ServicePlan(){
    }

    public ServicePlan(String planType, String planDescription, double planPrice){
        this.planType = planType;
        this.planDescription = planDescription;
        this.planPrice = planPrice;
    }

    public ServicePlan(int planId, String planType, String planDescription, double planPrice){
        this.planId = planId;
        this.planType = planType;
        this.planDescription = planDescription;
        this.planPrice = planPrice;
    }

    public ServicePlan(int planId, String planType, String planDescription, double planPrice, int userId) {
        this.planId = planId;
        this.planType = planType;
        this.planDescription = planDescription;
        this.planPrice = planPrice;
        this.userId = userId;
    }


    public ServicePlan(String planType, String planDescription, double planPrice, LocalDate datePurchased, int userId) {

        this.planType = planType;
        this.planDescription = planDescription;
        this.planPrice = planPrice;
        this.datePurchased = datePurchased;
        this.userId = userId;
    }

    public ServicePlan(int planId, String planType, String planDescription, double planPrice, LocalDate datePurchased, int userId) {
        this.planId = planId;
        this.planType = planType;
        this.planDescription = planDescription;
        this.planPrice = planPrice;
        this.datePurchased = datePurchased;
        this.userId = userId;
    }


    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public String getPlanDescription() {
        return planDescription;
    }

    public void setPlanDescription(String planDescription) {
        this.planDescription = planDescription;
    }

    public double getPlanPrice() {
        return planPrice;
    }

    public void setPlanPrice(double planPrice) {
        this.planPrice = planPrice;
    }

    public LocalDate getDatePurchased() {
        return datePurchased;
    }

    public void setDatePurchased(LocalDate datePurchased) {
        this.datePurchased = datePurchased;
    }

    public int getUserId() {
        return userId;
    }

    @Override
    public void setUser_id(Integer user_id) {
        super.setUser_id(user_id);
    }

    @Override
    public String toString() {
        return ("Plan type: " + this.planType + ", " + "Description: " + this.planDescription + ", " + "Price: " + this.planPrice + ", " + "Date purchased: " +  this.datePurchased + ", " + "User ID: " + this.userId);
    }
}
