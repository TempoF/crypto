/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Package;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import objects.Candidates;
import objects.Ips;
import objects.Request;
import objects.Response;
import objects.Votes;

/**
 *
 * @author Tempori
 */
public class Results extends javax.swing.JFrame {

    /**
     * Creates new form Results
     */
    
    public Results() {
        initComponents();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Candidate");
        model.addColumn("Party");
        model.addColumn("Percentage");
        this.jTable1.setModel(model);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/votex.jpg"))); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Regresar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        try {
            
            Socket sck=new Socket(Ips.getMI(),6986);
            ObjectOutputStream out= new ObjectOutputStream(sck.getOutputStream());
            SHA256 comd = new SHA256("checkPeriod"); 
            
            Request req=new Request(comd.getSha(),(Object)new ArrayList<>());
            out.writeObject(req);
            ObjectInputStream in = new ObjectInputStream(sck.getInputStream());
            Response resp=(Response)in.readObject();
            if (resp.getCode()!=200) {
                sck.close();
                JOptionPane.showMessageDialog(this, resp.getMessage());
                MenuAdm menu= new MenuAdm();
                menu.setVisible(true);
                this.dispose();
            }          
            
             sck=new Socket(Ips.getMI(),6986);
             out= new ObjectOutputStream(sck.getOutputStream());
             comd = new SHA256("CandidatesSimple"); 
            
             req=new Request(comd.getSha(),(Object)new ArrayList<>());
            out.writeObject(req);
             in = new ObjectInputStream(sck.getInputStream());
            ArrayList<Candidates> candidates=(ArrayList<Candidates>)in.readObject();
            System.out.println(candidates.size());
            
            sck=new Socket(Ips.getMV(),6987);
            out= new ObjectOutputStream(sck.getOutputStream());
            
            comd = new SHA256("GetVotes"); 
            ArrayList<String> sender = new ArrayList<>();
            
            req=new Request(comd.getSha(),(Object)sender);
            out.writeObject(req);
            in = new ObjectInputStream(sck.getInputStream());
            ArrayList<String> votesRes=(ArrayList<String>)in.readObject();
            System.out.println(votesRes.size());
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            Map<String, Integer> hm = new HashMap<String, Integer>(); 
  
            
            for (int i = 0; i < votesRes.size(); i++) {
                Integer j = hm.get(votesRes.get(i));
                hm.put(votesRes.get(i), (j == null) ? 1 : j + 1);
                
//                model.addRow(new Object[]{votesRes.get(i).getCandidate(), votesRes.get(i).getParty(), votesRes.get(i).getPercent()+"%"});
            }
            
            for (Map.Entry<String, Integer> val : hm.entrySet()) {
                System.out.println(val.getKey());
                for (int i = 0; i < candidates.size(); i++) {
                    if (candidates.get(i).getId().equals(val.getKey())) {
                        System.out.println(candidates.get(i).getId()+" ==== "+val.getKey());
                        double perc=((double)val.getValue()/(double)votesRes.size())*100.0;
                        String percs=String.format("%2.02f", (float)perc);
                        model.addRow(new Object[]{candidates.get(i).getName(), candidates.get(i).getParty(), percs+"%"});
                    }
                }
            } 
            jTable1.setModel(model);
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(Results.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Ocurrio un error");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Results.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Ocurrio un error");
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Results.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Ocurrio un error");
        }
    }//GEN-LAST:event_formWindowOpened

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        MenuAdm menu = new MenuAdm();
        menu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Results.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Results.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Results.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Results.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Results().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
