package com.cozythehotel.database;

import com.cozythehotel.model.Room;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {
    public List<Room> ambilSemuaKamar() {
        List<Room> daftar = new ArrayList<>();
        String kueri = "SELECT * FROM rooms";
        try (Connection koneksi = DBConnection.getConnection();
             Statement pernyataan = koneksi.createStatement();
             ResultSet hasil = pernyataan.executeQuery(kueri)) {
            while (hasil.next()) {
                daftar.add(new Room(
                        hasil.getInt("id"),
                        hasil.getString("room_number"),
                        hasil.getString("room_type"),
                        hasil.getString("status"),
                        hasil.getString("customer_name")
                ));
            }
        } catch (SQLException galat) {
            galat.printStackTrace();
        }
        return daftar;
    }
}    