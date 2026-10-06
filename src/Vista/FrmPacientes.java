/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Modelo.Paciente;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author luise
 */
public class FrmPacientes extends javax.swing.JFrame {
    
    private Paciente.PacienteJDBC pacienteJDBC = new Paciente.PacienteJDBC();
    private int codigoPacienteSeleccionado = 0;
    /**
     * Creates new form FrmPacientes
     */
    
    public FrmPacientes() {
    initComponents();
    setLocationRelativeTo(null);
    cargarPacientes();
}
    
   

private void cargarPacientes() {

    try {

        Paciente.PacienteJDBC jdbc = new Paciente.PacienteJDBC();

        java.util.List<Paciente> lista = jdbc.listarPacientes();

        javax.swing.table.DefaultTableModel modelo =
                (javax.swing.table.DefaultTableModel) tblPacientes.getModel();

        modelo.setRowCount(0);

        for (Paciente paciente : lista) {

            modelo.addRow(new Object[]{
                paciente.getCodigoPaciente(),
                paciente.getNombreCompleto(),
                paciente.getEdad(),
                paciente.getTelefono(),
                paciente.getCorreo(),
                paciente.getTipoPaciente(),
                paciente.getEstado()
            });
        }

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(
                this,
                "Error al cargar pacientes: " + e.getMessage()
        );
    }
}
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtEdad = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cmbTipoPaciente = new javax.swing.JComboBox<>();
        btonGuardar = new javax.swing.JButton();
        btonActualizar = new javax.swing.JButton();
        btonEliminar = new javax.swing.JButton();
        btonLimpiar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        cmbEstado = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPacientes = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Nombre completo");

        txtNombre.setText("txtNombre");
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        jLabel2.setText("Edad");

        txtEdad.setText("txtEdad");
        txtEdad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEdadActionPerformed(evt);
            }
        });

        jLabel3.setText("Telefono");

        txtTelefono.setText("txtTelefono");
        txtTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoActionPerformed(evt);
            }
        });

        jLabel4.setText("Correo");

        txtCorreo.setText("txtCorreo");
        txtCorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCorreoActionPerformed(evt);
            }
        });

        jLabel5.setText("Tipo de pacientes");

        jLabel6.setText("Gestionar pacientes");

        cmbTipoPaciente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Estudiante", "Docente", "Administrativo", "Visitante" }));
        cmbTipoPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoPacienteActionPerformed(evt);
            }
        });

        btonGuardar.setText("Guardar");
        btonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btonGuardarActionPerformed(evt);
            }
        });

        btonActualizar.setText("Actualizar");
        btonActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btonActualizarActionPerformed(evt);
            }
        });

        btonEliminar.setText("Eliminar");
        btonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btonEliminarActionPerformed(evt);
            }
        });

        btonLimpiar.setText("Limpiar");
        btonLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btonLimpiarActionPerformed(evt);
            }
        });

        jLabel8.setText("Estado");

        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Inactivo" }));
        cmbEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEstadoActionPerformed(evt);
            }
        });

        tblPacientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Codigo", "Nombre", "Edad", "Telefono", "Correo", "Tipo", "Estado"
            }
        ));
        tblPacientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPacientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPacientes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabel7))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel8))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNombre)
                            .addComponent(txtEdad)
                            .addComponent(txtTelefono)
                            .addComponent(txtCorreo)
                            .addComponent(cmbTipoPaciente, 0, 216, Short.MAX_VALUE)
                            .addComponent(cmbEstado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(jLabel6)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btonGuardar)
                        .addGap(30, 30, 30)
                        .addComponent(btonActualizar)
                        .addGap(18, 18, 18)
                        .addComponent(btonEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addComponent(btonLimpiar)))
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel6)
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cmbTipoPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btonGuardar)
                    .addComponent(btonActualizar)
                    .addComponent(btonEliminar)
                    .addComponent(btonLimpiar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents                        

    private void cmbTipoPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoPacienteActionPerformed                                                
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTipoPacienteActionPerformed                                               

    private void btonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btonGuardarActionPerformed                                            
        try {

    String nombre = txtNombre.getText();
    int edad = Integer.parseInt(txtEdad.getText());
    String telefono = txtTelefono.getText();
    String correo = txtCorreo.getText();
    String tipoPaciente = cmbTipoPaciente.getSelectedItem().toString();
    String estado = cmbEstado.getSelectedItem().toString();

    Paciente paciente = new Paciente(
            0,
            nombre,
            edad,
            telefono,
            correo,
            tipoPaciente,
            estado
    );

    Paciente.PacienteJDBC jdbc = new Paciente.PacienteJDBC();

    jdbc.insertarPaciente(paciente);

    JOptionPane.showMessageDialog(
            this,
            "Paciente registrado correctamente."
    );

} catch (NumberFormatException e) {

    JOptionPane.showMessageDialog(
            this,
            "La edad debe ser un número."
    );

} catch (SQLException e) {

    JOptionPane.showMessageDialog(
            this,
            "Error al guardar el paciente: " + e.getMessage()
    );
}
    }//GEN-LAST:event_btonGuardarActionPerformed                                           

    private void txtEdadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEdadActionPerformed                                        
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEdadActionPerformed                                       

    private void txtTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoActionPerformed                                            
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoActionPerformed                                           

    private void txtCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCorreoActionPerformed                                          
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoActionPerformed                                         

    private void cmbEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEstadoActionPerformed                                          
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbEstadoActionPerformed                                         

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed                                          
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed                                         

    private void tblPacientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPacientesMouseClicked                                          
       int fila = tblPacientes.getSelectedRow();

