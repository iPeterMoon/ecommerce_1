package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Security {
    
    /**
     * Hashea una cadena de texto usando SHA-256
     * @param texto La cadena a hashear
     * @return El hash en formato hexadecimal
     */
    public static String hashear(String texto) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(texto.getBytes());
            
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al hashear la cadena", e);
        }
    }
}
