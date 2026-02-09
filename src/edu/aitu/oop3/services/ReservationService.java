package edu.aitu.oop3.services;

import edu.aitu.oop3.repositories.ReservationRepository;
import edu.aitu.oop3.repositories.RoomAvailabilityRepository;
import edu.aitu.oop3.utils.InvalidDateException;
import edu.aitu.oop3.utils.RoomNotAvailableException;

import java.time.LocalDate;

public class ReservationService {

    private final ReservationRepository reservationRepo = new ReservationRepository();
    private final RoomAvailabilityRepository availabilityRepo = new RoomAvailabilityRepository();

    public void create(int guestId, int roomId, LocalDate from, LocalDate to) {

        if (from.isAfter(to)) {
            throw new InvalidDateException("Start date is after end date");
        }

        if (!availabilityRepo.isAvailable(roomId, from, to)) {
            throw new RoomNotAvailableException("Room is not available for selected dates");
        }

        reservationRepo.save(guestId, roomId, from, to);
    }
}
