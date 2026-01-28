package edu.aitu.oop3;

import edu.aitu.oop3.db.*;
import edu.aitu.oop3.entities.*;
import edu.aitu.oop3.repositories.*;
import edu.aitu.oop3.repositories.interfaces.*;
import edu.aitu.oop3.services.ReservationService;
import edu.aitu.oop3.exceptions.PaymentDeclinedException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        IDB db = new PostgresDB();

        IGuestRepository guestRepo = new GuestRepository(db);
        IRoomRepository roomRepo = new RoomRepository(db);
        IReservationRepository resRepo = new ReservationRepository(db);
        IPaymentRepository payRepo = new PaymentRepository(db); // New

        ReservationService service = new ReservationService(resRepo, roomRepo);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Hotel Booking System ---");
            System.out.println("1. Register Guest\n2. View Rooms\n3. Book Room\n4. Process Payment\n0. Exit");
            int choice = scanner.nextInt();

            if (choice == 1) {
            } else if (choice == 3) {
            } else if (choice == 4) {
                System.out.print("Enter Reservation ID: ");
                int resId = scanner.nextInt();
                System.out.print("Enter Amount: ");
                double amount = scanner.nextDouble();

                try {
                    if (amount <= 0) {
                        throw new PaymentDeclinedException("Invalid amount! Payment declined.");
                    }

                    Payment payment = new Payment(resId, amount, "Completed");
                    if (payRepo.processPayment(payment)) {
                        System.out.println("Payment successful and recorded!");
                    }
                } catch (PaymentDeclinedException e) {
                    System.err.println("Error: " + e.getMessage());
                }
            } else if (choice == 0) break;
        }
    }
}