if (fila >= 0) {

    txtNombre.setText(tblPacientes.getValueAt(fila, 1).toString());
    txtEdad.setText(tblPacientes.getValueAt(fila, 2).toString());
    txtTelefono.setText(tblPacientes.getValueAt(fila, 3).toString());
    txtCorreo.setText(tblPacientes.getValueAt(fila, 4).toString());

    cmbTipoPaciente.setSelectedItem(
            tblPacientes.getValueAt(fila, 5).toString()
    );

    cmbEstado.setSelectedItem(
            tblPacientes.getValueAt(fila, 6).toString()
    );
}
    }//GEN-LAST:event_tblPacientesMouseClicked                                         

    private void btonActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btonActualizarActionPerformed                                               
     
    try {

        int fila = tblPacientes.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un paciente de la tabla."
            );
            return;
        }

        int codigoPaciente = Integer.parseInt(
                tblPacientes.getValueAt(fila, 0).toString()
        );

        String nuevoEstado = cmbEstado.getSelectedItem().toString();

        Paciente.PacienteJDBC jdbc = new Paciente.PacienteJDBC();

        jdbc.actualizarEstado(codigoPaciente, nuevoEstado);

        JOptionPane.showMessageDialog(
                this,
                "Estado del paciente actualizado correctamente."
        );

        cargarPacientes();

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(
                this,
                "Error al actualizar el paciente: " + e.getMessage()
        );
    }

    }//GEN-LAST:event_btonActualizarActionPerformed                                              

    private void btonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btonEliminarActionPerformed                                             
        try {

    int fila = tblPacientes.getSelectedRow();

    if (fila == -1) {
        JOptionPane.showMessageDialog(
                this,
                "Seleccione un paciente de la tabla."
        );
        return;
    }

    int codigoPaciente = Integer.parseInt(
            tblPacientes.getValueAt(fila, 0).toString()
    );

    int confirmacion = JOptionPane.showConfirmDialog(
            this,
            "¿Está seguro de eliminar este paciente?",
            "Confirmar eliminación",
            JOptionPane.YES_NO_OPTION
    );

    if (confirmacion == JOptionPane.YES_OPTION) {

        Paciente.PacienteJDBC jdbc = new Paciente.PacienteJDBC();

        jdbc.eliminarPaciente(codigoPaciente);

        JOptionPane.showMessageDialog(
                this,
                "Paciente eliminado correctamente."
        );

        cargarPacientes();
    }

} catch (SQLException e) {

    JOptionPane.showMessageDialog(
            this,
            "Error al eliminar el paciente: " + e.getMessage()
    );
}
    }//GEN-LAST:event_btonEliminarActionPerformed                                            

    private void btonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btonLimpiarActionPerformed                                            
        txtNombre.setText("");
txtEdad.setText("");
txtTelefono.setText("");
txtCorreo.setText("");

cmbTipoPaciente.setSelectedIndex(0);
cmbEstado.setSelectedIndex(0);

tblPacientes.clearSelection();
    }//GEN-LAST:event_btonLimpiarActionPerformed                                           

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
            java.util.logging.Logger.getLogger(FrmPacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPacientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables                     
    private javax.swing.JButton btonActualizar;
    private javax.swing.JButton btonEliminar;
    private javax.swing.JButton btonGuardar;
    private javax.swing.JButton btonLimpiar;
    private javax.swing.JComboBox<String> cmbEstado;
    private javax.swing.JComboBox<String> cmbTipoPaciente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPacientes;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables                   
}
