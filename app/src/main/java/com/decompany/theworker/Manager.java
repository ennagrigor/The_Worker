package com.decompany.theworker;

import java.util.List;

public class Manager extends Employee {
    List<String> listOfManagerEmployeesId;
    String team;

    public Manager(String uid, String name, String position, String email, String phone, String managerName, String department, String linkToPhoto, String password, String kindOfEmployee, String salary, List<String> listOfManagerEmployeesId, String team) {
        super(uid, name, position, email, phone, managerName, department, linkToPhoto, password, kindOfEmployee, salary);
        this.listOfManagerEmployeesId = listOfManagerEmployeesId;
    }

    public Manager() {}

    public List<String> getListOfManagerEmployeesId() {
        return listOfManagerEmployeesId;
    }

    public void setListOfManagerEmployeesId(List<String> listOfManagerEmployeesId) {
        this.listOfManagerEmployeesId = listOfManagerEmployeesId;
    }

    public String getTeam(){
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }
}
