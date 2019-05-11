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
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import objects.Candidates;
import objects.Request;
import objects.Response;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.util.encoders.Hex;

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
            ObjectInputStream in= new ObjectInputStream(cli.getInputStream());
            Object requestObj=(Request)in.readObject();
            Request req=(Request) requestObj;
            
            String request=req.getRequest();
            
            if((new SHA256("Verify")).getSha().equals(request)){
                System.out.println("Se recibe H(CEL+HD): "+req.getMessage().get(0));
                //db query
                
                Response resp=new Response(200,"Valido");
                
                ObjectOutputStream out= new ObjectOutputStream(cli.getOutputStream());
                
                out.writeObject(resp);
                
            }else if((new SHA256("Candidates")).getSha().equals(request)){
            System.out.println("\n****************************************************\n");
                System.out.println("Envio de candidatos");
                ArrayList<Candidates> candidates=new ArrayList<>();
                candidates.add(new Candidates("Candidato 1","PRN",(new SHA256("Candidato 1")).getSha()));
                candidates.add(new Candidates("Candidato 2","PRN",(new SHA256("Candidato 2")).getSha()));
                candidates.add(new Candidates("Candidato 3","PRN",(new SHA256("Candidato 3")).getSha()));
                candidates.add(new Candidates("Candidato 4","PRN",(new SHA256("Candidato 4")).getSha()));


                ObjectOutputStream out= new ObjectOutputStream(cli.getOutputStream());
                out.writeObject(candidates);
                
                
            }else if((new SHA256("Signature")).getSha().equals(request)){
                    System.out.println("\n****************************************************\n");
                    System.out.println("Recibiendo V' y H(V')");
                    System.out.println("V' : "+req.getMessage().get(0));
                    System.out.println("H(V') : "+req.getMessage().get(1));
                    if (!new SHA256(req.getMessage().get(0)).getSha().equals(req.getMessage().get(1))) {
                        System.out.println("Integridad comprometida volver a intentar.");
                        Response resp=new Response(300,"Integridad comprometida");
                        ObjectOutputStream out= new ObjectOutputStream(cli.getOutputStream());
                        out.writeObject(resp);
                    }else{
                         try {                   
                            Security.addProvider(new BouncyCastleProvider());
                            KeyPair pair = GenerateKeys();
                            Signature ecdsaSign = Signature.getInstance("SHA256withECDSA", "BC");
                            ecdsaSign.initSign(pair.getPrivate());
                            ecdsaSign.update(req.getMessage().get(0).getBytes("UTF-8"));
                            byte[] signature = ecdsaSign.sign();
                            Response resp=new Response(200,new String(Hex.encode(signature)));
                            ObjectOutputStream out= new ObjectOutputStream(cli.getOutputStream());
                            out.writeObject(resp);


                        } catch (NoSuchProviderException | InvalidKeyException | SignatureException | InvalidAlgorithmParameterException ex) {
                            Logger.getLogger(MI.class.getName()).log(Level.SEVERE, null, ex);
                        }    
                    }
                    
                           
                
            }
        } 
//        cli.close();
  
        
    }
    
    public static KeyPair GenerateKeys()
    throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException
{
    Security.addProvider(new BouncyCastleProvider());
    ECParameterSpec ecSpec = ECNamedCurveTable.getParameterSpec("B-571");
    KeyPairGenerator g = KeyPairGenerator.getInstance("ECDSA", "BC");
    g.initialize(ecSpec, new SecureRandom());
    return g.generateKeyPair();
}
    
}
