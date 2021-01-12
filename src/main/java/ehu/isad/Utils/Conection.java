package ehu.isad.Utils;

import javafx.scene.image.Image;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Conection {

    public static String getStringFromUrl(String url) {
        System.out.println(url);
        String inputLine = "";
        URL conection;

        try {
            conection = new URL(url);
            URLConnection yc = conection.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            inputLine = in.readLine();
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputLine;
    }
    public Image createImageFromUrl(String url) {

        Image image= null;
        try{
            url = url.replace("-S", "-M");
            URLConnection conn = new URL(url).openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36");
            try (InputStream stream = conn.getInputStream()) {
                image = new Image(stream, 700, 700, true, false);

            }
        }catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
