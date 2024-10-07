package model;

public class StandardRoom extends RoomType {
    public StandardRoom(int numberOfRooms) {
        super("Standard", 500000, 10, numberOfRooms); // Set harga dan kapasitas tamu
    }

    @Override
    public String getDescription() {
        return "Kamar Standard dengan fasilitas dasar.";
    }
}
