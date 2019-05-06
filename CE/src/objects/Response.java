/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.io.Serializable;

/**
 *
 * @author Tempori
 */
public class Response implements Serializable{
    String message;
    int code;
    
    public Response(int code, String message){
        this.message=message;
        this.code=code;
    }
    
    public int getCode(){
        return this.code;
    }
    
    public String getMessage(){
        return this.message;
    }
}