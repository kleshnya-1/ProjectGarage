import lombok.Data;

@Data
public class Person {

    int id ;
    String name;
            int age;
    String email ;

    public Person(int id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }
}
