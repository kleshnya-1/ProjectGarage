package com.project.garage.models.objects.cars;

import com.project.garage.models.objects.cars.CarResult;

public class CarForComparing extends CarResult {

    double suitableIndex;//определяет степень
    double priceForOrder;



    public void setSuitableIndex(double suitableIndex) {
        this.suitableIndex = suitableIndex;
    }

    public void setPriceForOrder(double priceForOrder) {
        this.priceForOrder = priceForOrder;
    }
    // double

}
