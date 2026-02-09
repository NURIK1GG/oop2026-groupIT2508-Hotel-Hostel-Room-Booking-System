package edu.aitu.oop3.utils;

import edu.aitu.oop3.entities.*;
import java.time.LocalDate;

public class ReservationBuilder {
    private Guest guest;
    private Room room;
    private LocalDate from;
    private LocalDate to;

    public ReservationBuilder guest(Guest guest) {
        this.guest = guest;
        return this;
    }

    public ReservationBuilder room(Room room) {
        this.room = room;
        return this;
    }

    public ReservationBuilder dates(LocalDate from, LocalDate to) {
        this.from = from;
        this.to = to;
        return this;
    }

    public Reservation build() {
        return new Reservation(guest, room, from, to);
    }
}
