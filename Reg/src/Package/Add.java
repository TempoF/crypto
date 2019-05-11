package Package;

import Objects.Candidates;
import Objects.IdCandidate;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Add extends javax.swing.JFrame {
    ArrayList<Candidates> candidates=new ArrayList<>();
    String imagen;
    int NumCandidates;
    public Add() {
        initComponents();
        setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.white);
        Btn_Save.setEnabled(false);
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
        Btn_Save = new javax.swing.JButton();
        Btn_SearchImg = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Logo_Encabezado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Votex_white_2.jpg"))); // NOI18N

        Add_Title.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Add_Title.setText("Add Candidate:");

        jLabel1.setText("Name(s):");

        jLabel2.setText("First Surname:");

        jLabel3.setText("Second Surname:");

        jLabel4.setText("Political Party:");

        Lbl_Imagen.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, java.awt.Color.gray));

        Btn_Save.setText("Save");
        Btn_Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_SaveActionPerformed(evt);
            }
        });

        Btn_SearchImg.setText("Search Imagen");
        Btn_SearchImg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_SearchImgActionPerformed(evt);
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
                            .addComponent(Btn_Save))
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
                            .addComponent(Btn_Save))
                        .addContainerGap(22, Short.MAX_VALUE))
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
                            .addComponent(TextF_PoliticalParty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Btn_SearchImgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_SearchImgActionPerformed
        JFileChooser select = new JFileChooser();
        File fichero;
        
        int OPT = 0;
                while (OPT == 0){
                        FileNameExtensionFilter filtro = new FileNameExtensionFilter(".jpg, .bmp, .png & .gif","jpg","bmp","png","gif");
                        select.setFileFilter(filtro);
                        if(select.showDialog(null,"Open File") == JFileChooser.APPROVE_OPTION){
                                fichero = select.getSelectedFile();
                                imagen = fichero.getName();
                                Image img = new ImageIcon(select.getSelectedFile().getPath()).getImage();
                                ImageIcon img2 = new ImageIcon(img.getScaledInstance(160, 160, Image.SCALE_AREA_AVERAGING));
                                Lbl_Imagen.setIcon(img2);
                                OPT = 1;
                            }else JOptionPane.showMessageDialog(null,"Please select Imagen (>ω< *)");
                    }
                Btn_Save.setEnabled(true);
    }//GEN-LAST:event_Btn_SearchImgActionPerformed

    private void Btn_SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_SaveActionPerformed
        String ID;
        String Name;
        String N,AP,AM;
        String PoliticParty;
        String Img;
        
        N = TextF_Name.getText();
        AP = TextF_1rstSurname.getText();
        AM = TextF_2ndSurname.getText();
        PoliticParty = TextF_PoliticalParty.getText();
        Img = imagen;
        ID = IdCandidate.getID();
       
        if(!N.equals("") && !AP.equals("") && !AM.equals("") && !PoliticParty.equals("") && Img != null){
            JOptionPane.showMessageDialog(null, "Agregar Candidato");
            Name = TextF_Name.getText()+" "+TextF_1rstSurname.getText()+" "+TextF_2ndSurname.getText();
            //Meter a la Lista
            //candidates.add(new Candidates(Name,PoliticParty,ID));
            NumCandidates++;
            Btn_Save.setEnabled(false);
        }else JOptionPane.showMessageDialog(null, "All fields are required");
        
    }//GEN-LAST:event_Btn_SaveActionPerformed

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
    private javax.swing.JButton Btn_Save;
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