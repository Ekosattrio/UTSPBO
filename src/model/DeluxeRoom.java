/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model;

/**
 *
 * @author Advan
 */


public class DeluxeRoom extends RoomType {
    public DeluxeRoom(int numberOfRooms) {
        super("Deluxe", 750000, 15, numberOfRooms); // Set harga dan kapasitas tamu
    }

    @Override
    public String getDescription() {
        return "Kamar Deluxe dengan fasilitas lebih baik.";
    }
}




