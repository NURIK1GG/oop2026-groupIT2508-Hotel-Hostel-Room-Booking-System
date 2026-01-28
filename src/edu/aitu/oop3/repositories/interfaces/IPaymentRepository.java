package edu.aitu.oop3.repositories.interfaces;

import edu.aitu.oop3.entities.Payment;

public interface IPaymentRepository {
    boolean processPayment(Payment payment);
}