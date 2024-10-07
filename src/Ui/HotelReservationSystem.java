package ui;

import service.Hotel;
import model.User;

import java.util.Scanner;
import model.RoomType;

public class HotelReservationSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hotel hotel = new Hotel();

        boolean isRunning = true; // Loop utama
        while (isRunning) {
            // Login Menu
            System.out.println("=== Menu Login ===");
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();

            User user = hotel.login(username, password);

            if (user != null) {
                System.out.println("\nLogin berhasil. Selamat datang, " + user.getUsername() + "!");
                
                if (user.isAdmin()) {
                    // Tampilkan menu admin
                    adminMenu(scanner, hotel);
                } else {
                    // Tampilkan menu customer
                    customerMenu(scanner, hotel, user);
                }
            } else {
                System.out.println("Login gagal. Username atau password salah.");
            }
        }
    }

    public static void customerMenu(Scanner scanner, Hotel hotel, User user) {
        boolean customerRunning = true; // Loop untuk customer
        while (customerRunning) {
            System.out.println("\n=== Menu Utama Customer ===");
            System.out.println("1. Memesan Kamar");
            System.out.println("2. Mengecek Ketersediaan Kamar");
            System.out.println("3. Keluar");
            System.out.print("Pilih menu: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    makeReservation(scanner, hotel, user);
                    break;
                case 2:
                    hotel.checkRoomAvailability();
                    break;
                case 3:
                    customerRunning = false; // Kembali ke menu login
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }

 public static void adminMenu(Scanner scanner, Hotel hotel) {
    boolean adminRunning = true; // Loop untuk admin
    while (adminRunning) {
        System.out.println("\n=== Menu Utama Admin ===");
        System.out.println("1. Lihat Semua Reservasi");
        System.out.println("2. Cek Ketersediaan Kamar");
        System.out.println("3. Keluar");
        System.out.print("Pilih menu: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                hotel.printAllReservations();
                break;
            case 2:
                boolean validInput = false; // Flag untuk memastikan input valid
                String checkIn = "";
                int nights = 0;
                int guests = 0;

                // Meminta input tanggal check-in, jumlah malam, dan jumlah tamu
                while (!validInput) {
                    System.out.print("Masukkan tanggal check-in (YYYY-MM-DD): ");
                    checkIn = scanner.nextLine();

                    // Validasi format tanggal
                    if (!checkIn.matches("\\d{4}-\\d{2}-\\d{2}")) {
                        System.out.println("Input tanggal tidak valid. Harap masukkan tanggal dalam format YYYY-MM-DD.");
                        continue; // Kembali ke awal loop untuk meminta input ulang
                    }

                    System.out.print("Masukkan jumlah malam: ");
                    nights = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    System.out.print("Masukkan jumlah tamu: ");
                    guests = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    // Validasi jumlah tamu
                    boolean exceedsCapacity = false;
                    for (RoomType roomType : hotel.rooms) {
                        if (guests > roomType.getMaxGuests()) {
                            exceedsCapacity = true;
                            break;
                        }
                    }

                    if (exceedsCapacity) {
                        System.out.println("Jumlah tamu melebihi batas maksimum. Ketik ulang semua input.");
                    } else {
                        validInput = true; // Jika input valid, keluar dari loop
                    }
                }

                // Cek ketersediaan kamar berdasarkan tanggal check-in
                hotel.checkRoomAvailabilityForDate(checkIn);
                break;
            case 3:
                adminRunning = false; // Kembali ke menu login
                break;
            default:
                System.out.println("Pilihan tidak valid. Silakan coba lagi.");
        }
    }
}





    public static void makeReservation(Scanner scanner, Hotel hotel, User user) {
        System.out.print("Masukkan nomor telepon: ");
        String phone = scanner.nextLine();

        System.out.print("Pilih tipe kamar (1. Standard, 2. Deluxe, 3. Suite): ");
        int roomTypeChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Masukkan tanggal check-in (YYYY-MM-DD): ");
        String checkIn = scanner.nextLine();

        System.out.print("Masukkan jumlah malam: ");
        int nights = scanner.nextInt();

        System.out.print("Masukkan jumlah tamu: ");
        int guests = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Cek tipe kamar
        RoomType roomType = hotel.rooms[roomTypeChoice - 1];
        if (hotel.reserveRoom(user.getUsername(), phone, roomType, checkIn, guests, nights)) {
            System.out.println("Reservasi berhasil.");
        } else {
            System.out.println("INPUT YANG BENER!!!.");
        }
    }
}
