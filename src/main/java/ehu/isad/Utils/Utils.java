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

    public static String getHash(String non) throws IOException, NoSuchAlgorithmException {
        URL url = new URL(non);
        InputStream is = url.openStream();
        MessageDigest md = MessageDigest.getInstance("MD5");
        String digest = getDigest(is, md, 2048);
        return digest;
    }

    private static String getDigest(InputStream is, MessageDigest md, int byteArraySize)
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

