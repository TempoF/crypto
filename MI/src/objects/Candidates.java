/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.awt.Image;
import java.io.Serializable;


/**
 *
 * @author Tempori
 */
public class Candidates implements Serializable {
    private String name;
    private Image photo;
    private String party;
    private String id;
    
    public Candidates(String name, String party, String id){
        this.name=name;
        this.party=party;
        this.id=id;
    }
    
    public void setImage(Image photo){
        this.photo=photo;
    }
    
    public String getName(){
        return name;
    }
    public String getParty(){
        return party;
    }
    public String getId(){
        return id;
    }
    public Image getImage(){
        return photo;
    }
    
}
