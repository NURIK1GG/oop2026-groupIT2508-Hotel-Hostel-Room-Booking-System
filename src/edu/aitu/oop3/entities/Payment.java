package edu.aitu.oop3.entities;

public class Payment {
    private double amount;
    private boolean success;

    public Payment(double amount, boolean success) {
        this.amount = amount;
        this.success = success;
    }

    public boolean isSuccess() { return success; }
}
