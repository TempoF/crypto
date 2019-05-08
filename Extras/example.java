package Interface;

import java.sql.Connection;


import Connectors.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
public class example extends javax.swing.JFrame {
    private connector conector;
    private Connection conn;
    
    private JScrollPane scroll;
    DefaultTableModel modelo;
    JTable tabla;
    public example() {
        initComponents();
        this.setLocationRelativeTo(null);
        modelo = new DefaultTableModel();
        tabla = new JTable(modelo);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        conectBTN = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        queryBTN = new javax.swing.JButton();
        exitBTN = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jText1 = new javax.swing.JTextField();
        jText2 = new javax.swing.JTextField();
        jText3 = new javax.swing.JTextField();
        jText4 = new javax.swing.JTextField();
        insertBTN = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        conectBTN.setText("Conect to DB");
        conectBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                conectBTNActionPerformed(evt);
            }
        });

        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "column1", "column2", "column3", "column4"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable);

        queryBTN.setText("Show all");
        queryBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                queryBTNActionPerformed(evt);
            }
        });

        exitBTN.setText("Salir");
        exitBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBTNActionPerformed(evt);
            }
        });

        jLabel1.setText("ID User:");

        jLabel2.setText("First Name:");

        jLabel3.setText("Last Name:");

        jLabel4.setText("Email: ");

        jText1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jText1ActionPerformed(evt);
            }
        });

        jText2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jText2ActionPerformed(evt);
            }
        });

        jText3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jText3ActionPerformed(evt);
            }
        });

        jText4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jText4ActionPerformed(evt);
            }
        });

        insertBTN.setText("Agregar!");
        insertBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertBTNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(queryBTN)
                        .addGap(71, 71, 71))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(conectBTN)
                        .addGap(59, 59, 59))))
            .addGroup(layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jText4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jText3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jText2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jText1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(insertBTN)
                .addGap(96, 96, 96)
                .addComponent(exitBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(281, 281, 281))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(164, 164, 164)
                        .addComponent(conectBTN)
                        .addGap(18, 18, 18)
                        .addComponent(queryBTN))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jText1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jText2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jText3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jText4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exitBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(insertBTN))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void prepareTable(ResultSet rs) throws SQLException{
        ResultSetMetaData metaData = rs.getMetaData();
        //Se obtiene el número de columnas
        int columns = metaData.getColumnCount();
        // Se crea un array de etiquetas para rellenar
        Object[] etiquetas = new Object[columns];
        //Se colocan cada etiqueta para cada columna
        for (int i=0;i < columns; i++){
            //Para ResultSetMetaData la primera columna es 1
            System.out.println("Columna "+ i + 1 + " = " + metaData.getColumnLabel(i+1));
            etiquetas[i] = metaData.getColumnLabel(i + 1);
        }
        //Se colocan las columnas obtenidas en la tabla
        
        modelo.setColumnIdentifiers(etiquetas);
        jTable.setModel(modelo);
        scroll = new JScrollPane(tabla);
        
        
    }
    private void conectBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_conectBTNActionPerformed
        String dbName = "conexionprueba";
        conector = new connector(dbName);
        conn = conector.connectBD();
        if (conn != null)
            System.out.println("Conectado a " + dbName + " Succesfully");
        else
            System.out.println("Error: " + dbName + " Database conection null");
    }//GEN-LAST:event_conectBTNActionPerformed

    private void queryBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_queryBTNActionPerformed
        String query = "SELECT * FROM USER";
        try{
            //PreparedStatement ps = conn.prepareStatement(query); 
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery (query);
            //Se obtiene la información de la tabla
            prepareTable(rs);
            int columns;
            while(rs.next()){
                columns = modelo.getColumnCount();
                Object[] datos = new Object[columns];
                // Se obtienen cada dato de un registro segun la query en un arreglo
                for (int i=0; i<columns; i++){
                    datos[i] = rs.getObject(i + 1);
                    System.out.println("datos["+i+"] = " + rs.getObject(i + 1));
                }
                modelo.addRow(datos);
            }
            System.out.println("Consulta finalizada");
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
    }//GEN-LAST:event_queryBTNActionPerformed

    private void exitBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBTNActionPerformed
        try{
            conn.close();
            System.out.println("Conexion finalizada");
            this.dispose();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_exitBTNActionPerformed

    private void jText1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jText1ActionPerformed
        
    }//GEN-LAST:event_jText1ActionPerformed

    private void jText2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jText2ActionPerformed
        
    }//GEN-LAST:event_jText2ActionPerformed

    private void jText3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jText3ActionPerformed
        
    }//GEN-LAST:event_jText3ActionPerformed

    private void jText4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jText4ActionPerformed
        
    }//GEN-LAST:event_jText4ActionPerformed

    private void insertBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertBTNActionPerformed
        //Boton para agregar en la tabla 
        try{
            String query = "INSERT INTO user VALUES (?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(jText1.getText()));
            ps.setString(2, jText2.getText());
            ps.setString(3, jText3.getText());
            ps.setString(4, jText4.getText());
            ps.executeUpdate();
            System.out.println("Registro agregado correctamente");
        }catch(SQLException e){
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_insertBTNActionPerformed

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
            java.util.logging.Logger.getLogger(example.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(example.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(example.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(example.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new example().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton conectBTN;
    private javax.swing.JButton exitBTN;
    private javax.swing.JButton insertBTN;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    private javax.swing.JTextField jText1;
    private javax.swing.JTextField jText2;
    private javax.swing.JTextField jText3;
    private javax.swing.JTextField jText4;
    private javax.swing.JButton queryBTN;
    // End of variables declaration//GEN-END:variables
}
