package ehu.isad.controller.ui;

import ehu.isad.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MainKud implements Initializable {

  private Main mainApp;

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private TableView<Modelo> tbdata;

  @FXML
  private TableColumn<Modelo, String> editable;

  @FXML
  private TableColumn<Modelo, Image> irudia;

  @FXML
  private TableColumn<Modelo, ?> checkbox;



  public void setMainApp(Main main) {
    this.mainApp = mainApp;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    tbdata.setEditable(true);

    //columna editable
    editable.setCellValueFactory(new PropertyValueFactory<>("editable"));
    editable.setCellFactory(TextFieldTableCell.forTableColumn());
    editable.setOnEditCommit((TableColumn.CellEditEvent<Model, String> event) ->{
      // lo que pasa al editar la columna editable
      TablePosition<Model, String> pos = event.getTablePosition();
      int row = pos.getRow();
      Model act = event.getTableView().getItems().get(row);
      act.setContent(event.getNewValue());
    });

    //irudia columna
    irudia.setCellValueFactory(new PropertyValueFactory<Model, Image>("irudia"));
    irudia.setCellFactory(p -> new TableCell<>() {
      public void updateItem(Image image, boolean empty) {
        if (image != null && !empty){
          final ImageView imageview = new ImageView();
          imageview.setFitHeight(50);
          imageview.setFitWidth(50);
          imageview.setImage(image);
          setGraphic(imageview);
          setAlignment(Pos.CENTER);
          // tbData.refresh();
        }else{
          setGraphic(null);
          setText(null);
        }
      };
    });

    //checkbox columna --> Gogoratu modeloan checkbox motakoa atributoa izan behar dela
    checkbox.setCellValueFactory(new PropertyValueFactory<Model, String>("checkbox"));
  }
}