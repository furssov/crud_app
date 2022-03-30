package furssov.spring.model;




import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {

    @NotEmpty(message = "this field cant be empty")
    @Size(min = 2, max = 15, message = "name should be between 2 and 15")
    private String name;

    @Email(message = "email is not valid")
    @NotEmpty(message = "this field cant be empty")
    private String email;

   @Min(value = 0, message = "min age is 0")
    private int age;


    private int id;


    public Person(String name, int age, String email, int id) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.id = id;
    }

        public Person(){}

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
