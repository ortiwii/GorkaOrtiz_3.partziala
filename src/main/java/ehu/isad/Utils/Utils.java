package ehu.isad.Utils;


import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.security.*;
import org.apache.commons.codec.binary.Hex;

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

//    public String getHash(String txt, String hashType) {
//        try {
//            java.security.MessageDigest md = java.security.MessageDigest.getInstance(hashType);
//            byte[] array = md.digest(txt.getBytes());
//            StringBuffer sb = new StringBuffer();
//            for (int i = 0; i < array.length; ++i) {
//                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
//            }
//            return sb.toString();
//        } catch (java.security.NoSuchAlgorithmException e) {
//            //error action
//        }
//        return null;
//    }
    public static String getHash(String non) throws IOException, NoSuchAlgorithmException {
        URL url = new URL(non);
        InputStream is = url.openStream();
        MessageDigest md = MessageDigest.getInstance("MD5");
        String digest = getDigest(is, md, 2048);
        System.out.println("MD5 Digest:" + digest);
        return digest;
    }

    public static String getDigest(InputStream is, MessageDigest md, int byteArraySize)
            throws NoSuchAlgorithmException, IOException {

        md.reset();
        byte[] bytes = new byte[byteArraySize];
        int numBytes;
        while ((numBytes = is.read(bytes)) != -1) {
            md.update(bytes, 0, numBytes);
        }
        byte[] digest = md.digest();
        String result = new String(Hex.encodeHex(digest));
        return result;
    }

}
//    public Image irudiaKargatu (String path){
//        String imagePath = this.lortuEzarpenak().getProperty("pathToImages")+path+".png";
//        Image irudia = null;
//        try {
//            irudia =  new Image(new FileInputStream(imagePath));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        return irudia;
//    }

