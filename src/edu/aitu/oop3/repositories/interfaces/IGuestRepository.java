package edu.aitu.oop3.repositories.interfaces;

import edu.aitu.oop3.entities.Guest;
import java.util.List;

public interface IGuestRepository {
    boolean createGuest(Guest guest);
    Guest getGuestByEmail(String email);
    List<Guest> getAllGuests();
}