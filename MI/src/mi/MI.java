/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mi;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import objects.Candidates;
import objects.Request;

/**
 *
 * @author Tempori
 */
public class MI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchAlgorithmException{
        ServerSocket sck = new ServerSocket(6987);
        Socket cli;
        while(true){
            
            cli=sck.accept();
            System.out.println("holaaaa");
            ObjectInputStream in= new ObjectInputStream(cli.getInputStream());
            System.out.println("holaaaa");
            Object requestObj=(Request)in.readObject();
            Request req=(Request) requestObj;
            System.out.println("holaaaa");
            
            String request=req.getRequest();
            System.out.println(request+" + "+(new SHA256("Verify")).getSha().equals(request));
            if((new SHA256("Verify")).getSha().equals(request)){

            }else if((new SHA256("Candidates")).getSha().equals(request)){
                System.out.println("Entra sha");
                ArrayList<Candidates> candidates=new ArrayList<>();
                candidates.add(new Candidates("Candidato 1","PRN",(new SHA256("Candidato 1")).getSha()));
                candidates.add(new Candidates("Candidato 2","PRN",(new SHA256("Candidato 2")).getSha()));
                candidates.add(new Candidates("Candidato 3","PRN",(new SHA256("Candidato 3")).getSha()));
                candidates.add(new Candidates("Candidato 4","PRN",(new SHA256("Candidato 4")).getSha()));


                ObjectOutputStream out= new ObjectOutputStream(cli.getOutputStream());
                out.writeObject(candidates);
            }
        } 
//        cli.close();
  
        
    }
    
}
