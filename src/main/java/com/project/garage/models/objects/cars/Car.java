package com.project.garage.models.objects.cars;

import com.project.garage.models.enums.FuelType;
import lombok.Data;

@Data
public class Car implements Cloneable {
    private String id;
    private String brand;
    private  String model;
    private boolean is_ok = true;
    private int driver_id;
    private double consumption;
    private FuelType fuelType;
    private double consumption_loaded;
    private double fuel_in_tank;
    private  int reliabilityInPercents;
    private int seats;
    private int maxLoadKg;
    private int tankCapacity;
    private int odometer;


    public Car clone() throws CloneNotSupportedException{

        return (Car) super.clone();
    }





}
