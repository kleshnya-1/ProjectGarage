import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class carsDAO {


  /*  private static String URL= "jdbc:postgresql://localhost:5432/test01";
    private static String USERNAME= "postgres";
    private static String PASSWORD= "1";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        try {
            connection = DriverManager.getConnection(URL, USERNAME,PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }*/



/*    public List<Car> find2ndWatch(){
        List<Car> carList= new ArrayList<>();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement
                    ("select * from cars where driver_id =2 and ");
          *//*  preparedStatement.setInt(1, 12);
            preparedStatement.setString(2,"Jimmy");
            preparedStatement.setInt(3, 45);
            preparedStatement.setString(4,"Jimmy@inthe");*//*
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Car car = new Car();
                car.setBrand(resultSet.getString("brand"));
                car.setConsumption(resultSet.getInt("consumption"));
               // car.setConsumption();
                carList.add(car);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
return carList;*/

    }



}
