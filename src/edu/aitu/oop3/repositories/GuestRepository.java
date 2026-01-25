package edu.aitu.oop3.repositories;

import edu.aitu.oop3.db.IDB;
import edu.aitu.oop3.entities.Guest;
import edu.aitu.oop3.repositories.interfaces.IGuestRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GuestRepository implements IGuestRepository {
    private final IDB db;

    public GuestRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createGuest(Guest guest) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "INSERT INTO guests(name, email, phone) VALUES (?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, guest.getName());
            st.setString(2, guest.getEmail());
            st.setString(3, guest.getPhone());

            st.execute();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public Guest getGuestByEmail(String email) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT * FROM guests WHERE email = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, email);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Guest(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone")
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<Guest> getAllGuests() {
        Connection con = null;
        List<Guest> guests = new ArrayList<>();
        try {
            con = db.getConnection();
            String sql = "SELECT id, name, email, phone FROM guests";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Guest guest = new Guest(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone")
                );
                guests.add(guest);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return guests;
    }
}