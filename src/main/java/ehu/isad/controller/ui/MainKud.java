package ehu.isad.controller.ui;

import ehu.isad.Main;
import ehu.isad.Model;
import ehu.isad.Utils.Conection;
import ehu.isad.Utils.Utils;
import ehu.isad.controller.db.DBKudeatzaile;
import ehu.isad.controller.db.PMADBKud;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MainKud implements Initializable {

  private Main mainApp;

  @FXML
  private TableView<Model> tbdata;

  @FXML
  private TableColumn<Model, String> url;

  @FXML
  private TableColumn<Model, String> md5;

  @FXML
  private TableColumn<Model, String> version;

  @FXML
  private TextField urlTextField;

  @FXML
  private Button check;

  @FXML
  private Text info;

  @FXML
  void onClick(ActionEvent event) {
    this.bilatu();
  }

  private ObservableList<Model> models = FXCollections.observableArrayList();

  private void bilatu (){
    this.info.setText("");
    //Bilatu ea datu basean zegoen
    try {
      String non = this.urlTextField.getText();
      String hash = Utils.getUtils().getHash(non+"/README");
      Model berria = PMADBKud.getInstance().bilatu(non, hash);
      if (berria.getVersion().equals("")){
        this.info.setText("Ez da datubasean aurkitu");
      }else{
        this.info.setText("Datubasean zegoen");
      }
      this.models.add(berria);
      this.tbdata.refresh();
    } catch (Exception e){
      this.info.setText("URL ez dago ondo edo ez du PHPMyAdmin erabiltzen");
    }

  }

  public void setMainApp(Main main) {
    this.mainApp = mainApp;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    tbdata.setEditable(true);

    //columnas
    url.setCellValueFactory(new PropertyValueFactory<>("Url"));
    md5.setCellValueFactory(new PropertyValueFactory<>("Md5"));
    version.setCellValueFactory(new PropertyValueFactory<>("Version"));

    version.setCellFactory(TextFieldTableCell.forTableColumn());

    version.setOnEditCommit((TableColumn.CellEditEvent<Model, String> event) ->{
      TablePosition<Model, String> pos = event.getTablePosition();
      int row = pos.getRow();
      Model act = event.getTableView().getItems().get(row);
      act.setVersion(event.getNewValue());
      PMADBKud.getInstance().gehitu(act);
      this.info.setText("md5 eta bertsio berria datubasean sartu dira");
    });
      tbdata.setItems(models);

  }
  @FXML
  void onKeyPressed(KeyEvent event) {
    if (event.getCode() == KeyCode.ENTER){
      this.bilatu();
    }
  }
}