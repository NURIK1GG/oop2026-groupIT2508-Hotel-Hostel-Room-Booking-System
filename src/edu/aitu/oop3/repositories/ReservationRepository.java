package edu.aitu.oop3.repositories;

import edu.aitu.oop3.db.PostgresDatabase;

import java.sql.*;
import java.time.LocalDate;

public class ReservationRepository {

    public void save(int guestId, int roomId, LocalDate from, LocalDate to) {
        String sql = """
            INSERT INTO reservations(guest_id, room_id, date_from, date_to)
            VALUES (?, ?, ?, ?)
        """;

        try (Connection c = PostgresDatabase.getInstance().getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, guestId);
            ps.setInt(2, roomId);
            ps.setDate(3, Date.valueOf(from));
            ps.setDate(4, Date.valueOf(to));
            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM reservations WHERE id = ?";
        try (Connection c = PostgresDatabase.getInstance().getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
