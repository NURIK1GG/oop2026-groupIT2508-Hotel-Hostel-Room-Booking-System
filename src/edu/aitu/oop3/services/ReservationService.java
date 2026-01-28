package edu.aitu.oop3.services;

import edu.aitu.oop3.entities.Reservation;
import edu.aitu.oop3.entities.Room;
import edu.aitu.oop3.exceptions.RoomNotAvailableException;
import edu.aitu.oop3.repositories.interfaces.IReservationRepository;
import edu.aitu.oop3.repositories.interfaces.IRoomRepository;

import java.sql.Date;

public class ReservationService {
    private final IReservationRepository reservationRepo;
    private final IRoomRepository roomRepo;

    public ReservationService(IReservationRepository reservationRepo, IRoomRepository roomRepo) {
        this.reservationRepo = reservationRepo;
        this.roomRepo = roomRepo;
    }

    public String makeReservation(int guestId, int roomId, String startDate, String endDate) {
        try {
            Room room = roomRepo.getRoomById(roomId);
            if (room == null) {
                return "Error: Room not found.";
            }

            Date start = Date.valueOf(startDate);
            Date end = Date.valueOf(endDate);

            if (start.after(end)) {
                return "Error: Start date cannot be after end date.";
            }

            Reservation reservation = new Reservation(guestId, roomId, start, end);
            boolean created = reservationRepo.createReservation(reservation);

            if (created) {
                return "Reservation created successfully!";
            } else {
                return "Reservation failed.";
            }

        } catch (IllegalArgumentException e) {
            return "Error: Invalid date format. Use YYYY-MM-DD";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}