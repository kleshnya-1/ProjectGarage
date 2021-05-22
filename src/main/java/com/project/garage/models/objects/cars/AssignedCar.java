package com.project.garage.models.objects.cars;

import com.project.garage.models.suppObjects.TimeInterval;
import lombok.Data;


@Data
public class AssignedCar  {

    private String order;
    private double priceForOrder;
    private int numOfElements;
    TimeInterval timeInterval;
    CarResult carResultInAssignedCar;

    public AssignedCar(CarResult carResultInAssignedCar) {
        this.carResultInAssignedCar = carResultInAssignedCar;

    }

    public AssignedCar( ) {

    }

//    public void set(CarResult c){
//        this. = (AssignedCar)c;
//    }



}
