package com.cozythehotel.controller;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DashboardController {
    private Stage panggung;

    public DashboardController(Stage panggung) {
        this.panggung = panggung;
    }

    public void tampilkan() {
        VBox akar = new VBox(30);
        akar.setAlignment(Pos.CENTER);
        akar.setStyle("-fx-background-color: #f4ebe1; -fx-padding: 50;");

        Label judul = new Label("Dashboard CozyTheHotel");
        judul.setStyle("-fx-font-size: 32px; -fx-font-weight: bold; -fx-text-fill: #0c2340; -fx-font-family: 'Georgia';");

        Button tombolKamar = new Button("Manajemen Kamar");
        tombolKamar.setPrefSize(250, 60);
        tombolKamar.setStyle("-fx-background-color: #0c2340; -fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold; -fx-background-radius: 10; -fx-cursor: hand;");
        tombolKamar.setOnAction(e -> new RoomController(panggung).tampilkan());

        akar.getChildren().addAll(judul, tombolKamar);

        Scene adegan = new Scene(akar, 1100, 750);
        panggung.setScene(adegan);
        panggung.setTitle("Dashboard - CozyTheHotel");
        panggung.setMaximized(true);
        panggung.show();
    }
}
