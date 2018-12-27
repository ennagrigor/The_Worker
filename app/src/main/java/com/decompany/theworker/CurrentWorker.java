package com.decompany.theworker;

public class CurrentWorker {
    private Employee worker = null;

    private CurrentWorker(){}

    public static final CurrentWorker instance = new CurrentWorker();

    public Employee getCurrentWorker() {
        return worker;
    }

    public void setCurrentWorker(Employee employee) {
        this.worker = employee;
    }
}
