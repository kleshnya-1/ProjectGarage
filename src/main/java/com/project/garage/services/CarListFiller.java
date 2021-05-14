package com.project.garage.services;

import com.project.garage.dao.CarResult;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarListFiller {

    FuelTupeConverter fuelTupeConverter = new FuelTupeConverter();

    public List<CarResult> CarListFiller(ResultSet resultSet) throws SQLException {
        List<CarResult> carList = new ArrayList<>();

        while (resultSet.next()) {
            CarResult carResult = new CarResult();
            carResult.setBrand(resultSet.getString("brand"));
            carResult.setModel(resultSet.getString("model"));
            carResult.setDriverName(resultSet.getString("name"));
            carResult.setDriver_id(resultSet.getInt("driver_id"));
            carResult.setEarnedThisWeek(resultSet.getInt("earned_this_week"));
            carResult.setFuelType(fuelTupeConverter.fuelTypeChooser(resultSet.getString("fuel")));
            carResult.setConsumption(resultSet.getInt("consumption"));
            carResult.setConsumption_loaded(resultSet.getInt("consumption_loaded"));
            carResult.setReliabilityInPercents(resultSet.getInt("reliability_%"));
            carResult.setFuel_in_tank(resultSet.getInt("fuel_in_tank"));
            carResult.setTankCapacity(resultSet.getInt("tank_capacity"));

            carResult.setSeats(resultSet.getInt("seats"));
            carResult.setCarryingKG(resultSet.getInt("carrying"));


            //System.out.println(resultSet.getString("fuel"));
            carList.add(carResult);
        }
        return carList;
    }



}
