/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Tempori
 */
public class Request implements Serializable {
    private String request;
    private ArrayList<String> message;
    
    public Request(String HashCommand,ArrayList<String> message){
        this.request=HashCommand;
        this.message=message;
    }
    
    public String getRequest(){
        return this.request;
    }
    public ArrayList<String> getMessage(){
        return this.message;
    }
}