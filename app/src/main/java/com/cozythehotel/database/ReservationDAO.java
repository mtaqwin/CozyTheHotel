package com.cozythehotel.database;

import com.cozythehotel.model.Reservation;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {
    public void tambahReservasi(int idPelanggan, int idKamar, String masuk, String keluar, String metodePembayaran) {
        String kueri = "INSERT INTO reservations (customer_id, room_id, check_in, check_out, payment_method, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection koneksi = DBConnection.getConnection();
             PreparedStatement pernyataan = koneksi.prepareStatement(kueri)) {
            pernyataan.setInt(1, idPelanggan);
            pernyataan.setInt(2, idKamar);
            pernyataan.setString(3, masuk);
            pernyataan.setString(4, keluar);
            pernyataan.setString(5, metodePembayaran);
            pernyataan.setString(6, "Belum Checkout");
            pernyataan.executeUpdate();
        } catch (SQLException galat) {
            galat.printStackTrace();
        }
    }

    public List<Reservation> getReservasiAktif() {
        List<Reservation> daftar = new ArrayList<>();
        String kueri = "SELECT r.*, c.name, c.phone, rm.room_number, rm.room_type " +
                       "FROM reservations r " +
                       "JOIN customers c ON r.customer_id = c.id " +
                       "JOIN rooms rm ON r.room_id = rm.id " +
                       "WHERE r.status = 'Belum Checkout'";
        try (Connection koneksi = DBConnection.getConnection();
             Statement pernyataan = koneksi.createStatement();
             ResultSet hasil = pernyataan.executeQuery(kueri)) {
            while (hasil.next()) {
                Reservation res = new Reservation(
                        hasil.getInt("id"),
                        hasil.getInt("customer_id"),
                        hasil.getInt("room_id"),
                        hasil.getString("check_in"),
                        hasil.getString("check_out"),
                        hasil.getString("payment_method"),
                        hasil.getString("status")
                );
                res.setNamaPelanggan(hasil.getString("name"));
                res.setTeleponPelanggan(hasil.getString("phone"));
                res.setNomorKamar(hasil.getString("room_number"));
                res.setTipeKamar(hasil.getString("room_type"));
                daftar.add(res);
            }
        } catch (SQLException galat) {
            galat.printStackTrace();
        }
        return daftar;
    }
    
     public int getJumlahTerisiBerdasarkanTanggal(String tanggal) {
        String kueri = "SELECT COUNT(DISTINCT room_id) FROM reservations WHERE check_in <= ? AND check_out >= ? AND status = 'Belum Checkout'";
        try (Connection koneksi = DBConnection.getConnection();
             PreparedStatement pernyataan = koneksi.prepareStatement(kueri)) {
            pernyataan.setString(1, tanggal);
            pernyataan.setString(2, tanggal);
            ResultSet hasil = pernyataan.executeQuery();
            if (hasil.next()) return hasil.getInt(1);
        } catch (SQLException galat) {
            galat.printStackTrace();
        }
        return 0;
    }

    public int getJumlahTotalKamar() {
        String kueri = "SELECT COUNT(*) FROM rooms";
        try (Connection koneksi = DBConnection.getConnection();
             Statement pernyataan = koneksi.createStatement();
             ResultSet hasil = pernyataan.executeQuery(kueri)) {
            if (hasil.next()) return hasil.getInt(1);
        } catch (SQLException galat) {
            galat.printStackTrace();
        }
        return 0;
    }

    public void perbaruiStatusKamar(int id, String status, String namaPelanggan) {
        String kueri = "UPDATE rooms SET status = ?, customer_name = ? WHERE id = ?";
        try (Connection koneksi = DBConnection.getConnection();
             PreparedStatement pernyataan = koneksi.prepareStatement(kueri)) {
            pernyataan.setString(1, status);
            pernyataan.setString(2, namaPelanggan);
            pernyataan.setInt(3, id);
            pernyataan.executeUpdate();
        } catch (SQLException galat) {
            galat.printStackTrace();
        }
    }

    public int getJumlahBerdasarkanStatus(String status) {
        String kueri = "SELECT COUNT(*) FROM rooms WHERE status = ?";
        try (Connection koneksi = DBConnection.getConnection();
             PreparedStatement pernyataan = koneksi.prepareStatement(kueri)) {
            pernyataan.setString(1, status);
            ResultSet hasil = pernyataan.executeQuery();
            if (hasil.next()) return hasil.getInt(1);
        } catch (SQLException galat) {
            galat.printStackTrace();
        }
        return 0;
    }
}    