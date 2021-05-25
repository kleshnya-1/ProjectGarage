package com.project.garage.services.calcAndConv;

import com.project.garage.models.objects.cars.AssignedCar;
import lombok.extern.log4j.Log4j2;

import java.util.Random;

@Log4j2
public class CarBrokingCalculator {

    private int goodOdometer = 600_000;
    private double luckiness = 0.8;
    private boolean decision = false;

    private int reliabilityInPercents;
    private int odometerInTheCar;

    private double chanceForBreaking;
    private int chanceForBreakingOnePer;

    public CarBrokingCalculator(double luckiness) {
        this.luckiness = luckiness;
    }

    public CarBrokingCalculator() {

    }
    public boolean caculateWillTheCarllBeBrokenInTheTrip(AssignedCar car){


        Random randomForBrakingCar = new Random();
        reliabilityInPercents =car.getCarResultInAssignedCar().getReliabilityInPercents();
        odometerInTheCar =car.getCarResultInAssignedCar().getOdometer();
        chanceForBreaking = (1-luckiness)*(1-(double)reliabilityInPercents/100)*
                ((double)odometerInTheCar/(double)goodOdometer);
        chanceForBreakingOnePer = (int)(1/chanceForBreaking);





        log.info("Шанс поломки для "+ car.getCarResultInAssignedCar().getBrand()+" "+
                car.getCarResultInAssignedCar().getModel()+ " составляет "+ chanceForBreaking+
                " или 1/"+((int)(1/chanceForBreaking)));


        if (randomForBrakingCar.nextInt(chanceForBreakingOnePer)==0)
        {
                        decision = true;
                    }
        log.info("Так сломалась ли машина? "+decision);



        return decision;
    }


}
