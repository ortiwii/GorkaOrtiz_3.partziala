package ehu.isad;

import ehu.isad.controller.ui.MainKud;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

  private Parent mainUI;

  private Scene mainScene;
  private Stage stage;

  private MainKud mainKud;

  @Override
  public void start(Stage primaryStage) throws Exception {

    stage = primaryStage;
    pantailakKargatu();

    stage.setTitle("Azterketa Gorka Ortiz");
    stage.setScene(mainScene);
    stage.show();
  }

  private void pantailakKargatu() throws IOException {

    FXMLLoader loaderMain = new FXMLLoader(getClass().getResource("/Main.fxml"));
    mainUI = (Parent) loaderMain.load();
    mainKud = loaderMain.getController();
    mainScene = new Scene(mainUI);
    mainKud.setMainApp(this);
  }

  public static void main(String[] args) {
    launch(args);
  }

}
