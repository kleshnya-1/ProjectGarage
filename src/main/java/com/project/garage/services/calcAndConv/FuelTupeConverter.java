package com.project.garage.services.calcAndConv;

import com.project.garage.models.enums.FuelType;

public class FuelTupeConverter {




    public FuelType fuelTypeChooser(String s){
        FuelType forReturn = null;
        switch (s){
            case ("gasoline"):
                forReturn = FuelType.gasoline;
                break;

            case ("gas"):
                forReturn = FuelType.gas;
                break;

            case ("diesel"):
                forReturn = FuelType.diesel;
                break;

            case ("electric"):
                forReturn = FuelType.electric;
                break;
        }
        return forReturn;
    }
}
