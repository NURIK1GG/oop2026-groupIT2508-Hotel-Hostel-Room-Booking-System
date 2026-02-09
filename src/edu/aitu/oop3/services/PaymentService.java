package edu.aitu.oop3.services;

import edu.aitu.oop3.db.PostgresDatabase;
import edu.aitu.oop3.utils.PaymentFailedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PaymentService {

    public void pay(Connection conn, int reservationId, double amount) {
        if (amount <= 0) {
            throw new PaymentFailedException("Invalid payment amount");
        }

        String sql = """
            INSERT INTO payments(reservation_id, amount, paid_at)
            VALUES (?, ?, now())
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, reservationId);
            ps.setDouble(2, amount);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new PaymentFailedException("Payment failed");
        }
    }
}
