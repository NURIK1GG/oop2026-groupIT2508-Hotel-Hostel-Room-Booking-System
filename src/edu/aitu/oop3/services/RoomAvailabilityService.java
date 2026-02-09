package edu.aitu.oop3.services;

import edu.aitu.oop3.entities.Room;
import java.util.List;

public class RoomAvailabilityService {

    public List<Room> filterCheapRooms(List<Room> rooms, double maxPrice) {
        return rooms.stream()
                .filter(r -> r.getPrice() <= maxPrice)
                .toList();
    }
}
