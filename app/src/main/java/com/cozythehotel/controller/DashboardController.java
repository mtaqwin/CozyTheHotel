package com.cozythehotel.controller;

import com.cozythehotel.database.CustomerDAO;
import com.cozythehotel.database.ReservationDAO;
import com.cozythehotel.database.RoomDAO;
import com.cozythehotel.view.DashboardView;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DashboardController {
    private Stage panggung;
    private DashboardView tampilan;
    private RoomDAO daoKamar;
    private ReservationDAO daoReservasi;
    private CustomerDAO daoPelanggan;

    public DashboardController(Stage panggung) {
        this.panggung = panggung;
        this.tampilan = new DashboardView();
        this.daoKamar = new RoomDAO();
        this.daoReservasi = new ReservationDAO();
        this.daoPelanggan = new CustomerDAO();
        
        inisialisasiHandler();
        perbaruiStatistik(java.time.LocalDate.now().toString());
    }

    private void inisialisasiHandler() {
        tampilan.getPemilihTanggal().setOnAction(e -> {
            if (tampilan.getPemilihTanggal().getValue() != null) {
                perbaruiStatistik(tampilan.getPemilihTanggal().getValue().toString());
            }
        });

        tampilan.getTombolTambahReservasi().setOnAction(e -> {
            // Placeholder: new ReservationController(panggung).tampilkan();
        });

        tampilan.getTombolKelolaKamar().setOnAction(e -> new RoomController(panggung).tampilkan());
        
        tampilan.getTombolDataPelanggan().setOnAction(e -> new CustomerController(panggung).tampilkan());
        
        tampilan.getTombolCheckout().setOnAction(e -> new CheckoutController(panggung).tampilkan());
    }

    private void perbaruiStatistik(String tanggal) {
        int totalKamar = daoKamar.getJumlahTotalKamar();
        int terisi = daoReservasi.getJumlahTerisiBerdasarkanTanggal(tanggal);
        int tersedia = totalKamar - terisi;
        int tamuAktif = daoPelanggan.getJumlahTamuAktifBerdasarkanTanggal(tanggal);
        
        tampilan.setStatistik(tersedia, terisi, tamuAktif);
    }

    public void tampilkan() {
        Scene adegan = new Scene(tampilan.getAkar(), 1100, 750);
        panggung.setScene(adegan);
        panggung.setTitle("Dashboard Staff - CozyTheHotel");
        panggung.setMaximized(true);
        panggung.show();
    }
}
