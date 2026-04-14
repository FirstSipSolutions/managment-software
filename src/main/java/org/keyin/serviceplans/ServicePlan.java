package org.keyin.serviceplans;

import org.keyin.user.User;

import java.time.LocalDate;

public class ServicePlan extends User {
    // Instance variables
    private String planType;
    private String planDescription;
    private Float planPrice;
    LocalDate datePurchased = LocalDate.now();
    private int userId;

    //Constructors
    public ServicePlan(){
    }

    public ServicePlan(String planType, String planDescription, Float planPrice, LocalDate datePurchased, int userId) {
        this.planType = planType;
        this.planDescription = planDescription;
        this.planPrice = planPrice;
        this.datePurchased = datePurchased;
        this.userId = userId;
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

    public Float getPlanPrice() {
        return planPrice;
    }

    public void setPlanPrice(Float planPrice) {
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
        return ("Plan type: " + this.planType + ", " + "Description: " + this.planDescription + ", " + " Price: " + this.planPrice + ",  " + " Date purchased:" +  this.datePurchased + ", " + " User ID: " + this.userId);
    }
}
