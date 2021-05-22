package com.project.garage.models.objects;

import com.project.garage.models.objects.cars.CarResult;

public class CarDriver {

    CarResult carResult;



    public void takeAndDriveTheCar(CarResult c0)   {

        try {
            if (this.carResult != null) {
                throw new Exception
                        ("Водитель уже ведет другую машину");
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        if (this.carResult == null)
        this.carResult = c0;

    }

    public void returnCar(Boolean isOk) {

// пусть идет в гараж и настраивает фалс

        this.carResult = null;
    }
}
