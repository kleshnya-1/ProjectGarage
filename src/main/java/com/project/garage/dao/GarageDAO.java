package com.project.garage.dao;


import com.project.garage.models.serviceObjects.SubOrder;
import com.project.garage.models.serviceObjects.SubOrderWithSpecialConditions;
import com.project.garage.services.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class GarageDAO {

   /* private static String URL = "jdbc:postgresql://localhost:5432/project_garage_database";
    private static String USERNAME = "postgres";
    private static String PASSWORD = "1";*/
    Connection connection = new ConnectionMaker().makeConnection();
    WatchCalc watchCalc = new WatchCalc();
    FuelTupeConverter fuelTupeConverter = new FuelTupeConverter();
    CarListFiller carListFiller = new CarListFiller();
    AskerToBaseForCarAndDriver askerToBaseForCarAndDriver = new AskerToBaseForCarAndDriver();

    //   FuelCalc fuelCalc = new FuelCalc();

    /*static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }*/

// придумать
   /* public List<Car>  getDriverAbleList(ExpectedCar expectedCar){
        List<Car> carList = new ArrayList<>();
        try {
            //watch =? and
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("select * from cars where " +
                            " seats >=? and carrying >=? and is_ok = true ");
            //SELECT * FROM course WHERE dept_name='Comp. Sci.' AND credits>3;
            //preparedStatement.setInt(1, 12);
            preparedStatement.setInt(1, expectedCar.getSeatsExpected());
            preparedStatement.setInt(2, expectedCar.getCarryingKgExpected());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Car car = new Car();
                car.setBrand(resultSet.getString("brand"));
                car.setModel(resultSet.getString("model"));

                car.setDriver_id(resultSet.getInt("driver_id"));
                // car.setBrand(resultSet.getString("brand"));
                // car.setConsumption(resultSet.getInt("consumption"));
                // car.setConsumption();
                carList.add(car);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return carList;
    }*/

    public List<CarResult> getCarListSuitableCars(SubOrder subOrder, SubOrderWithSpecialConditions subOrderWithSpecialConditions){
        List<CarResult> carList = new ArrayList<>();
        ResultSet resultSet ;
        try {

            resultSet = askerToBaseForCarAndDriver.AskerToBaseForCarAndDriver(connection, subOrder, subOrderWithSpecialConditions);
               /* PreparedStatement preparedStatement = connection.prepareStatement
                        (" select * " +
                                "from cars left join drivers on cars.driver_id = drivers.id where " +
                                " seats >=? and carrying >=? and is_ok = true and watch=?  " );
                //SELECT * FROM course WHERE dept_name='Comp. Sci.' AND credits>3;
                //preparedStatement.setInt(1, 12);
                preparedStatement.setInt(1, subOrder.getNumOfPass());
                preparedStatement.setInt(2, subOrder.getNumOfKg());
                preparedStatement.setInt(3, watchCalc.returnWatch(subOrder));
                ResultSet resultSet = preparedStatement.executeQuery();*/

            carList = carListFiller.CarListFiller(resultSet);
                /*while (resultSet.next()) {
                    CarResult carResult = new CarResult();
                    carResult.setBrand(resultSet.getString("brand"));
                    carResult.setModel(resultSet.getString("model"));
                    carResult.setDriverName(resultSet.getString("name"));
                    carResult.setDriver_id(resultSet.getInt("driver_id"));
                    carResult.setEarnedThisWeek(resultSet.getInt("earned_this_week"));
                    carResult.setFuelType(fuelTupeConverter.fuelTypeChooser(resultSet.getString("fuel")));
                    System.out.println(resultSet.getString("fuel"));
                    carList.add(carResult);
            }*/

             if (subOrderWithSpecialConditions != null) {
                //code


            }


        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return carList;
    }





   /* public void addCar(ExpectedCar car){
        PreparedStatement preparedStatement = connection.prepareStatement
                ("INSERT INTO cars VALUES (?,?,?,?,?,?,?,?)");
        preparedStatement.setInt();

        preparedStatement.setString();

        preparedStatement.setString();
        preparedStatement.setString();
        preparedStatement.setString();
        preparedStatement.setString();
        preparedStatement.setString();
        preparedStatement.setString();
        preparedStatement.setString();

        private int id;
        private String brand;
        private String model;
        private boolean is_ok;
        private int driver_id;
        private double consumption;
        private FuelType fuelType;
        private double consumption_loaded;
        private double fuel_in_tank;
        private  int reliabilityInPercents;
        private int seats;
        private int carryingKG;


    }*/

 /*   public List<Car> find2ndWatch() {
        List<Car> carList = new ArrayList<>();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement
                    ("select * from cars where driver_id =2 and ");
            preparedStatement.setInt(1, 12);
            preparedStatement.setString(2,"Jimmy");
            preparedStatement.setInt(3, 45);
            preparedStatement.setString(4,"Jimmy@inthe");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Car car = new Car();
                car.setBrand(resultSet.getString("brand"));
                car.setConsumption(resultSet.getInt("consumption"));
                // car.setConsumption();
                carList.add(car);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return carList;

    }*/
}