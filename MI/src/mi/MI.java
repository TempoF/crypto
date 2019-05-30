/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mi;

import connectors.connector;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
import javax.imageio.ImageIO;
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
    
        private static connector conector;
        private static Connection conn;
    
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
    
    private static void connectToDB(){
        String dbName = "Mesa_Registro";
        conector = new connector(dbName);
        conn = conector.connectBD();
        if (conn != null)
            System.out.println("Conected to " + dbName + " Succesfully");
        else
            System.out.println("Error: " + dbName + " Database conection null");
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
            connectToDB();
            
        while(true){
            
            cli=sck.accept();
            System.out.println("Peticion aceptada desde: "+sck.getInetAddress().getHostAddress());
            
            ObjectInputStream in= new ObjectInputStream(cli.getInputStream());
            Object requestObj=(Request)in.readObject();
            Request req=(Request) requestObj;            
            String request=req.getRequest();
            
            if((new SHA256("Datetimes")).getSha().equals(request)){
                
                System.out.println("Actualizando periodo de votacion...");
                
                ArrayList<String> msg=(ArrayList<String>) req.getMessage();
                String query = "call USP_set_period (?,?)";
                try{
                    PreparedStatement ps = conn.prepareStatement(query);
                    ps.setString(1, msg.get(0));
                    ps.setString(2, msg.get(1));
                   ResultSet rs= ps.executeQuery();
                    rs.first();
//                    System.out.println(rs.getBoolean("result")+"--"+rs.getString("message"));
                    if (rs.getBoolean("result")==true) {
                        System.out.println("Periodo actualizado correctamente");
                        Response resp=new Response(200,rs.getString("message"));
                        ObjectOutputStream out= new ObjectOutputStream(cli.getOutputStream());

                        out.writeObject(resp);
                    }else{
                        System.out.println("Periodo no actualizado: "+rs.getString("message"));
                        Response resp=new Response(300,rs.getString("message"));
                        ObjectOutputStream out= new ObjectOutputStream(cli.getOutputStream());

                        out.writeObject(resp);
                    }
                } catch (SQLException ex) {
                    System.out.println("Error de consulta: "+ex.getMessage());
                     Response resp=new Response(300,"Error al hacer el registro, contacte al administrador");
                     ObjectOutputStream out= new ObjectOutputStream(cli.getOutputStream());
                    out.writeObject(resp);
                }
                
                
            }else if((new SHA256("Login")).getSha().equals(request)){
                
                System.out.println("Haciendo login de admin...");
                ArrayList<String> msg=(ArrayList<String>) req.getMessage();
//                System.out.println(msg.get(0)+" -- "+msg.get(1));
                String query = "call USP_Check_admin_login (?,?)";
                try{
                    PreparedStatement ps = conn.prepareStatement(query);
                    ps.setString(1, msg.get(0));
                    ps.setString(2, msg.get(1));
                   ResultSet rs= ps.executeQuery();
                    rs.first();
//                    System.out.println(rs.getBoolean("result")+"--"+rs.getString("message"));
                    if (rs.getBoolean("result")==true) {
                        System.out.println("Login correcto");
                        Response resp=new Response(200,rs.getString("message"));
                        ObjectOutputStream out= new ObjectOutputStream(cli.getOutputStream());

                        out.writeObject(resp);
                    }else{
                        System.out.println("Login incorrecto: "+rs.getString("message"));
                        Response resp=new Response(300,rs.getString("message"));
                        ObjectOutputStream out= new ObjectOutputStream(cli.getOutputStream());

                        out.writeObject(resp);
                    }
                } catch (SQLException ex) {
                    System.out.println("Error de consulta: "+ex.getMessage());
                     Response resp=new Response(300,"Error al hacer el login, contacte al administrador");
                     ObjectOutputStream out= new ObjectOutputStream(cli.getOutputStream());

                    out.writeObject(resp);
                    
                }
                
                
            }else if((new SHA256("RegistryCandidates")).getSha().equals(request)){
                System.out.println("Registrando candidato...");
                Candidates msg=(Candidates) req.getMessage();
                
//                byte[] img = Hex.decode(msg.getImage());
                System.out.println("Obteniendo Imagen");
                
                int sizeImg = Integer.parseInt(msg.getImage());
                System.out.println(sizeImg+"");
                
                byte [] mybytearray  = new byte [sizeImg];
                InputStream is = cli.getInputStream();
                System.out.println("Aquiiiiiiiiiiiii");
                FileOutputStream fos = new FileOutputStream("../photos/"+msg.getId()+".png");
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                int bytesRead = is.read(mybytearray,0,mybytearray.length);
                int current = bytesRead;
                
                 do {
                     System.out.println(bytesRead);
                    bytesRead =
                       is.read(mybytearray, current, (mybytearray.length-current));
                    if(bytesRead >= 0) current += bytesRead;
                 } while(bytesRead > 0);

                 bos.write(mybytearray, 0 , current);
                 bos.flush();
                 System.out.println("File " + "../photos/"+msg.getId()+".png"
                     + " downloaded (" + current + " bytes read)");
                  if (fos != null) fos.close();
                  if (bos != null) bos.close();
//                ByteArrayInputStream bis = new ByteArrayInputStream(img);
//                BufferedImage bImage2 = ImageIO.read(bis);
//                
//                ImageIO.write(bImage2, "png", new File("../photos/"+msg.getId()+".png") );
                
                String[] nm=msg.getName().split("--");
                String query = "call USP_insert_candidate (?,?,?,?,?,?)";
                try{
                    PreparedStatement ps = conn.prepareStatement(query);
                    ps.setString(1, msg.getId());
                    ps.setString(2, nm[0]);
                    ps.setString(3, nm[1]);
                    ps.setString(4, nm[2]);
                    ps.setString(5, msg.getParty());
                    ps.setString(6, msg.getId()+".png");
                    
                   ResultSet rs= ps.executeQuery();
                    rs.first();
//                    System.out.println(rs.getBoolean("result")+"--"+rs.getString("message"));
                    if (rs.getBoolean("result")==true) {
                        System.out.println("Registro de candidato correcto");
                        Response resp=new Response(200,rs.getString("message"));
                        ObjectOutputStream out= new ObjectOutputStream(cli.getOutputStream());

                        out.writeObject(resp);
                    }else{
                        System.out.println("Registro de candidato fallido: "+rs.getString("message"));
                        Response resp=new Response(300,rs.getString("message"));
                        ObjectOutputStream out= new ObjectOutputStream(cli.getOutputStream());

                        out.writeObject(resp);
                    }
                } catch (SQLException ex) {
                    System.out.println("Error de consulta: "+ex.getMessage());
                     Response resp=new Response(300,"Error al hacer el login, contacte al administrador");
                     ObjectOutputStream out= new ObjectOutputStream(cli.getOutputStream());

                    out.writeObject(resp);
                }
                
            }else if((new SHA256("Registry")).getSha().equals(request)){
                System.out.println("Registro de votante...");
                ArrayList<String> msg=(ArrayList<String>) req.getMessage();
                
                SHA256 cl = new SHA256(msg.get(0));
                SHA256 id=new SHA256(cl.getSha()+msg.get(4));
                String query = "call USP_insert_voter (?,?,?,?,?,?)";
                try{
                    PreparedStatement ps = conn.prepareStatement(query);
                    ps.setString(1,id.getSha() );
                    ps.setString(2, msg.get(0));
                    ps.setString(3, msg.get(1));
                    ps.setString(4, msg.get(2));
                    ps.setString(5, msg.get(3));
                    ps.setString(6, msg.get(4));
                    
                   ResultSet rs= ps.executeQuery();
                    rs.first();
                   System.out.println(rs.getBoolean("result")+"--"+rs.getString("message"));
                    if (rs.getBoolean("result")==true) {
                        System.out.println("Registro correcto");
                        Response resp=new Response(200,rs.getString("message"));
                        ObjectOutputStream out= new ObjectOutputStream(cli.getOutputStream());

                        out.writeObject(resp);
                    }else{
                        System.out.println("Registro fallido: "+rs.getString("message"));
                        Response resp=new Response(300,rs.getString("message"));
                        ObjectOutputStream out= new ObjectOutputStream(cli.getOutputStream());

                        out.writeObject(resp);
                    }
                } catch (SQLException ex) {
                    System.out.println("Error de consulta: "+ex.getMessage());
                     Response resp=new Response(300,"Error al hacer el login, contacte al administrador");
                     ObjectOutputStream out= new ObjectOutputStream(cli.getOutputStream());

                    out.writeObject(resp);
                      
                }
                
            }else if((new SHA256("Verify")).getSha().equals(request)){
                System.out.println("Verificando votante");
                ArrayList<String> msg=(ArrayList<String>) req.getMessage();
                System.out.println("Se recibe H(CEL+HD): "+msg.get(0));
                String query = "call USP_Check_login (?)";
                try{
                    PreparedStatement ps = conn.prepareStatement(query);
                    ps.setString(1, msg.get(0));
                   ResultSet rs= ps.executeQuery();
                    rs.first();
//                    System.out.println(rs.getBoolean("result")+"--"+rs.getString("message"));
                    if (rs.getBoolean("result")==true) {
                        System.out.println("El candidato fue validado.");
                        Response resp=new Response(200,rs.getString("message"));
                        System.out.println("Enviando mensaje: "+rs.getString("message"));
                        ObjectOutputStream out= new ObjectOutputStream(cli.getOutputStream());

                        out.writeObject(resp);
                    }else{
                        System.out.println("El candidato no se encuentra validado.");
                        Response resp=new Response(300,rs.getString("message"));
                        System.out.println("Enviando mensaje: "+rs.getString("message"));
                        ObjectOutputStream out= new ObjectOutputStream(cli.getOutputStream());

                        out.writeObject(resp);
                    }
                } catch (SQLException ex) {
                    System.out.println("Error de consulta: "+ex.getMessage());
                     Response resp=new Response(300,"Error al hacer el login, contacte al administrador");
                     ObjectOutputStream out= new ObjectOutputStream(cli.getOutputStream());

                    out.writeObject(resp);
                      
                }
                
            }else if((new SHA256("Candidates")).getSha().equals(request)){
                System.out.println("Envio de candidatos");
                ArrayList<Candidates> candidates=new ArrayList<>();
                String query = "call USP_Get_Candidates";
                try{
                    PreparedStatement ps = conn.prepareStatement(query);                   
                   ResultSet rs= ps.executeQuery();
                    while (rs.next())
                    {
                    if (rs.getBoolean("result")==true) {
                        System.out.println("Enviando...");
                       Candidates candidato=new Candidates(rs.getString("Name")+" "+rs.getString("LastNameP")+" "+rs.getString("LastNameM"),
                              rs.getString("party"),rs.getString("IdCandidate"));
                      
                      File photo=new File("../photos/"+rs.getString("ProfileImage"));
                      candidato.setImage(imgPrepare(photo.getAbsolutePath(),"png"));
                      candidates.add(candidato);
                   
                        
                    
                    }else{
                        System.out.println("No hay candidatos");
                        ObjectOutputStream out= new ObjectOutputStream(cli.getOutputStream());
                        out.writeObject(new ArrayList<Candidates>());
                        break;
                    }
                    }
                    ObjectOutputStream out= new ObjectOutputStream(cli.getOutputStream());
                    out.writeObject(candidates);
                    out.flush();
                   
                    
                    
                } catch (SQLException ex) {
                    System.out.println("Error de consulta: "+ex.getMessage());
                    ObjectOutputStream out= new ObjectOutputStream(cli.getOutputStream());
                    out.writeObject(candidates);
                }

                
                
                
                
            }else if((new SHA256("CandidatesSimple")).getSha().equals(request)){
                System.out.println("Envio de candidatos sin fotos");
                ArrayList<Candidates> candidates=new ArrayList<>();
                String query = "call USP_Get_Candidates_w";
                try{
                    PreparedStatement ps = conn.prepareStatement(query);                   
                   ResultSet rs= ps.executeQuery();
                    while (rs.next())
                    {
                    if (rs.getBoolean("result")==true) {
                    
                       Candidates candidato=new Candidates(rs.getString("Name")+" "+rs.getString("LastNameP")+" "+rs.getString("LastNameP"),
                              rs.getString("party"),rs.getString("IdCandidate"));
                      candidates.add(candidato);
                   
                        
                    
                    }else{
                        ObjectOutputStream out= new ObjectOutputStream(cli.getOutputStream());
                        out.writeObject(new ArrayList<Candidates>());
                        break;
                    }
                    }
                    ObjectOutputStream out= new ObjectOutputStream(cli.getOutputStream());
                    out.writeObject(candidates);
                    out.flush();
                   
                    
                    
                } catch (SQLException ex) {
                    System.out.println("Error de consulta: "+ex.getMessage());
                    ObjectOutputStream out= new ObjectOutputStream(cli.getOutputStream());
                    out.writeObject(candidates);
                }

                
                
                
                
            }else if((new SHA256("checkPeriod")).getSha().equals(request)){
                System.out.println("Verificando periodo....");
                String query = "call USP_period";
                try{
                    PreparedStatement ps = conn.prepareStatement(query);                   
                   ResultSet rs= ps.executeQuery();
                    rs.first();
//                    System.out.println(rs.getBoolean("result")+"--"+rs.getString("message"));
                    if (rs.getBoolean("result")==true) {
                        System.out.println("Verificado: "+rs.getString("message"));
                        Response resp=new Response(200,rs.getString("message"));
                        ObjectOutputStream out= new ObjectOutputStream(cli.getOutputStream());

                        out.writeObject(resp);
                    }else{
                        System.out.println("No verificado: "+rs.getString("message"));
                        Response resp=new Response(300,rs.getString("message"));
                        ObjectOutputStream out= new ObjectOutputStream(cli.getOutputStream());

                        out.writeObject(resp);
                    }
                   
                    
                    
                } catch (SQLException ex) {
                    System.out.println("Error de consulta: "+ex.getMessage());
                   Response resp=new Response(300,"Error al consultar periodo, contacte al administrador");
                     ObjectOutputStream out= new ObjectOutputStream(cli.getOutputStream());
                }

                
                
                
                
            }else if((new SHA256("VoterTrue")).getSha().equals(request)){
                System.out.println("Verificando si el votante ya ha votado...");
                ArrayList<String> msg=(ArrayList<String>) req.getMessage();
                String query = "call USP_voter_true (?)";
                try{
                    PreparedStatement ps = conn.prepareStatement(query); 
                   ps.setString(1, msg.get(0));                  
                   ResultSet rs= ps.executeQuery();
                    rs.first();
//                    System.out.println(rs.getBoolean("result")+"--"+rs.getString("message"));
                    if (rs.getBoolean("result")==true) {
                        System.out.println("Verificado: "+rs.getString("message"));
                        Response resp=new Response(200,rs.getString("message"));
                        ObjectOutputStream out= new ObjectOutputStream(cli.getOutputStream());

                        out.writeObject(resp);
                    }else{
                        System.out.println("No verificado: "+rs.getString("message"));
                        Response resp=new Response(300,rs.getString("message"));
                        ObjectOutputStream out= new ObjectOutputStream(cli.getOutputStream());

                        out.writeObject(resp);
                    }
                   
                    
                    
                } catch (SQLException ex) {
                     System.out.println("Error de consulta: "+ex.getMessage());
                   Response resp=new Response(300,"Contacte al administrador");
                     ObjectOutputStream out= new ObjectOutputStream(cli.getOutputStream());
                   
                }

                
                
                
                
            }else if((new SHA256("Signature")).getSha().equals(request)){
                ArrayList<String> msg=(ArrayList<String>) req.getMessage();
                    System.out.println("Firmando voto....");
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
                             System.out.println(signature.length);
                             System.out.println("Voto Firmado: "+new String(Hex.encode(signature)));
                            Response resp=new Response(200,new String(Hex.encode(signature)));
                            
                            ObjectOutputStream out= new ObjectOutputStream(cli.getOutputStream());
                            out.writeObject(resp);


                        } catch (NoSuchProviderException | InvalidKeyException | SignatureException ex) {
                             System.out.println("Ocurrio un problema: "+ex.getMessage());
                        }    
                    }
                    
                           
                
            }
        } 
//        cli.close();
  
        
    }
    
    private static String imgPrepare(String dir, String ext){
       try{
        BufferedImage image = ImageIO.read(new File(dir));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, ext, baos);
        byte[] res=baos.toByteArray();
        return new String(Hex.encode(baos.toByteArray()));
        
        }catch(Exception e) {
             e.printStackTrace();
             return "";
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
