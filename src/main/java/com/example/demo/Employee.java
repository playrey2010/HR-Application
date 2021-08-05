package com.example.demo;

public class Employee extends User {
    private Department department;

    public Employee(User user){
        this.setFirstName(user.getFirstName());
        this.setLastName(user.getLastName());
        this.setEmail(user.getEmail());
        this.setId(user.getId());
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
    }

    public Employee(){

    }

    public Employee(Department department) {
        this.department = department;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
