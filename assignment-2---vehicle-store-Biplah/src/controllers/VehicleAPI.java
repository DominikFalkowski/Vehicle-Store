package controllers;

import models.*;
import utils.FuelTypeUtility;
import utils.Serializer;


import java.io.*;
import java.util.*;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


public class VehicleAPI implements Serializer {
    private List<Vehicle> vehicles = new ArrayList<>();

    private File file;

    public VehicleAPI(File file) {
    }

    public String listAllCarbonFuelsByFuelType(String fuelType) {
        String str = "";

        if (FuelTypeUtility.validFuelType(fuelType)) {
            for (Vehicle vehicle : vehicles) {
                if (vehicle instanceof CarbonFuelCar)
                    if (((CarbonFuelCar) vehicle).getFuelType().equalsIgnoreCase(fuelType))
                        str += vehicles.indexOf(vehicle) + ": " + vehicle + "\n";
            }
        }

        if (str.isEmpty()) {
            return "No Carbon fuel cars with this fuel type";
        } else {
            return str;
        }
    }

    public String listAllVehiclesAfterAGivenYear(int year) {
        String str = "";

        for (Vehicle vehicle : vehicles) {
            if (vehicle.getYear() >= (year))
                str += vehicles.indexOf(vehicle) + ": " + vehicle + "\n";
        }

        if (str.isEmpty()) {
            return "No Vehicles in this year";
        } else {
            return str;
        }
    }

    public String listAllVehiclesEqualToAGivenYear(int year) {
        String str = "";

        for (Vehicle vehicle : vehicles) {
            if (vehicle.getYear() == (year))
                str += vehicles.indexOf(vehicle) + ": " + vehicle + "\n";
        }

        if (str.isEmpty()) {
            return "No Vehicles in this year";
        } else {
            return str;
        }
    }

