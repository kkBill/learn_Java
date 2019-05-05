package clone;

public class CloneTest {
    public static void main(String[] args) {
        try{
            Employee original = new Employee("kkbll", 20000);
            original.setHireDay(2019,3,28);
            Employee copy = original.clone();
            copy.raiseSalary(10);
            copy.setHireDay(2017,6,15);
            System.out.println("original: " + original);
            System.out.println("copy: " + copy);

        }catch ( CloneNotSupportedException e){
            e.printStackTrace();
        }
    }
}
