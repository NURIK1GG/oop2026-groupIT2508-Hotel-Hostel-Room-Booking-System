package edu.aitu.oop3.repositories;

import edu.aitu.oop3.db.IDB;
import edu.aitu.oop3.entities.Payment;
import edu.aitu.oop3.repositories.interfaces.IPaymentRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class PaymentRepository implements IPaymentRepository {
    private final IDB db;

    public PaymentRepository(IDB db) { this.db = db; }

    @Override
    public boolean processPayment(Payment payment) {
        String sql = "INSERT INTO payments (reservation_id, amount, status) VALUES (?, ?, ?)";
        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {
            st.setInt(1, payment.getReservationId());
            st.setDouble(2, payment.getAmount());
            st.setString(3, payment.getStatus());
            return st.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Payment DB Error: " + e.getMessage());
            return false;
        }
    }
}