package com.project.garage.services.collectionFillersAndPutters;

import com.project.garage.models.objects.cars.CarResult;
import com.project.garage.services.calcAndConv.FuelTupeConverter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarListFiller {

    FuelTupeConverter fuelTupeConverterToEnum = new FuelTupeConverter();

    public List<CarResult> CarListFiller(ResultSet resultSet) throws SQLException {
        List<CarResult> carList = new ArrayList<CarResult>();

        while (resultSet.next()) {
            CarResult carResult = new CarResult();
            carResult.setId(resultSet.getString("id"));
            carResult.setBrand(resultSet.getString("brand"));
            carResult.setModel(resultSet.getString("model"));
            carResult.setDriverName(resultSet.getString("name"));
            carResult.setDriver_id(resultSet.getInt("driver_id"));
            carResult.setEarnedThisWeek(resultSet.getDouble("earned_this_week"));
            carResult.setFuelType(fuelTupeConverterToEnum.fuelTypeChooser(resultSet.getString("fuel")));
            carResult.setConsumption(resultSet.getInt("consumption"));
            carResult.setConsumption_loaded(resultSet.getInt("consumption_loaded"));
            carResult.setReliabilityInPercents(resultSet.getInt("reliability_%"));
            carResult.setFuel_in_tank(resultSet.getInt("fuel_in_tank"));
            carResult.setTankCapacity(resultSet.getInt("tank_capacity"));
            carResult.setIs_male(resultSet.getBoolean("is_male"));

            carResult.setSeats(resultSet.getInt("seats"));
            carResult.setMaxLoadKg(resultSet.getInt("carrying"));
            carResult.setOdometer(resultSet.getInt("odometer_km"));


            //System.out.println(resultSet.getString("fuel"));
            carList.add(carResult);
        }
        return carList;
    }



}
