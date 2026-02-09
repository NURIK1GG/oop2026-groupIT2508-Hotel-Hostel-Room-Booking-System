package edu.aitu.oop3.repositories;

import edu.aitu.oop3.db.PostgresDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class RoomAvailabilityRepository {

    public boolean isAvailable(int roomId, LocalDate from, LocalDate to) {

        String sql = """
            SELECT COUNT(*) FROM reservations
            WHERE room_id = ?
            AND NOT (date_to < ? OR date_from > ?)
        """;

        try (Connection c = PostgresDatabase.getInstance().getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, roomId);
            ps.setDate(2, java.sql.Date.valueOf(from));
            ps.setDate(3, java.sql.Date.valueOf(to));

            ResultSet rs = ps.executeQuery();
            rs.next();

            return rs.getInt(1) == 0;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
