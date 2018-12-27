package com.decompany.theworker;

import java.util.List;

public class Hr extends Employee {
    List<String> listOfAllEmployeesId;

    public Hr(String uid, String name, String position, String email, String phone, String managerName, String department, String linkToPhoto, String password, String kindOfEmployee, String salary, List<String> listOfAllEmployeesId) {
        super(uid, name, position, email, phone, managerName, department, linkToPhoto, password, kindOfEmployee, salary);
        this.listOfAllEmployeesId = listOfAllEmployeesId;
    }

    public Hr(){}

    public List<String> getListOfAllEmployeesId() {
        return listOfAllEmployeesId;
    }

    public void setListOfAllEmployeesId(List<String> listOfAllEmployeesId) {
        this.listOfAllEmployeesId = listOfAllEmployeesId;
    }
}

