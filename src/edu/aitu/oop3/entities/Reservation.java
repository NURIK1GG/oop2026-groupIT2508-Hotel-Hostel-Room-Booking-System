package edu.aitu.oop3.entities;

import java.time.LocalDate;

public class Reservation {
    private Guest guest;
    private Room room;
    private LocalDate from;
    private LocalDate to;

    public Reservation(Guest guest, Room room, LocalDate from, LocalDate to) {
        this.guest = guest;
        this.room = room;
        this.from = from;
        this.to = to;
    }

    public Guest getGuest() { return guest; }
    public Room getRoom() { return room; }
}
