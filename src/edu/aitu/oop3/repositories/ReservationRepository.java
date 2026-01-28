package edu.aitu.oop3.repositories;

import edu.aitu.oop3.db.IDB;
import edu.aitu.oop3.entities.Reservation;
import edu.aitu.oop3.repositories.interfaces.IReservationRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationRepository implements IReservationRepository {
    private final IDB db;

    public ReservationRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createReservation(Reservation reservation) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "INSERT INTO reservations(guest_id, room_id, start_date, end_date) VALUES (?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, reservation.getGuestId());
            st.setInt(2, reservation.getRoomId());
            st.setDate(3, reservation.getStartDate());
            st.setDate(4, reservation.getEndDate());

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
    public List<Reservation> getReservationsByGuestId(int guestId) {
        Connection con = null;
        List<Reservation> reservations = new ArrayList<>();
        try {
            con = db.getConnection();
            String sql = "SELECT * FROM reservations WHERE guest_id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, guestId);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                reservations.add(new Reservation(
                        rs.getInt("guest_id"),
                        rs.getInt("room_id"),
                        rs.getDate("start_date"),
                        rs.getDate("end_date")
                ));
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
        return reservations;
    }
}