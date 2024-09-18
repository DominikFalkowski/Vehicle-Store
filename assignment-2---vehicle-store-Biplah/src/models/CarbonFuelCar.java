package models;

import utils.FuelTypeUtility;
import utils.Utilities;

import java.util.Objects;

public class CarbonFuelCar extends Car{

    private boolean automatic = false ;

    private float carbonEmission = 1;

    private float fuelConsumption = 5 ;

    private String fuelType;

    private int engineSize = 800;
    public CarbonFuelCar(String regNumber,String model,float cost,Manufacturer manufacturer,int year,int power,int sec0To60, int topSpeed, float torque,String fuelType, float fuelConsumption,float carbonEmission, int engineSize,boolean automatic) {
        super(regNumber, model, cost, manufacturer, year, sec0To60, topSpeed, power, torque);
        this.automatic = automatic;
        this.carbonEmission = carbonEmission;
        this.fuelConsumption = fuelConsumption;
        this.fuelType = fuelType;
        this.engineSize = engineSize;
    }

    public boolean isAutomatic() {
        return automatic;
    }

    public void setAutomatic(boolean automatic) {
        this.automatic = automatic;
    }

    public float getCarbonEmission() {
        return carbonEmission;
    }

    public void setCarbonEmission(float carbonEmission) {
        if (Utilities.validRange(carbonEmission, 0, .01F))
        this.carbonEmission = carbonEmission;
    }

    public float getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(float fuelConsumption) {
        if (Utilities.validRange(fuelConsumption, 5, 20,.01F))
        this.fuelConsumption = fuelConsumption;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
       if (FuelTypeUtility.validFuelType(fuelType))
        this.fuelType = fuelType;
    }


    public int getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(int engineSize) {
        if (Utilities.validRange(engineSize, 800, 2500))
        this.engineSize = engineSize;
    }

    @Override
    public double getCarbonFootPrint() {
        return  (engineSize * fuelConsumption * carbonEmission * getAge()) /  2000;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarbonFuelCar that = (CarbonFuelCar) o;
        return automatic == that.automatic && Float.compare(that.carbonEmission, carbonEmission) == 0 && Float.compare(that.fuelConsumption, fuelConsumption) == 0 && engineSize == that.engineSize && fuelType.equals(that.fuelType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(automatic, carbonEmission, fuelConsumption, fuelType, engineSize);
    }
}
