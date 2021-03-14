package GB.Lesson5;

import java.lang.reflect.Field;

public class Main {
    public static void main(String[] args) {
        var employees = new Employee[5];
        employees[0] = new Employee("Ivanov Ivan", "Engineer", "ivivan@mailbox.com", "892312312", 30000, 30);
        employees[1] = new Employee("Petrov Ivan", "Engineer2", "secondIvan@mailbox.com", "892312312", 20000, 50);
        employees[2] = new Employee("Sidorov Ivan", "Engineer3", "thirdIvan@mailbox.com", "892312312", 40000, 45);
        employees[3] = new Employee("Nikonov Ivan", "Engineer4", "fourthIvan@mailbox.com", "892312312", 50000, 40);
        employees[4] = new Employee("Fedorov Ivan", "Engineer5", "fifthIvan@mailbox.com", "892312312", 60000, 60);

        for (Employee employee : employees) {
            if (employee.get_age() > 40) System.out.println(employee.getInfo());
        }
    }
}
