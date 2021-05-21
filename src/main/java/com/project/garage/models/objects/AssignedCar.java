package com.project.garage.models.objects;

import com.project.garage.models.objects.Car;
import com.project.garage.models.objects.CarResult;
import com.project.garage.models.objects.TimeInterval;
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
