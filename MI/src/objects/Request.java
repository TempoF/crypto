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
public class Request implements Serializable {
    private String request;
    
    public Request(String HashCommand){
        this.request=HashCommand;
    }
    
    public String getRequest(){
        return this.request;
    }
}
