package service;

import model.*;

public class Hotel {
    public RoomType[] rooms; // Ubah menjadi public
    private Reservation[] reservations;
    private int reservationCount;

    public Hotel() {
        // Menginisialisasi kamar
        rooms = new RoomType[9]; // 3 jenis kamar, masing-masing 3 kamar
        rooms[0] = new StandardRoom(3); // 3 StandardRoom
        rooms[1] = new StandardRoom(3);
        rooms[2] = new StandardRoom(3);
        rooms[3] = new DeluxeRoom(3);   // 3 DeluxeRoom
        rooms[4] = new DeluxeRoom(3);
        rooms[5] = new DeluxeRoom(3);
        rooms[6] = new SuiteRoom(3);     // 3 SuiteRoom
        rooms[7] = new SuiteRoom(3);
        rooms[8] = new SuiteRoom(3);

        reservations = new Reservation[100]; // Menyimpan maksimal 100 reservasi
        reservationCount = 0;
    }

    public User login(String username, String password) {
        // Menginisialisasi pengguna
        User[] users = new User[6];
        users[0] = new User("admin", "adminpass", true); // Admin
        users[1] = new User("EkoSatrio", "123", false);
        users[2] = new User("Rama", "123", false);
        users[3] = new User("Farhan", "123", false);
        users[4] = new User("Desman", "123", false);
        users[5] = new User("Dafa", "123", false);

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user; // Mengembalikan objek User yang sesuai
            }
        }
        return null; // Pengguna tidak ditemukan
    }

     public boolean reserveRoom(String customerName, String phone, RoomType roomType, String checkIn, int guests, int nights) {
        for (int i = 0; i < roomType.availability.length; i++) {
            if (roomType.isRoomAvailableForDates(i, checkIn, nights) && guests <= roomType.getMaxGuests()) {
                roomType.bookRoom(i, customerName, phone, checkIn, nights); // Tambahkan checkIn dan nights
                reservations[reservationCount++] = new Reservation(customerName, phone, roomType, checkIn, guests, i, nights);
                System.out.println(reservations[reservationCount - 1]); // Cetak struk
                return true; // Reservasi berhasil
            }
        }
        return false; // Tidak ada kamar tersedia
    }


    public void checkRoomAvailability() {
        System.out.println("=== Ketersediaan Kamar ===");
        for (RoomType roomType : rooms) {
            System.out.println("Tipe Kamar: " + roomType.getType());
            boolean[] availability = roomType.getAvailability(); // Gunakan getter untuk mengakses availability
            for (int i = 0; i < availability.length; i++) {
                String status = availability[i] ? "Tersedia" : "Tidak Tersedia";
                System.out.println("Kamar " + (i + 1) + ": " + status);
                if (!availability[i]) {
                    System.out.println("Dipesan oleh: " + roomType.getReservationName(i));
                }
            }
            System.out.println("-----------------------------------");
        }
    }
public void checkRoomAvailabilityForDate(String checkIn) {
    System.out.println("=== Ketersediaan Kamar Berdasarkan Tanggal ===");
    for (RoomType roomType : rooms) {
        System.out.println("Tipe Kamar: " + roomType.getType());
        boolean available = false;
        for (int i = 0; i < roomType.getAvailability().length; i++) {
            if (roomType.isRoomAvailableForDates(i, checkIn, 1)) { // Kita bisa gunakan '1' untuk cek hanya ketersediaan untuk check-in
                System.out.println("Kamar " + (i + 1) + ": Tersedia");
                available = true;
            } else {
                System.out.println("Kamar " + (i + 1) + ": Tidak Tersedia");
                System.out.println("   Dipesan oleh: " + roomType.getReservationName(i));
                System.out.println("   Nomor Telepon: " + roomType.getReservationPhone(i));
                System.out.println("   Tanggal Check-in: " + roomType.getCheckInDate(i));
                System.out.println("   Jumlah Malam: " + roomType.getNights(i));
            }
        }
        if (!available) {
            System.out.println("Tidak ada kamar tersedia pada tanggal ini.");
        }
        System.out.println("-----------------------------------");
    }
}


    public void printAllReservations() {
        System.out.println("=== Daftar Reservasi ===");
        for (int i = 0; i < reservationCount; i++) {
            System.out.println(reservations[i]);
        }
        if (reservationCount == 0) {
            System.out.println("Tidak ada reservasi.");
        }
    }
}
