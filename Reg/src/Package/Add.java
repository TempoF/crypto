package Package;

import objects.Candidates;
import objects.IdCandidate;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import objects.Request;
import objects.Response;
import org.bouncycastle.util.encoders.Hex;

public class Add extends javax.swing.JFrame {
    ArrayList<Candidates> candidates=new ArrayList<>();
    String imgSender;
    int NumCandidates;
    String MID="66.70.157.20";
    public Add() {
        initComponents();
        setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.white);
        Btn_Save1.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Logo_Encabezado = new javax.swing.JLabel();
        Add_Title = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        TextF_Name = new javax.swing.JTextField();
        TextF_1rstSurname = new javax.swing.JTextField();
        TextF_2ndSurname = new javax.swing.JTextField();
        TextF_PoliticalParty = new javax.swing.JTextField();
        Lbl_Imagen = new javax.swing.JLabel();
        Btn_SearchImg = new javax.swing.JButton();
        Btn_Save1 = new javax.swing.JButton();
        Btn_Save2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Logo_Encabezado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Votex_white_2.jpg"))); // NOI18N

        Add_Title.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Add_Title.setText("Add Candidate:");

        jLabel1.setText("Name(s):");

        jLabel2.setText("First Surname:");

        jLabel3.setText("Second Surname:");

        jLabel4.setText("Political Party:");

        Lbl_Imagen.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, java.awt.Color.gray));

        Btn_SearchImg.setText("Search Imagen");
        Btn_SearchImg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_SearchImgActionPerformed(evt);
            }
        });

        Btn_Save1.setText("Save");
        Btn_Save1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Save1ActionPerformed(evt);
            }
        });

        Btn_Save2.setText("Back");
        Btn_Save2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Save2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(Logo_Encabezado))
                    .addComponent(Add_Title)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(TextF_2ndSurname, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TextF_1rstSurname, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TextF_Name, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TextF_PoliticalParty, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Btn_Save2)
                                .addGap(70, 70, 70)
                                .addComponent(Btn_Save1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Lbl_Imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(Btn_SearchImg)
                                .addGap(30, 30, 30)))))
                .addContainerGap(19, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Logo_Encabezado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Add_Title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Lbl_Imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Btn_SearchImg)
                            .addComponent(Btn_Save1)
                            .addComponent(Btn_Save2)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(TextF_Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(TextF_1rstSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(TextF_2ndSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(TextF_PoliticalParty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Btn_SearchImgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_SearchImgActionPerformed
       JFileChooser fl=new JFileChooser();
       FileNameExtensionFilter filtro = new FileNameExtensionFilter(".jpg, .bmp, .png & .gif","jpg","bmp","png","gif");
        fl.setFileFilter(filtro);
        int result= fl.showOpenDialog(this);
        if (result == JFileChooser.CANCEL_OPTION) {
            return;
        }
        File fll=fl.getSelectedFile();
        String imagen=fll.getAbsolutePath();
        String ext="";
        int i = imagen.lastIndexOf('.');
        if (i >= 0) {
            ext = imagen.substring(i+1);
        }
        
        imgSender=imgPrepare(imagen,ext);
        
        BufferedImage img = null;
        File f = null;
        try
        {
            f = new File(imagen);
            img = ImageIO.read(f);
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        
        ImageIcon imgi=new ImageIcon(img);
        Lbl_Imagen.setIcon(new ImageIcon(imgi.getImage().getScaledInstance(160, 160, Image.SCALE_DEFAULT)));
       
        Btn_Save1.setEnabled(true);
    }//GEN-LAST:event_Btn_SearchImgActionPerformed

   private String imgPrepare(String dir, String ext){
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
    private void Btn_Save1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Save1ActionPerformed
        String ID;
        String N,AP,AM;
        String PoliticParty;
        
        N = TextF_Name.getText();
        AP = TextF_1rstSurname.getText();
        AM = TextF_2ndSurname.getText();
        PoliticParty = TextF_PoliticalParty.getText();        
        ID = IdCandidate.getID();
        if(!N.equals("") && !AP.equals("") && !AM.equals("") && !PoliticParty.equals("") && !imgSender.equals("")){
            try {
                SHA256 shaid = new SHA256(ID+N); 
                Socket sck=new Socket(MID,6986);
                ObjectOutputStream out= new ObjectOutputStream(sck.getOutputStream());

                SHA256 comd = new SHA256("RegistryCandidates"); 
                Candidates sender = new Candidates(N+"--"+AP+"--"+AM,PoliticParty,shaid.getSha());                
                sender.setImage(imgSender);
               
                Request req=new Request(comd.getSha(),(Object)sender);
                out.writeObject(req);
                out.flush();
                ObjectInputStream in = new ObjectInputStream(sck.getInputStream());
                Response response=(Response)in.readObject();
                
                if (response.getCode()==200) {
                    //registro correcto
                    JOptionPane.showMessageDialog(this, response.getMessage());
                    this.TextF_Name.setText("");
                    this.TextF_1rstSurname.setText("");
                    this.TextF_2ndSurname.setText("");
                    this.TextF_PoliticalParty.setText("");
                    Lbl_Imagen.setIcon(null);
                    Lbl_Imagen.revalidate();
                     imgSender="";
                }else{
                    JOptionPane.showMessageDialog(this,response.getMessage());
                    this.TextF_Name.setText("");
                    this.TextF_1rstSurname.setText("");
                    this.TextF_2ndSurname.setText("");
                    this.TextF_PoliticalParty.setText("");
                    Lbl_Imagen.setIcon(null);
                    Lbl_Imagen.revalidate();
                     imgSender="";

                }
                sck.close();
            } catch (IOException ex) {
               JOptionPane.showMessageDialog(this, "Ocurrio un error en el registro, intentelo de nuevo.");
            } catch (NoSuchAlgorithmException ex) {
               JOptionPane.showMessageDialog(this, "Ocurrio un error en el registro, llame al tecnico.");
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Ocurrio un error en el registro, llame al tecnico.");
            }
            
            
            
            
        }else{
            TextF_Name.setText("");
            TextF_1rstSurname.setText("");
            TextF_2ndSurname.setText("");
            TextF_PoliticalParty.setText("");
            ID="";
            imgSender="";
            Lbl_Imagen.setIcon(null);
            Lbl_Imagen.revalidate();
            JOptionPane.showMessageDialog(null, "Todos los campos son requeridos.");
        }
        Btn_Save1.setEnabled(false);
    }//GEN-LAST:event_Btn_Save1ActionPerformed

    private void Btn_Save2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Save2ActionPerformed
        // TODO add your handling code here:
        MenuAdm menu=new MenuAdm();
        menu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_Btn_Save2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Add.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Add.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Add.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Add.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Add().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Add_Title;
    private javax.swing.JButton Btn_Save1;
    private javax.swing.JButton Btn_Save2;
    private javax.swing.JButton Btn_SearchImg;
    private javax.swing.JLabel Lbl_Imagen;
    private javax.swing.JLabel Logo_Encabezado;
    private javax.swing.JTextField TextF_1rstSurname;
    private javax.swing.JTextField TextF_2ndSurname;
    private javax.swing.JTextField TextF_Name;
    private javax.swing.JTextField TextF_PoliticalParty;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
