import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {



      /*  PersonDAO personDAO = new PersonDAO();
        //personDAO.save();
        personDAO.index();

        System.out.println();
        Person letsCheck = new Person(3, "ji33m", 65, "some000Email");
        personDAO.update(6, letsCheck);

        personDAO.index();
*/


        carsDAO carsDAO = new carsDAO();

        carsDAO.find2ndWatch();

        System.out.println(carsDAO.find2ndWatch().size());

        List<Car> aa =  carsDAO.find2ndWatch();

        System.out.println();







    }
}
