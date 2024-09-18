package models;

import utils.Utilities;

import java.util.Objects;

public abstract class Vehicle {

    private String regNumber = "no reg";

    private int year = 2000;

    private float cost = 1000;

    private Manufacturer manufacturer;

    private String model = "No model";

    public Vehicle(String regNumber,String model,float cost, Manufacturer manufacturer,int year) {
        this.regNumber = (Utilities.truncateString(regNumber,8 ));
        setYear(year);
        setCost(cost);
        this.manufacturer = manufacturer;
        this.model = (Utilities.truncateString(model,15 ));
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        if (Utilities.validStringlength(regNumber,8 ))
            this.regNumber = regNumber;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if ((year >= 2000 )&&(year <= 2023))
        this.year = year;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        if (cost >= 1000)
            this.cost = cost;
    }
    public abstract double getCarbonFootPrint();


    public int getAge( ){
      return (2023 - year);
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        if ((Utilities.validStringlength(model,15 )))
        this.model = model;
    }
    @Override
    public String toString(){
        return "Vehicle{" +
                "Reg Number = " + regNumber +
                "Model = " + model +
                "Cost = " + cost +
                "Manufacturer = " + manufacturer.getManufacturerName() + manufacturer.getNumEmployees() +
                "Age = ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle vehicle)) return false;
        return getYear() == vehicle.getYear() && Float.compare(vehicle.getCost(), getCost()) == 0 && getRegNumber().equals(vehicle.getRegNumber()) && getManufacturer().equals(vehicle.getManufacturer()) && getModel().equals(vehicle.getModel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRegNumber(), getYear(), getCost(), getManufacturer(), getModel());
    }
}
