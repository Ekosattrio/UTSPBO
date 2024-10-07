package model;

public class SuiteRoom extends RoomType {
    public SuiteRoom(int numberOfRooms) {
        super("Suite", 1000000, 20, numberOfRooms); // Set harga dan kapasitas tamu
    }

    @Override
    public String getDescription() {
        return "Kamar Suite dengan fasilitas lengkap.";
    }
}
