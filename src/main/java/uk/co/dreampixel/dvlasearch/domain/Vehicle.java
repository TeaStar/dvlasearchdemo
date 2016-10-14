package uk.co.dreampixel.dvlasearch.domain;

import lombok.Data;

@Data
public class Vehicle {

    private String make;
    private String model;
    private String vin;
    private Boolean taxed;
    private Boolean mot;
     private String dateOfFirstRegistration;

// commented out to demonstrate change
//    private String fuelType;
//    private String co2Emissions;
//    private String dateOfFirstRegistration;
//    private String yearOfManufacture;
//    private String cylinderCapacity;
//    private String taxStatus;
//    private String colour;
//    private String typeApproval;
//    private String wheelPlan;
//    private String revenueWeight;
//    private String taxDetails;
//    private String motDetails;
//    private String transmission;
//    private String numberOfDoors;
//    private String sixMonthRate;
//    private String twelveMonthRate;
}
