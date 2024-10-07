package model;

public class Reservation {
    private String customerName;
    private String phone;
    private RoomType roomType;
    private String checkIn;
    private int guests;
    private int roomNumber;
    private int nights; // Jumlah malam

    public Reservation(String customerName, String phone, RoomType roomType, String checkIn, int guests, int roomNumber, int nights) {
        this.customerName = customerName;
        this.phone = phone;
        this.roomType = roomType;
        this.checkIn = checkIn;
        this.guests = guests;
        this.roomNumber = roomNumber;
        this.nights = nights; // Simpan jumlah malam
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getPhone() {
        return phone;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public int getGuests() {
        return guests;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getNights() {
        return nights; // Getter untuk jumlah malam
    }

    @Override
    public String toString() {
        double totalCost = roomType.getPrice() * nights; // Hitung total biaya
        return "Struk Reservasi:\n" +
               "Nama: " + customerName + "\n" +
               "Telepon: " + phone + "\n" +
               "Tipe Kamar: " + roomType.getType() + "\n" +
               "Tanggal Check-in: " + checkIn + "\n" +
               "Jumlah Tamu: " + guests + "\n" +
               "Nomor Kamar: " + (roomNumber + 1) + "\n" +
               "Jumlah Malam: " + nights + "\n" +
               "Harga per Malam: " + roomType.getPrice() + "\n" +
               "Total Biaya: " + totalCost + "\n" +
               "-----------------------------------";
    }
}
