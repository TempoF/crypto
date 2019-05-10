
package Package;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Daniela
 */
public class AddCandidates extends javax.swing.JFrame {
    JFileChooser select = new JFileChooser();
    File fichero;
    String imagen;

    public AddCandidates() {
        initComponents();
        setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.white);
        BtnSave.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Label_Agregar_Candidato = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        Label_Name = new javax.swing.JLabel();
        Label_First_Surname = new javax.swing.JLabel();
        Lable_Second_Surname = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Txt_Name = new javax.swing.JTextField();
        LblImg = new javax.swing.JLabel();
        Btn_SearchIMG = new javax.swing.JButton();
        Txt_1stSurname = new javax.swing.JTextField();
        Txt_2ndSurname = new javax.swing.JTextField();
        Txt_PoliticalParty = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        BtnSave = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        Label_Agregar_Candidato.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Label_Agregar_Candidato.setText("Add Candidate:");

        Label_Name.setText("Name(s):");

        Label_First_Surname.setText("First Surname:");

        Lable_Second_Surname.setText("Second Surname:");

        jLabel1.setText("Political party:");

        Txt_Name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Txt_NameActionPerformed(evt);
            }
        });

        LblImg.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), java.awt.Color.gray));

        Btn_SearchIMG.setText("Search Imagen");
        Btn_SearchIMG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_SearchIMGActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Votex_white_2.jpg"))); // NOI18N

        BtnSave.setText("Save");
        BtnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(Label_Agregar_Candidato)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 39, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(13, 13, 13)))
                .addGap(20, 20, 20))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BtnSave)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(Lable_Second_Surname)
                                .addComponent(Label_First_Surname)
                                .addComponent(Label_Name)))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Txt_1stSurname)
                            .addComponent(Txt_Name)
                            .addComponent(Txt_2ndSurname)
                            .addComponent(Txt_PoliticalParty))
                        .addGap(18, 18, 18)
                        .addComponent(LblImg, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Btn_SearchIMG)
                        .addGap(29, 29, 29))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Label_Agregar_Candidato)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Label_Name)
                            .addComponent(Txt_Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Label_First_Surname)
                            .addComponent(Txt_1stSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Lable_Second_Surname)
                            .addComponent(Txt_2ndSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(Txt_PoliticalParty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(LblImg, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Btn_SearchIMG)
                    .addComponent(BtnSave))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Btn_SearchIMGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_SearchIMGActionPerformed

        //        select.setCurrentDirectory(new File(rutaImg));
                int OPT = 0;
                while (OPT == 0){
                        FileNameExtensionFilter filtro = new FileNameExtensionFilter(".jpg, .bmp, .png & .gif","jpg","bmp","png","gif");
                        select.setFileFilter(filtro);
                        if(select.showDialog(null,"Open File") == JFileChooser.APPROVE_OPTION){
                                fichero = select.getSelectedFile();
                                imagen = fichero.getName();
                //                System.out.println("Nombre de la imagen "+imagen);
                                Image img = new ImageIcon(select.getSelectedFile().getPath()).getImage();
                                ImageIcon img2 = new ImageIcon(img.getScaledInstance(160, 160, Image.SCALE_AREA_AVERAGING));
                                LblImg.setIcon(img2);
                                OPT = 1;
                            }else JOptionPane.showMessageDialog(null,"Debe Seleccionar una imagen (>ω< *)");
                    }
                BtnSave.setEnabled(true);
    }//GEN-LAST:event_Btn_SearchIMGActionPerformed

    private void Txt_NameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Txt_NameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Txt_NameActionPerformed

    private void BtnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSaveActionPerformed
        
    }//GEN-LAST:event_BtnSaveActionPerformed

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
            java.util.logging.Logger.getLogger(AddCandidates.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddCandidates.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddCandidates.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddCandidates.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddCandidates().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnSave;
    private javax.swing.JButton Btn_SearchIMG;
    private javax.swing.JLabel Label_Agregar_Candidato;
    private javax.swing.JLabel Label_First_Surname;
    private javax.swing.JLabel Label_Name;
    private javax.swing.JLabel Lable_Second_Surname;
    private javax.swing.JLabel LblImg;
    private javax.swing.JTextField Txt_1stSurname;
    private javax.swing.JTextField Txt_2ndSurname;
    private javax.swing.JTextField Txt_Name;
    private javax.swing.JTextField Txt_PoliticalParty;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
