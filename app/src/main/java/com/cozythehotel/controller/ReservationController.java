package com.cozythehotel.controller;

import com.cozythehotel.database.CustomerDAO;
import com.cozythehotel.database.ReservationDAO;
import com.cozythehotel.database.RoomDAO;
import com.cozythehotel.model.Room;
import com.cozythehotel.view.ReservationView;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationController {
    private Stage panggung;
    private ReservationView tampilan;
    private CustomerDAO daoPelanggan;
    private ReservationDAO daoReservasi;
    private RoomDAO daoKamar;

    public ReservationController(Stage panggung) {
        this.panggung = panggung;
        this.tampilan = new ReservationView();
        this.daoPelanggan = new CustomerDAO();
        this.daoReservasi = new ReservationDAO();
        this.daoKamar = new RoomDAO();

        inisialisasiHandler();
        muatNomorKamar();
    }

    private void inisialisasiHandler() {
        tampilan.getTombolBatal().setOnAction(e -> new DashboardController(panggung).tampilkan());

        tampilan.getCbTipe().setOnAction(e -> muatNomorKamar());

        tampilan.getTombolSimpan().setOnAction(e -> simpanReservasi());
    }

    private void muatNomorKamar() {
        String tipeDipilih = tampilan.getCbTipe().getValue();
        if (tipeDipilih != null) {
            List<Room> semuaKamar = daoKamar.ambilSemuaKamar();
            List<String> nomorKamarTersedia = semuaKamar.stream()
                    .filter(k -> k.getTipeKamar().equalsIgnoreCase(tipeDipilih) && k.getStatus().equalsIgnoreCase("Available"))
                    .map(Room::getNomorKamar)
                    .collect(Collectors.toList());
            
            tampilan.getCbNomorKamar().getItems().setAll(nomorKamarTersedia);
        }
    }

    private void simpanReservasi() {
        String nama = tampilan.getTxtNama().getText();
        String telp = tampilan.getTxtTelepon().getText();
        String masuk = tampilan.getDpMasuk().getValue() != null ? tampilan.getDpMasuk().getValue().toString() : "";
        String keluar = tampilan.getDpKeluar().getValue() != null ? tampilan.getDpKeluar().getValue().toString() : "";
        String tipe = tampilan.getCbTipe().getValue();
        String nomorKamar = tampilan.getCbNomorKamar().getValue();
        String pembayaran = tampilan.getCbPembayaran().getValue();

        if (nama.isEmpty() || telp.isEmpty() || masuk.isEmpty() || keluar.isEmpty() || nomorKamar == null) {
            tampilkanPeringatan("Peringatan", "Semua data harus diisi!");
            return;
        }

        // 1. Tambah Pelanggan
        int idPelanggan = daoPelanggan.tambahPelanggan(nama, telp);
        
        // 2. Cari ID Kamar berdasarkan nomor kamar
        List<Room> daftarKamar = daoKamar.ambilSemuaKamar();
        int idKamar = daftarKamar.stream()
                .filter(k -> k.getNomorKamar().equals(nomorKamar))
                .findFirst()
                .map(Room::getId)
                .orElse(-1);

        if (idPelanggan != -1 && idKamar != -1) {
            // 3. Tambah Reservasi
            daoReservasi.tambahReservasi(idPelanggan, idKamar, masuk, keluar, pembayaran);
            
            // 4. Update Status Kamar
            daoKamar.perbaruiStatusKamar(idKamar, "Occupied", nama);

            tampilkanPeringatan("Sukses", "Reservasi berhasil disimpan!");
            new DashboardController(panggung).tampilkan();
        } else {
            tampilkanPeringatan("Gagal", "Terjadi kesalahan saat menyimpan reservasi.");
        }
    }

    private void tampilkanPeringatan(String judul, String isi) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(judul);
        alert.setHeaderText(null);
        alert.setContentText(isi);
        alert.showAndWait();
    }

    public void tampilkan() {
        Scene adegan = new Scene(tampilan.getAkar(), 1100, 750);
        panggung.setScene(adegan);
        panggung.setTitle("Tambah Reservasi - CozyTheHotel");
        panggung.setMaximized(true);
        panggung.show();
    }
}
