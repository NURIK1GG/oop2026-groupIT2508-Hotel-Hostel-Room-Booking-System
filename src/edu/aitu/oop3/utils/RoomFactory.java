package edu.aitu.oop3.utils;

import edu.aitu.oop3.entities.Room;

public class RoomFactory {
    public static Room create(String type) {
        return switch (type) {
            case "SUITE" -> new Room(1, "Suite", 200);
            case "STANDARD" -> new Room(2, "Standard", 100);
            default -> new Room(3, "Dorm", 50);
        };
    }
}
