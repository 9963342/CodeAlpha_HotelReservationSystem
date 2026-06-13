import java.util.*;
import java.io.*;

public class HotelReservationSystem {

    static ArrayList<Room> rooms = new ArrayList<>();
    static ArrayList<Reservation> reservations = new ArrayList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        rooms.add(new Room(101, "Standard", 1000));
        rooms.add(new Room(102, "Deluxe", 2000));
        rooms.add(new Room(103, "Suite", 3500));

        while (true) {

            System.out.println("\n===== HOTEL RESERVATION SYSTEM =====");
            System.out.println("1. Search Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Cancel Reservation");
            System.out.println("4. View Bookings");
            System.out.println("5. Exit");
            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    searchRooms();
                    break;

                case 2:
                    bookRoom(sc);
                    break;

                case 3:
                    cancelReservation(sc);
                    break;

                case 4:
                    viewBookings();
                    break;

                case 5:
                    sc.close();
                    System.out.println("Thank You!");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }

    static void searchRooms() {

        System.out.println("\nAvailable Rooms:");

        for (Room room : rooms) {

            if (room.available) {

                System.out.println(
                        room.roomNo + " | "
                        + room.category + " | Rs."
                        + room.price);
            }
        }
    }

    static void bookRoom(Scanner sc) {

        System.out.print("Enter Customer Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Room Number: ");
        int roomNo = sc.nextInt();

        for (Room room : rooms) {

            if (room.roomNo == roomNo && room.available) {

                System.out.println("Payment Amount: Rs." + room.price);
                System.out.println("Payment Successful");

                Customer customer = new Customer(name);

                Reservation reservation =
                        new Reservation(customer, room);

                reservations.add(reservation);

                room.available = false;

                saveBooking(customer.name, room);

                System.out.println("Room Booked Successfully!");

                return;
            }
        }

        System.out.println("Room Not Available!");
    }

    static void cancelReservation(Scanner sc) {

        sc.nextLine();

        System.out.print("Enter Customer Name: ");
        String name = sc.nextLine();

        for (Reservation r : reservations) {

            if (r.customer.name.equalsIgnoreCase(name)) {

                r.room.available = true;

                reservations.remove(r);

                System.out.println("Reservation Cancelled!");

                return;
            }
        }

        System.out.println("Booking Not Found!");
    }

    static void viewBookings() {

        if (reservations.isEmpty()) {

            System.out.println("No Bookings Found");
            return;
        }

        for (Reservation r : reservations) {

            System.out.println(
                    "Customer: " + r.customer.name
                    + " | Room: " + r.room.roomNo
                    + " | Category: " + r.room.category);
        }
    }

    static void saveBooking(String customer, Room room) {

        try {

            FileWriter fw =
                    new FileWriter("bookings.txt", true);

            fw.write(customer + " - Room "
                    + room.roomNo + " - "
                    + room.category + "\n");

            fw.close();

        } catch (Exception e) {

            System.out.println("File Error");
        }
    }
}