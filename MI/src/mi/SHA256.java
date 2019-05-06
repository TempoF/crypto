/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mi;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.bouncycastle.util.encoders.Hex;

/**
 *
 * @author Tempori
 */
public class SHA256 {
    private String sha256hex;
    public SHA256(String message) throws NoSuchAlgorithmException{
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(
          message.getBytes(StandardCharsets.UTF_8));
        this.sha256hex = new String(Hex.encode(hash));
    }
    
    public String getSha(){
        return this.sha256hex;
    }
}
