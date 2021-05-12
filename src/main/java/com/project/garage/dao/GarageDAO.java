package com.project.garage.dao;


import com.project.garage.models.objects.Car;
import com.project.garage.models.objects.ExpectedCar;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GarageDAO {

    private static String URL = "jdbc:postgresql://localhost:5432/project_garage_database";
    private static String USERNAME = "postgres";
    private static String PASSWORD = "1";
    private static Connection connection;

    static {
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
    }

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

    public List<Car>  getCarListExpectedCars(ExpectedCar expectedCar){
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