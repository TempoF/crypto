/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mv;

import connectors.connector;
import java.io.File;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import objects.*;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.util.encoders.Hex;

/**
 *
 * @author Tempori
 */
public class MV {
    private static KeyPair pair ;
    private static PublicKey MIPublic;
    /**
     * @param args the command line arguments
     */
    private static connector conector;
        private static Connection conn;
    private static void getPublicKey(){
            try {
                System.out.println("Esperando llave de MI");
                ServerSocket sck = new ServerSocket(6989);
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
                    
                    MIPublic=((KeySync)req.getMessage()).getPublic();
                    System.out.println("Llave de MI recibida: "+ new String(Hex.encode(MIPublic.getEncoded())));
                   
                }

            } catch (IOException | ClassNotFoundException | NoSuchAlgorithmException ex) {
                
            }  
        
    }
    
    private static int sendPublicKey(){
            Socket sck = null;
             try {
                System.out.println("Conectando con MI");
                sck = new Socket("127.0.0.1",6988);               
                ObjectOutputStream out= new ObjectOutputStream(sck.getOutputStream());
                SHA256 comd = new SHA256("SentPK"); 

                System.out.println("Enviando llave a MI");
                Request req=new Request(comd.getSha(),(Object)new KeySync("",pair.getPublic()));
                out.writeObject(req);
                
                
                ObjectInputStream in = new ObjectInputStream(sck.getInputStream());
                Response resp=(Response)in.readObject();
                
                 if (resp.getCode()==200) {
                     System.out.println("MI recibio la llave con exito");
                     return 1;
                 }

            } catch (IOException | ClassNotFoundException | NoSuchAlgorithmException | NullPointerException ex) {
                try {
                    sck.close();
                } catch (Exception ex1) {
                  
                }
            }
        return 0;
    }
    private static void connectToDB(){
        String dbName = "Mesa_Voto";
        conector = new connector(dbName);
        conn = conector.connectBD();
        if (conn != null)
            System.out.println("Conected to " + dbName + " Succesfully");
        else
            System.out.println("Error: " + dbName + " Database conection null");
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchProviderException, InvalidKeyException, SignatureException {
       
        ServerSocket sck = new ServerSocket(6987);
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
                    Logger.getLogger(MV.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
            Thread threadS = new Thread(runnableS);
            threadS.start();   
            connectToDB();
        
        while(true){
            
            cli=sck.accept();
            ObjectInputStream in= new ObjectInputStream(cli.getInputStream());
            Object requestObj=(Request)in.readObject();
            Request req=(Request) requestObj;
            
            String request=req.getRequest();
            if((new SHA256("GetVotes")).getSha().equals(request)){
                ArrayList<String> votesRes=new ArrayList<>();
                String query = "call USP_get_votes";
                try{
                    PreparedStatement ps = conn.prepareStatement(query);
                   ResultSet rs= ps.executeQuery();
                   while (rs.next())
                    {
                        
                        if (rs.getBoolean("result")==true) {
                            
                            String vprime=rs.getString("IDV");
                            String hash=rs.getString("Hash");
                            VERNAM coder1 = new VERNAM();

                            String vprimer=coder1.getVernam(vprime,hash);
                            System.out.println("Id del candidato : "+(vprimer));
                            votesRes.add(vprimer);

                        }else{
                            ObjectOutputStream out= new ObjectOutputStream(cli.getOutputStream());
                            out.writeObject(new ArrayList<String>());
                            break;
                        }
                    }
                   
                } catch (SQLException ex) {
                     ObjectOutputStream out= new ObjectOutputStream(cli.getOutputStream());

                    out.writeObject(votesRes);
                        Logger.getLogger(MV.class.getName()).log(Level.SEVERE, null, ex);
                }
                ObjectOutputStream out= new ObjectOutputStream(cli.getOutputStream());
                out.writeObject(votesRes);
                    

            }else if((new SHA256("Signature")).getSha().equals(request)){
                ArrayList<String> msg=(ArrayList<String>) req.getMessage();
                    System.out.println("\n****************************************************\n");
                    System.out.println("Recibiendo Voto Firmado");
                   
                    Signature ecdsaVerify = Signature.getInstance("SHA256withECDSA", "BC");
                    ecdsaVerify.initVerify(MIPublic);
                    System.out.println("Voto Firmado: "+msg.get(1));
                    System.out.println("V' :"+msg.get(0));
                    System.out.println("HE :"+msg.get(2));
                    byte[] signature=Hex.decode(msg.get(1));

                    ecdsaVerify.update(msg.get(0).getBytes("UTF-8"));

                    boolean result = ecdsaVerify.verify(signature);
                    
                    if (result) {
                        System.out.println("Origen del voto confirmado");
                        //sql salvar v' y he
                        
                        
                        String query = "call USP_Vote (?,?)";
                        try{
                            PreparedStatement ps = conn.prepareStatement(query);
                            ps.setString(1, msg.get(0));
                            ps.setString(2, msg.get(2));
                           ResultSet rs= ps.executeQuery();
                            rs.first();
        //                    System.out.println(rs.getBoolean("result")+"--"+rs.getString("message"));
                            if (rs.getBoolean("result")==true) {
                                Response resp=new Response(200,"Voto satisfactorio.");
                                ObjectOutputStream out= new ObjectOutputStream(cli.getOutputStream());

                                out.writeObject(resp);
                            }
                        } catch (SQLException ex) {
                             Response resp=new Response(300,"Error al hacer el registro, contacte al administrador");
                             ObjectOutputStream out= new ObjectOutputStream(cli.getOutputStream());

                            out.writeObject(resp);
                                Logger.getLogger(MV.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        
                        
                        
                        Response resp=new Response(200,"Origen del voto confirmado");
                        ObjectOutputStream out= new ObjectOutputStream(cli.getOutputStream());
                        out.writeObject(resp);
                    } else {
                        System.out.println("Origen del voto desconocido. Voto anulado");
                        Response resp=new Response(300,"Origen del voto desconocido. Voto anulado");
                        ObjectOutputStream out= new ObjectOutputStream(cli.getOutputStream());
                        out.writeObject(resp);

                    }

            }
        } 
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
