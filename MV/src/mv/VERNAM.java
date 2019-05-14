/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mv;

import org.bouncycastle.util.encoders.Hex;

/**
 *
 * @author Tempori
 */
public class VERNAM {
   
    
    public VERNAM(){
    }
    
    public String getVernam(String text, String key){
        byte[] id=Hex.decode(text);
        byte[] keyf=Hex.decode(key);
        byte[] result = new byte[keyf.length]; 
        for (int i = 0; i < keyf.length; i++) {
            result[i]=  (byte)(id[i] ^ keyf[i]);
        }        
        return new String(Hex.encode(result));
    }
    
     
}
