package edu.aitu.oop3.repositories;

import edu.aitu.oop3.db.PostgresDatabase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ReservationViewRepository {

    public void listAll() {

        String sql = """
            SELECT r.id,
                   g.name AS guest,
                   rm.type AS room,
                   r.date_from,
                   r.date_to
            FROM reservations r
            JOIN guests g ON r.guest_id = g.id
            JOIN rooms rm ON r.room_id = rm.id
            ORDER BY r.id
        """;

        try (Connection c = PostgresDatabase.getInstance().getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("guest") + " | " +
                                rs.getString("room") + " | " +
                                rs.getDate("date_from") + " → " +
                                rs.getDate("date_to")
                );
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
