package com.cozythehotel;

import com.cozythehotel.controller.DashboardController;
import com.cozythehotel.database.DBConnection;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Inisialisasi basis data saat aplikasi dimulai
        DBConnection.siapkanBasisData();
        
        // Tampilkan dashboard utama
        DashboardController dashboard = new DashboardController(primaryStage);
        dashboard.tampilkan();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
