package edu.aitu.oop3.repositories;

import edu.aitu.oop3.db.PostgresDatabase;
import edu.aitu.oop3.entities.Guest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GuestRepository {

    public void save(Guest guest) {
        String sql = "INSERT INTO guests(name, email) VALUES (?, ?)";
        try (Connection c = PostgresDatabase.getInstance().getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, guest.getName());
            ps.setString(2, guest.getEmail());
            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Guest> findAll() {
        List<Guest> guests = new ArrayList<>();
        String sql = "SELECT * FROM guests";

        try (Connection c = PostgresDatabase.getInstance().getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                guests.add(new Guest(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email")
                ));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return guests;
    }
}
