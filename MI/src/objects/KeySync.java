/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.io.Serializable;
import java.security.PublicKey;

/**
 *
 * @author Tempori
 */
public class KeySync  implements Serializable{
    private PublicKey publicK;
    private String comd;
    public KeySync(String cmd,PublicKey key){
        this.publicK=key;
        this.comd=cmd;
    }
    
    public String getCmd(){
        return comd;
    }
    
    public PublicKey getPublic(){
        return publicK;
    }
            
}
