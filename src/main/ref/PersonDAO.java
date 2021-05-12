import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {


    private static String URL= "jdbc:postgresql://localhost:5432/test01";
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
    }



    public List<Person> index(){
        List<Person> personList = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Person";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()){
                System.out.println(resultSet.getString("name"));
            }




        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return personList;
    }

    public void save( ){
        try {
            Statement statement = connection.createStatement();
            //String SQL = "INSERT INTO Person "
            PreparedStatement preparedStatement = connection.prepareStatement("insert into person values (?,?,?,?)");
            preparedStatement.setInt(1, 12);
            preparedStatement.setString(2,"Jimmy");
            preparedStatement.setInt(3, 45);
            preparedStatement.setString(4,"Jimmy@inthe");
            preparedStatement.executeUpdate();




        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    } public void update(int id, Person upPerson){
        try {
            Statement statement = connection.createStatement();
            //String SQL = "INSERT INTO Person "
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Person SET name=?,  age=?,  email=? WHERE id=?");
            preparedStatement.setString(1, upPerson.getName());
            preparedStatement.setInt(2, upPerson.getAge());
            preparedStatement.setString(3, upPerson.getEmail());
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }







}
