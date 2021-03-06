package com.project.garage.models.suppObjects;

import lombok.Data;

@Data
public class EconomicBlock implements Comparable<EconomicBlock> {


    private  double suitableIndex=0;//определяет степень
    private double priceForOrder;
    private double driverEarnedThisWeek;

    public EconomicBlock(double suitableIndex, double priceForOrder, double driverEarnedThisWeek) {
        this.suitableIndex = suitableIndex;
        this.priceForOrder = priceForOrder;
        this.driverEarnedThisWeek = driverEarnedThisWeek;

    }


    public void setSuitableIndex(double suitableIndex) {
        this.suitableIndex = suitableIndex;
    }

    public void setPriceForOrder(double priceForOrder) {
        this.priceForOrder = priceForOrder;
    }
    public void setDriverEarnedThisWeek(double driverEarnedThisWeek) {
        this.driverEarnedThisWeek = driverEarnedThisWeek;
    }

    @Override
    public int compareTo(EconomicBlock o) {

//        if (this.suitableIndex == 0 || o.suitableIndex==0){
//            if (this.priceForOrder<o.priceForOrder) return -1;
//            if (this.priceForOrder>=o.priceForOrder) return 1;
//            return 0;
//        }
//        else {
            if (this.suitableIndex<o.suitableIndex) return 1 ;
            if (this.suitableIndex>=o.suitableIndex) return -1;

            else  return 0;
        //}


    }




    }



