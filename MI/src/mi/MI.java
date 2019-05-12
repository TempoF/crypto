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
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import objects.Candidates;
import objects.*;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.util.encoders.Hex;

/**
 *
 * @author Tempori
 */
public class MI {
    private static KeyPair pair ;
    private static PublicKey MVPublic;
    /**
     * @param args the command line arguments
     */
    
    private static void getPublicKey(){
            try {
                System.out.println("Esperando llave de MV");
                ServerSocket sck = new ServerSocket(6988);
                Socket cli;
                cli=sck.accept();
                ObjectInputStream in= new ObjectInputStream(cli.getInputStream());
                Object requestObj=(Request)in.readObject();
                
                
                Request req=(Request) requestObj;
                String request=req.getRequest();
                
                if((new SHA256("SentPK")).getSha().equals(request)){
                    
                    Response resp=new Response(200,"Valido");                
                    ObjectOutputStream out= new ObjectOutputStream(cli.getOutputStream());
                    
                    
                    out.writeObject(resp);
                    
                    MVPublic=((KeySync)req.getMessage()).getPublic();
                    System.out.println("Llave de MV recibida: "+ new String(Hex.encode(MVPublic.getEncoded())));
                    
                }

            } catch (IOException | ClassNotFoundException | NoSuchAlgorithmException ex) {
                
            }    
        
    }
    
    private static int sendPublicKey(){
            Socket sck = null;
             try {
                 System.out.println("Conectando con MV");
                sck = new Socket("127.0.0.1",6989);               
                ObjectOutputStream out= new ObjectOutputStream(sck.getOutputStream());
                SHA256 comd = new SHA256("SentPK"); 
                 System.out.println("Enviando llave a MV");
                Request req=new Request(comd.getSha(),(Object)new KeySync("",pair.getPublic()));
                out.writeObject(req);
                
                ObjectInputStream in = new ObjectInputStream(sck.getInputStream());
                Response resp=(Response)in.readObject();
                
                 if (resp.getCode()==200) {
                     System.out.println("MV recibio la llave con exito");
                     return 1;
                 }

            } catch (IOException | ClassNotFoundException | NoSuchAlgorithmException ex) {
                try {
                    sck.close();
                } catch (IOException | NullPointerException ex1) {
                  
                }
            }
        return 0;
    }
    
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException{
        
        ServerSocket sck = new ServerSocket(6986);
        Socket cli;
        pair=generateKeys();
        Runnable runnable =() -> { getPublicKey(); };
            Thread thread = new Thread(runnable);
            thread.start();
            
        Runnable runnableS =() -> { 
            while(true){                
                try {
                    int res=sendPublicKey();
                    if (res==0) {
                    Thread.sleep(10000);                        
                    }else{
                        break;
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(MI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
            Thread threadS = new Thread(runnableS);
            threadS.start();    
            
        while(true){
            
            cli=sck.accept();
            ObjectInputStream in= new ObjectInputStream(cli.getInputStream());
            Object requestObj=(Request)in.readObject();
            Request req=(Request) requestObj;
            
            String request=req.getRequest();
            
            if((new SHA256("Verify")).getSha().equals(request)){
                ArrayList<String> msg=(ArrayList<String>) req.getMessage();
                System.out.println("Se recibe H(CEL+HD): "+msg.get(0));
                //db query
                
                Response resp=new Response(200,"Valido");
                
                ObjectOutputStream out= new ObjectOutputStream(cli.getOutputStream());
                
                out.writeObject(resp);
                
            }else if((new SHA256("Candidates")).getSha().equals(request)){
            System.out.println("\n****************************************************\n");
                System.out.println("Envio de candidatos");
                ArrayList<Candidates> candidates=new ArrayList<>();
//                candidates.add(new Candidates("Candidato 1","PRN",(new SHA256("Candidato 1")).getSha()));
//                candidates.add(new Candidates("Candidato 2","PRN",(new SHA256("Candidato 2")).getSha()));
//                candidates.add(new Candidates("Candidato 3","PRN",(new SHA256("Candidato 3")).getSha()));
//                candidates.add(new Candidates("Candidato 4","PRN",(new SHA256("Candidato 4")).getSha()));


                ObjectOutputStream out= new ObjectOutputStream(cli.getOutputStream());
                out.writeObject(candidates);
                
                
            }else if((new SHA256("Signature")).getSha().equals(request)){
                ArrayList<String> msg=(ArrayList<String>) req.getMessage();
                    System.out.println("\n****************************************************\n");
                    System.out.println("Recibiendo V' y H(V')");
                    System.out.println("V' : "+msg.get(0));
                    System.out.println("H(V') : "+msg.get(1));
                    
                    if (!new SHA256(msg.get(0)).getSha().equals(msg.get(1))) {
                        System.out.println("Integridad comprometida volver a intentar.");
                        Response resp=new Response(300,"Integridad comprometida");
                        ObjectOutputStream out= new ObjectOutputStream(cli.getOutputStream());
                        out.writeObject(resp);
                    }else{
                         try {                   
                            Security.addProvider(new BouncyCastleProvider());
                            Signature ecdsaSign = Signature.getInstance("SHA256withECDSA", "BC");
                            ecdsaSign.initSign(pair.getPrivate());
                            ecdsaSign.update(msg.get(0).getBytes("UTF-8"));
                            
                            byte[] signature = ecdsaSign.sign();
                             System.out.println("Voto Firmado: "+new String(Hex.encode(signature)));
                            Response resp=new Response(200,new String(Hex.encode(signature)));
                            
                            ObjectOutputStream out= new ObjectOutputStream(cli.getOutputStream());
                            out.writeObject(resp);


                        } catch (NoSuchProviderException | InvalidKeyException | SignatureException ex) {
                            Logger.getLogger(MI.class.getName()).log(Level.SEVERE, null, ex);
                        }    
                    }
                    
                           
                
            }
        } 
//        cli.close();
  
        
    }
    
    public static KeyPair generateKeys()
    throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException
    {
        Security.addProvider(new BouncyCastleProvider());
        ECParameterSpec ecSpec = ECNamedCurveTable.getParameterSpec("B-571");
        KeyPairGenerator g = KeyPairGenerator.getInstance("ECDSA", "BC");
        g.initialize(ecSpec, new SecureRandom());
        return g.generateKeyPair();
    }
    
}
