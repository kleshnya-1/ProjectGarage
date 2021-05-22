package com.project.garage.models.objects.cars;

import com.project.garage.models.enums.CarType;
import com.project.garage.models.enums.FuelType;
import lombok.Data;

@Data
public class ExpectedCar {

    private double distanceExpected;
    private int hoursBeforeOrderExpected;
    private int seatsExpected=0;
    private int carryingKgExpected=0;

    private boolean isMaleDriverExpected;
    private String brandExpected;
    private CarType carTypeExpected;
    private FuelType fuelTypeExpected;






}