    public Vehicle getVehicleByRegNumber(String regNumber) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getRegNumber().equals(regNumber)) {
                return vehicle;
            }
        }
        return null;
    }

    public boolean upDateCarbonFuelCar(String regNumber, CarbonFuelCar updatedCarbonFuelCar) {
        Vehicle vehicle = getVehicleByRegNumber(regNumber);

        if ((vehicle != null) && (vehicle instanceof Scooter)) {
            vehicle.setRegNumber(String.valueOf(updatedCarbonFuelCar.getRegNumber()));
            return true;
        }
        return false;
    }

    public boolean upDateScooter(String regNumber, Scooter updatedScooter) {
        Vehicle vehicle = getVehicleByRegNumber(regNumber);

        if ((vehicle != null) && (vehicle instanceof Scooter)) {
            vehicle.setRegNumber(updatedScooter.getRegNumber());
            return true;
        }
        return false;
    }

    public Vehicle findVehicle(int index) {
        if (isValidIndex(index)) {
            return vehicles.get(index);
        }
        return null;
    }

    public int numberOfScooters() {
        int number = 0;
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Scooter) {
                number++;
            }
        }
        return number;
    }


    public String listAllElectricCars() {
        String str = "";

        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof ElectricCar)
                str += vehicles.indexOf(vehicle) + ": " + vehicle + "\n";
        }

        if (str.isEmpty()) {
            return "No Electric Cars";
        } else {
            return str;
        }
    }

    public String listAllScooters() {
        String str = "";

        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Scooter)
                str += vehicles.indexOf(vehicle) + ": " + vehicle + "\n";
        }

        if (str.isEmpty()) {
            return "No Scooters";
        } else {
            return str;
        }
    }

    public String listAllCarbonFuelCars() {
        String str = "";

        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof CarbonFuelCar)
                str += vehicles.indexOf(vehicle) + ": " + vehicle + "\n";
        }

        if (str.isEmpty()) {
            return "No Carbon Fuel Cars";
        } else {
            return str;
        }
    }


    public Vehicle getVehicleByIndex(int index) {
        if (isValidIndex(index)) {
            return vehicles.get(index);
        }
        return null;
    }


    public int numberOfCarbonCars() {
        int number = 0;
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof CarbonFuelCar) {
                number++;
            }
        }
        return number;
    }

    public String listAllVehicles() {
        String str = "";

        for (Vehicle vehicle : vehicles) {
            str += vehicles.indexOf(vehicle) + ": " + vehicle + "\n";
        }

        if (str.isEmpty()) {
            return "No Vehicles";
        } else {
            return str;
        }
    }


    public List<Vehicle> topFiveCarbonVehicles() {

        sortByCarbonFootprintAscending();

        for (int j = 5; j < vehicles.size(); j++) {
            }
        return vehicles;
    }

    public Vehicle deleteVehiclesByRegNumber(String regNumber) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getRegNumber().equals(regNumber)) {
                return vehicles.remove(vehicles.indexOf(vehicle));
            }
        }
        return null;
    }

    public void sortByCostDescending() {
        for (int i = vehicles.size() - 1; i <= 0; i--) {
            int lowestIndex = 0;
            for (int j = 0; j >= i; j++) {
                if (vehicles.get(j).getCost() < vehicles.get(lowestIndex).getCost()) {
                    lowestIndex = j;
                }
            }
            swapProducts((ArrayList<Vehicle>) vehicles, i, lowestIndex);
        }
    }

    public void sortByAgeAscending() {
        for (int i = vehicles.size() - 1; i >= 0; i--) {
            int highestIndex = 0;
            for (int j = 0; j <= i; j++) {
                if (vehicles.get(j).getAge() > vehicles.get(highestIndex).getAge()) {
                    highestIndex = j;
                }
            }
            swapProducts((ArrayList<Vehicle>) vehicles, i, highestIndex);
        }
    }

    public void sortByCarbonFootprintAscending() {
        for (int i = vehicles.size() - 1; i >= 0; i--) {
            int highestIndex = 0;
            for (int j = 0; j <= i; j++) {
                if (vehicles.get(j).getCarbonFootPrint() > (vehicles.get(highestIndex).getCarbonFootPrint())) {
                    highestIndex = j;
                }
            }
            swapProducts((ArrayList<Vehicle>) vehicles, i, highestIndex);
        }
    }

    public void sortByCarbonFootPrintDescending() {
        for (int i = vehicles.size() - 1; i <= 0; i--) {
            int lowestIndex = 0;
            for (int j = 0; j >= i; j++) {
                if (vehicles.get(j).getCarbonFootPrint() < (vehicles.get(lowestIndex).getCarbonFootPrint())){
                    lowestIndex = j;
                }
            }
            swapProducts((ArrayList<Vehicle>) vehicles, i, lowestIndex);
        }
    }

    private void swapProducts(ArrayList<Vehicle> vehicles, int i, int j) {
        Vehicle smaller = vehicles.get(i);
        Vehicle bigger = vehicles.get(j);

        vehicles.set(i, bigger);
        vehicles.set(j, smaller);
    }

    public int numberOfVehicles() {
        return vehicles.size();
    }

    public int numberOfElectricCars() {
        int number = 0;
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof ElectricCar) {
                number++;
            }
        }
        return number;
    }

    public boolean addVehicle(Vehicle vehicle) {
        return vehicles.add(vehicle);
    }


    public void listAllVehicleByChosenManufacturer(Manufacturer manufacturer) {
        String str = "";

        for (Vehicle vehicle : vehicles) {
            if (vehicle.getManufacturer().equals(manufacturer))
                str += vehicles.indexOf(vehicle) + ": " + vehicle + "\n";
        }
    }

    public Vehicle deleteVehicleByIndex(int indexToDelete) {
        if (isValidIndex(indexToDelete)) {
            return vehicles.remove(indexToDelete);
        }
        return null;
    }

    public boolean isValidNewRegNumber(String regNumber) {
        for (Vehicle vehicle : vehicles)
            if (vehicle.getRegNumber().equals(regNumber))
                return false;
        return true;
    }


    //---------------------
    // Persistence methods
    //---------------------

    /**
     * The load method uses the XStream component to read all the objects from the xml
     * file stored on the hard disk.  The read objects are loaded into the associated ArrayList
     *
     * @throws Exception An exception is thrown if an error occurred during the load e.g. a missing file.
     */
    @SuppressWarnings("unchecked")
    public void load() throws Exception {
        //list of classes that you wish to include in the serialisation, separated by a comma
        Class<?>[] classes = new Class[]{Vehicle.class, Car.class, CarbonFuelCar.class,
                ElectricCar.class, Scooter.class, Manufacturer.class};

        //setting up the xstream object with default security and the above classes
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        //doing the actual serialisation to an XML file
        ObjectInputStream in = xstream.createObjectInputStream(new FileReader(file));
        vehicles = (List<Vehicle>) in.readObject();
        in.close();
    }

    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter(file));
        out.writeObject(vehicles);
        out.close();
    }

    public String fileName() {
        return this.file.toString();
    }

    public boolean isValidIndex(int index) {
        return (index >= 0) && (index < vehicles.size());
    }

}
