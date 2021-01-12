package ehu.isad.controller.Utils;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utils {

    private static Utils instantzia= new Utils();
    private Utils(){ }

    public static Utils getUtils(){return instantzia;}

    public Properties lortuEzarpenak()  {
        Properties properties = null;

        try (InputStream in = Utils.class.getResourceAsStream("/setup.properties")) {
            properties = new Properties();
            properties.load(in);

        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public Image irudiaKargatu (String path){
        String imagePath = this.lortuEzarpenak().getProperty("pathToImages")+path+".png";
        Image irudia = null;
        try {
            irudia =  new Image(new FileInputStream(imagePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return irudia;
    }
}
