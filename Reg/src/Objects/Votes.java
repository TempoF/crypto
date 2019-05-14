/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

/**
 *
 * @author Tempori
 */
public class Votes {
   private String candidate;
   private String party;
   private int percentage;
   
   public Votes(String candidate, String party, int percent){
       this.candidate=candidate;
       this.party=party;
       this.percentage=percent;
   }
   
   public String getCandidate(){
       return this.candidate;
   }
   
   public String getParty(){
       return this.party;
   }
   
   public int getPercent(){
       return this.percentage;
   }
}
