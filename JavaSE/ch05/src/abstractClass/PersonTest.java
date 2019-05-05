package abstractClass;

public class PersonTest {
    public static void main(String[] args){
        Person[] people = new Person[2];
        people[0] = new Employee("Harry", 20000,1994, 12,15);
        people[1] = new Student("kkbill","Computer Science");

        for(Person p : people){
            System.out.println(p.getName() + ": " +p.getDescription());
        }
    }
}
