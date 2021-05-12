package com.project.garage.models.serviceObjects;

import com.project.garage.models.enums.FuelType;
import lombok.Data;

@Data
public class SubOrderWithSpecialConditions {
    private String brand;
    private FuelType fuelType;
    private boolean is_male;


    public SubOrderWithSpecialConditions(String brand, FuelType fuelType, boolean is_male) {
        this.brand = brand;
        this.fuelType = fuelType;
        this.is_male = is_male;
    }
}
