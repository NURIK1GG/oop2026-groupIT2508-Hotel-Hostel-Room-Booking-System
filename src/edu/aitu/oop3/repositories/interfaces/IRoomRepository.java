package edu.aitu.oop3.repositories.interfaces;

import edu.aitu.oop3.entities.Room;
import java.util.List;

public interface IRoomRepository {
    List<Room> getAllRooms();
    Room getRoomById(int id);
}