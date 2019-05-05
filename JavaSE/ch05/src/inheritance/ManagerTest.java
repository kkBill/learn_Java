package inheritance;

public class ManagerTest {
    public static void main(String[] args){
        Manager boss = new Manager("kkbill",20000,2019,3,29);
        boss.setBonus(5000);

        Employee[] staff = new Employee[3];
        staff[0] = boss;
        staff[1] = new Employee("Harry",15000,2018,10,2);
        staff[2] = new Employee("Tony",16000,2015,6,25);

        for(Employee e : staff){
            System.out.println("name: " + e.getName() + " salary: " + e.getSalary());
        }
    }
}
