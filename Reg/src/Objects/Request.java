/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import java.io.Serializable;

/**
 *
 * @author Tempori
 */
public class Request implements Serializable {
    private String request;
    private Object message;
    
    public Request(String HashCommand,Object message){
        this.request=HashCommand;
        this.message=message;
    }
    
    public String getRequest(){
        return this.request;
    }
    public Object getMessage(){
        return this.message;
    }
}