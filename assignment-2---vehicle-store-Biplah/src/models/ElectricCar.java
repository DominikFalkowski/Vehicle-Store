package models;

import utils.Utilities;

import java.util.Objects;

public class ElectricCar extends Car{

    private float engineKWatts = 40;

    private int range = 100;

    public ElectricCar(String regNumber, String model, float cost, Manufacturer manufacturer, int year, int power,int sec0To60, int topSpeed, float torque, float engineKWatts, int range) {
        super(regNumber, model, cost, manufacturer, year, sec0To60, topSpeed, power, torque);
        this.engineKWatts = engineKWatts;
        this.range = range;
    }

    public float getEngineKWatts() {
        return engineKWatts;
    }

    public void setEngineKWatts(float engineKWatts) {
        if (Utilities.validRange(engineKWatts,40,60,.01F))
        this.engineKWatts = engineKWatts;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        if (Utilities.validRange(range,100,500))
        this.range = range;
    }


    @Override
    public double getCarbonFootPrint() {
        return  (engineKWatts * getAge()) /  20000;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ElectricCar that = (ElectricCar) o;
        return Float.compare(that.engineKWatts, engineKWatts) == 0 && range == that.range;
    }

    @Override
    public int hashCode() {
        return Objects.hash(engineKWatts, range);
    }
}