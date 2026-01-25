package edu.aitu.oop3;

import edu.aitu.oop3.db.IDB;
import edu.aitu.oop3.db.PostgresDB;
import edu.aitu.oop3.entities.Guest;
import edu.aitu.oop3.entities.Room;
import edu.aitu.oop3.repositories.GuestRepository;
import edu.aitu.oop3.repositories.ReservationRepository;
import edu.aitu.oop3.repositories.RoomRepository;
import edu.aitu.oop3.repositories.interfaces.IGuestRepository;
import edu.aitu.oop3.repositories.interfaces.IReservationRepository;
import edu.aitu.oop3.repositories.interfaces.IRoomRepository;
import edu.aitu.oop3.services.ReservationService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        IDB db = new PostgresDB();
        IGuestRepository guestRepo = new GuestRepository(db);
        IRoomRepository roomRepo = new RoomRepository(db);
        IReservationRepository reservationRepo = new ReservationRepository(db);
        ReservationService reservationService = new ReservationService(reservationRepo, roomRepo);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nHotel Management System");
            System.out.println("1. Register Guest");
            System.out.println("2. View All Guests");
            System.out.println("3. View Available Rooms");
            System.out.println("4. Book Room");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            if (option == 1) {
                System.out.print("Enter name: ");
                String name = scanner.nextLine();
                System.out.print("Enter email: ");
                String email = scanner.nextLine();
                System.out.print("Enter phone: ");
                String phone = scanner.nextLine();

                Guest guest = new Guest(name, email, phone);
                boolean created = guestRepo.createGuest(guest);
                System.out.println(created ? "Guest registered!" : "Error registering guest.");
            } else if (option == 2) {
                List<Guest> guests = guestRepo.getAllGuests();
                for (Guest g : guests) {
                    System.out.println(g);
                }
            } else if (option == 3) {
                List<Room> rooms = roomRepo.getAllRooms();
                for (Room r : rooms) {
                    System.out.println(r);
                }
            } else if (option == 4) {
                System.out.print("Enter Guest ID: ");
                int guestId = scanner.nextInt();
                System.out.print("Enter Room ID: ");
                int roomId = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Start Date (YYYY-MM-DD): ");
                String start = scanner.nextLine();
                System.out.print("End Date (YYYY-MM-DD): ");
                String end = scanner.nextLine();

                String result = reservationService.makeReservation(guestId, roomId, start, end);
                System.out.println(result);
            } else if (option == 0) {
                break;
            }
        }
    }
}