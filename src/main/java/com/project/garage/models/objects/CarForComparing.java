package com.project.garage.models.objects;

import com.project.garage.models.objects.CarResult;

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
