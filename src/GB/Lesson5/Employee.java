package GB.Lesson5;

import java.lang.reflect.Field;

public class Employee {
    private  String _name;
    private  String _position;
    private  String _email;
    private  String _phone;
    private  double _salary;
    private  int _age;


    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_position() {
        return _position;
    }

    public void set_position(String _position) {
        this._position = _position;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public String get_phone() {
        return _phone;
    }

    public void set_phone(String _phone) {
        this._phone = _phone;
    }

    public double get_salary() {
        return _salary;
    }

    public void set_salary(double _salary) {
        this._salary = _salary;
    }

    public int get_age() {
        return _age;
    }

    public void set_age(int _age) {
        this._age = _age;
    }

    public Employee(String name, String position, String email, String phone, double salary, int age) {
        this._name = name;
        this._position = position;
        this._email = email;
        this._phone = phone;
        this._salary = salary;
        this._age = age;
    }

    public String getInfo() {
        return "Name: " + _name + ", Position: " + _position + ", Email: " + _email + ", Phone: " + _phone + ", Salary: " + _salary + ", Age: " + _age;
    }
}
