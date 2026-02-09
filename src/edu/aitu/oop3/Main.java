package edu.aitu.oop3;

import edu.aitu.oop3.entities.*;
import edu.aitu.oop3.repositories.*;
import edu.aitu.oop3.services.PaymentService;
import edu.aitu.oop3.services.ReservationService;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        GuestRepository guestRepo = new GuestRepository();
        RoomRepository roomRepo = new RoomRepository();
        ReservationRepository resRepo = new ReservationRepository();
        ReservationService reservationService = new ReservationService();

        while (true) {
            System.out.println("""
                1. Add Guest
                2. Add Room
                3. List Guests
                4. List Rooms
                5. Make Reservation with Payment
                6. Delete Reservation
                0. Exit
                """);

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    guestRepo.save(new Guest(0, name, email));
                }

                case 2 -> {
                    System.out.print("Room type: ");
                    String type = sc.nextLine();
                    System.out.print("Price: ");
                    double price = sc.nextDouble();
                    sc.nextLine();
                    roomRepo.save(new Room(0, type, price));
                }

                case 3 -> guestRepo.findAll()
                        .forEach(g -> System.out.println(g.getId() + " " + g.getName()));

                case 4 -> roomRepo.findAll()
                        .forEach(r -> System.out.println(r.getId() + " " + r.getType() + " " + r.getPrice()));

                case 5 -> {
                    try {
                        System.out.print("Guest ID: ");
                        int gid = sc.nextInt();
                        System.out.print("Room ID: ");
                        int rid = sc.nextInt();
                        sc.nextLine();

                        System.out.print("From (yyyy-mm-dd): ");
                        LocalDate from = LocalDate.parse(sc.nextLine());

                        System.out.print("To (yyyy-mm-dd): ");
                        LocalDate to = LocalDate.parse(sc.nextLine());

                        System.out.print("Payment Amount: ");
                        double amount = sc.nextDouble();
                        sc.nextLine();


                    } catch (Exception e) {
                        System.out.println("ERROR: " + e.getMessage());
                    }
                }

                case 6 -> {
                    System.out.print("Reservation ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    resRepo.delete(id);
                }

                case 0 -> System.exit(0);
            }
        }
    }
}
