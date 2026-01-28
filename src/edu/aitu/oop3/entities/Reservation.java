package edu.aitu.oop3.entities;

import java.sql.Date;

public class Reservation {
    private int id;
    private int guestId;
    private int roomId;
    private Date startDate;
    private Date endDate;

    public Reservation(int guestId, int roomId, Date startDate, Date endDate) {
        this.guestId = guestId;
        this.roomId = roomId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getGuestId() { return guestId; }
    public int getRoomId() { return roomId; }
    public Date getStartDate() { return startDate; }
    public Date getEndDate() { return endDate; }

    @Override
    public String toString() {
        return "Reservation{guestId=" + guestId + ", roomId=" + roomId + ", start=" + startDate + ", end=" + endDate + "}";
    }
}