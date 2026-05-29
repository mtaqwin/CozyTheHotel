// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package com.cozythehotel.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ReservationView {
   private VBox akar;
   private TextField txtNama;
   private TextField txtTelepon;
   private DatePicker dpMasuk;
   private DatePicker dpKeluar;
   private ComboBox<String> cbTipe;
   private ComboBox<String> cbNomorKamar;
   private ComboBox<String> cbPembayaran;
   private Button tombolSimpan;
   private Button tombolBatal;

   public ReservationView() {
      this.siapkanUI();
   }

   private void siapkanUI() {
      this.akar = new VBox();
      this.akar.setStyle("-fx-background-color: #f2e9dc;");
      HBox header = new HBox();
      header.setStyle("-fx-background-color: #072247; -fx-border-color: #d4af37; -fx-border-width: 0 0 4 0; -fx-padding: 20 40;");
      Label lblJudul = new Label("Reservasi Customer");
      lblJudul.setStyle("-fx-text-fill: #e5b94c; -fx-font-family: 'Georgia', serif; -fx-font-size: 28px;");
      header.getChildren().add(lblJudul);
      VBox kartuForm = new VBox();
      kartuForm.setStyle("-fx-background-color: white; -fx-background-radius: 15; -fx-padding: 40;");
      DropShadow bayangan = new DropShadow();
      bayangan.setColor(Color.rgb(0, 0, 0, 0.05));
      bayangan.setRadius((double)15.0F);
      bayangan.setOffsetY((double)5.0F);
      kartuForm.setEffect(bayangan);
      VBox.setMargin(kartuForm, new Insets((double)40.0F, (double)60.0F, (double)40.0F, (double)60.0F));
      GridPane grid = new GridPane();
      grid.setVgap((double)15.0F);
      grid.setHgap((double)30.0F);
      grid.setAlignment(Pos.CENTER);
      ColumnConstraints col1 = new ColumnConstraints();
      col1.setPrefWidth((double)130.0F);
      ColumnConstraints col2 = new ColumnConstraints();
      col2.setHgrow(Priority.ALWAYS);
      col2.setPrefWidth((double)450.0F);
      grid.getColumnConstraints().addAll(new ColumnConstraints[]{col1, col2});
      this.txtNama = new TextField();
      this.txtTelepon = new TextField();
      this.dpMasuk = new DatePicker();
      this.dpKeluar = new DatePicker();
      this.cbTipe = new ComboBox();
      this.cbNomorKamar = new ComboBox();
      this.cbPembayaran = new ComboBox();
      this.cbTipe.getItems().addAll(new String[]{"Standard", "Deluxe", "Suite"});
      this.cbPembayaran.getItems().addAll(new String[]{"Transfer", "Virtual Account"});
      this.tombolBatal = new Button("Batal");
      this.tombolSimpan = new Button("Simpan Reservasi");
      String gayaInput = "-fx-background-color: white; -fx-border-color: #d1dce6; -fx-border-radius: 5; -fx-background-radius: 5; -fx-padding: 8; -fx-font-size: 14px;";
      String gayaLabel = "-fx-text-fill: #5b708b; -fx-font-size: 14px;";
      this.tambahBarisForm(grid, "Nama Tamu", this.txtNama, gayaLabel, gayaInput, 0);
      this.tambahBarisForm(grid, "Nomor Telepon", this.txtTelepon, gayaLabel, gayaInput, 1);
      this.tambahBarisForm(grid, "Tanggal Masuk", this.dpMasuk, gayaLabel, gayaInput, 2);
      this.tambahBarisForm(grid, "Tanggal Keluar", this.dpKeluar, gayaLabel, gayaInput, 3);
      this.tambahBarisForm(grid, "Tipe Kamar", this.cbTipe, gayaLabel, gayaInput, 4);
      this.tambahBarisForm(grid, "Nomor Kamar", this.cbNomorKamar, gayaLabel, gayaInput, 5);
      this.tambahBarisForm(grid, "Pembayaran", this.cbPembayaran, gayaLabel, gayaInput, 6);
      HBox kotakTombol = new HBox((double)15.0F);
      kotakTombol.setAlignment(Pos.CENTER);
      kotakTombol.setPadding(new Insets((double)30.0F, (double)0.0F, (double)0.0F, (double)0.0F));
      this.tombolBatal.setStyle("-fx-background-color: white; -fx-text-fill: #072247; -fx-border-color: #d1dce6; -fx-border-radius: 8; -fx-background-radius: 8; -fx-padding: 10 30; -fx-font-weight: bold; -fx-cursor: hand;");
      this.tombolSimpan.setStyle("-fx-background-color: #072247; -fx-text-fill: white; -fx-background-radius: 8; -fx-padding: 10 30; -fx-font-weight: bold; -fx-cursor: hand;");
      kotakTombol.getChildren().addAll(new Node[]{this.tombolBatal, this.tombolSimpan});
      kartuForm.getChildren().addAll(new Node[]{grid, kotakTombol});
      this.akar.getChildren().addAll(new Node[]{header, kartuForm});
   }

   private void tambahBarisForm(GridPane grid, String label, Control kontrol, String gayaL, String gayaI, int baris) {
      Label lbl = new Label(label);
      lbl.setStyle(gayaL);
      kontrol.setStyle(gayaI);
      kontrol.setMaxWidth(Double.MAX_VALUE);
      grid.add(lbl, 0, baris);
      grid.add(kontrol, 1, baris);
   }

   public VBox getAkar() {
      return this.akar;
   }

   public TextField getTxtNama() {
      return this.txtNama;
   }

   public TextField getTxtTelepon() {
      return this.txtTelepon;
   }

   public DatePicker getDpMasuk() {
      return this.dpMasuk;
   }

   public DatePicker getDpKeluar() {
      return this.dpKeluar;
   }

   public ComboBox<String> getCbTipe() {
      return this.cbTipe;
   }

   public ComboBox<String> getCbNomorKamar() {
      return this.cbNomorKamar;
   }

   public ComboBox<String> getCbPembayaran() {
      return this.cbPembayaran;
   }

   public Button getTombolSimpan() {
      return this.tombolSimpan;
   }

   public Button getTombolBatal() {
      return this.tombolBatal;
   }
}
