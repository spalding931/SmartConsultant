package esiea.org.app.Security;

import java.security.MessageDigest;

/**
 * Created by Ayoub Bouthoukine on 12/11/2016.
 */
public class Encrypt {

    public static final String md5Encrypt(final String toEncrypt) {
        try {
            final MessageDigest digest = MessageDigest.getInstance("md5");
            digest.update(toEncrypt.getBytes());
            final byte[] bytes = digest.digest();
            final StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(String.format("%02X", bytes[i]));
            }
            return sb.toString().toLowerCase();
        } catch (Exception exc) {
            return "";
        }
    }

    public static boolean verify(String input, String databaseValue)
    {
        input = md5Encrypt(input);
        if( input.equals(databaseValue))
            return true;
        return false;
    }
}
