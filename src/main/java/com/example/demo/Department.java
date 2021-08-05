package com.example.demo;

import java.util.ArrayList;

public class Department {
    private String name;
    private ArrayList<Employee> employees;

    public Department() {
        employees = new ArrayList<>();
    }

    public Department(String name, ArrayList<Employee> employees) {
        this.name = name;
        this.employees = employees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    // methods

    public void addEmployee(Employee employee){
        this.getEmployees().add(employee);
    }

    public boolean hasEmployeeById(long id){
        boolean answer = false;
        for (Employee employee: this.getEmployees()){
            if (employee.getId() == id){
                answer = true;
                break;
            }
        }
        return answer;
    }

    public Employee findEmployeeById(long id){
        Employee tempEmployee = null;
        for (Employee employee: this.getEmployees()){
            if (employee.getId() == id){
                tempEmployee = employee;
                break;
            }
        }
        return tempEmployee;
    }
}
