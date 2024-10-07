package model;

public abstract class RoomType {
    protected String type;
    protected double price;
    protected int maxGuests;
    public boolean[] availability; // Array untuk ketersediaan kamar
    protected String[] reservationNames; // Menyimpan nama-nama yang melakukan reservasi
    protected String[] checkInDates; // Menyimpan tanggal check-in untuk setiap kamar
    protected int[] nights; // Menyimpan jumlah malam untuk setiap kamar
    protected String[] reservationPhones; // Menyimpan nomor telepon yang melakukan reservasi

    public RoomType(String type, double price, int maxGuests, int numberOfRooms) {
        this.type = type;
        this.price = price;
        this.maxGuests = maxGuests;
        this.availability = new boolean[numberOfRooms]; // Inisialisasi semua kamar sebagai tersedia
        this.reservationNames = new String[numberOfRooms]; // Untuk menyimpan nama pemesan
        this.checkInDates = new String[numberOfRooms]; // Menyimpan tanggal check-in
        this.nights = new int[numberOfRooms]; // Menyimpan jumlah malam
        this.reservationPhones = new String[numberOfRooms]; // Menyimpan nomor telepon

        for (int i = 0; i < availability.length; i++) {
            availability[i] = true; // Semua kamar tersedia saat inisialisasi
            reservationNames[i] = ""; // Kosongkan nama pemesan
            checkInDates[i] = ""; // Tidak ada tanggal check-in
            nights[i] = 0; // Jumlah malam 0
            reservationPhones[i] = ""; // Kosongkan nomor telepon
        }
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public int getMaxGuests() {
        return maxGuests;
    }

    public abstract String getDescription();

    public boolean isRoomAvailable(int roomIndex) {
        return availability[roomIndex];
    }

    public void bookRoom(int roomIndex, String customerName, String phone, String checkIn, int nights) {
        availability[roomIndex] = false; // Tandai kamar sebagai tidak tersedia
        reservationNames[roomIndex] = customerName; // Simpan nama pemesan
        reservationPhones[roomIndex] = phone; // Simpan nomor telepon
        checkInDates[roomIndex] = checkIn; // Simpan tanggal check-in
        this.nights[roomIndex] = nights; // Simpan jumlah malam
    }

    // Method untuk mengecek ketersediaan kamar berdasarkan tanggal check-in dan jumlah malam
    public boolean isRoomAvailableForDates(int roomIndex, String desiredCheckIn, int desiredNights) {
        // Jika kamar tersedia, tidak perlu cek bentrok tanggal, langsung return true
        if (availability[roomIndex]) {
            return true;
        }

        // Jika kamar sudah dipesan, cek apakah tanggal yang diinginkan bentrok
        String bookedCheckIn = checkInDates[roomIndex];
        int bookedNights = nights[roomIndex];

        // Cek bentrok tanggal
        if (!isDateRangeConflicting(bookedCheckIn, bookedNights, desiredCheckIn, desiredNights)) {
            return true; // Tidak ada bentrok
        }
        
        return false; // Tanggal bentrok, kamar tidak tersedia
    }

    // Method untuk mengecek apakah rentang tanggal bentrok
    private boolean isDateRangeConflicting(String bookedCheckIn, int bookedNights, String desiredCheckIn, int desiredNights) {
        int bookedStart = convertDateToInt(bookedCheckIn);
        int bookedEnd = bookedStart + bookedNights;
        int desiredStart = convertDateToInt(desiredCheckIn);
        int desiredEnd = desiredStart + desiredNights;

        // Cek apakah ada bentrok
        return (desiredStart < bookedEnd && desiredEnd > bookedStart);
    }

    private int convertDateToInt(String date) {
        String[] parts = date.split("-");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);

        return year * 365 + month * 30 + day;
    }

    public String getReservationName(int roomIndex) {
        return reservationNames[roomIndex];
    }

    public String getReservationPhone(int roomIndex) {
        return reservationPhones[roomIndex];
    }

    public String getCheckInDate(int roomIndex) {
        return checkInDates[roomIndex];
    }

    public int getNights(int roomIndex) {
        return nights[roomIndex];
    }

    public boolean[] getAvailability() {
        return availability;
    }
}
