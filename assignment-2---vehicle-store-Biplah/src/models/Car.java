package models;

import utils.Utilities;

import java.util.Objects;
import models.CarbonFuelCar;

public abstract class Car extends Vehicle{

    private int sec0To60 = 4;

    private int topSpeed= 50;

    private int power= 120;

    private float torque = 100;


    public Car (String regNumber,String model,float cost,Manufacturer manufacturer,int year,int sec0To60, int topSpeed, int power, float torque) {
        super(regNumber, model, cost, manufacturer, year);
        this.sec0To60 = sec0To60;
        this.topSpeed = topSpeed;
        this.power = power;
        this.torque = torque;
    }

    public int getSec0To60() {
        return sec0To60;
    }

    public void setSec0To60(int sec0To60) {
        this.sec0To60 = sec0To60;
    }

    public int getTopSpeed() {
        return topSpeed;
    }

    public void setTopSpeed(int topSpeed) {
        if (Utilities.validRange(topSpeed, 50, 3000 ))
        this.topSpeed = topSpeed;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        if (Utilities.validRange(power, 120, 300 ))
        this.power = power;
    }

    public float getTorque() {
        return torque;
    }


    public void setTorque(float torque) {
        if (Utilities.validRange(torque, 100,400, .01F ))
        this.torque = torque;
    }

    public abstract double getCarbonFootPrint();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return sec0To60 == car.sec0To60 && topSpeed == car.topSpeed && power == car.power && Float.compare(car.torque, torque) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sec0To60, topSpeed, power, torque);
    }
}
