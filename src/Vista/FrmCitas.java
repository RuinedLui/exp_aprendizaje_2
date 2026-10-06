/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Modelo.CitaMedica;
import Modelo.CitaMedica.CitaJDBC;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.List;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 *
 * @author luise
 */
public class FrmCitas extends javax.swing.JFrame {

    private CitaJDBC citaDAO;
    private DefaultTableModel modeloTabla;
    /**
     * Creates new form FrmCitas
     */
    public FrmCitas() {
        initComponents();
        setLocationRelativeTo(null);
        citaDAO = new CitaJDBC(); // Inicializa la conexión mediante CitaJDBC
        configurarTabla();
        refrescarTodasLasTablas();
        
        // Delimitador de visualización de datos en Modificar
        txtModCodigoPaciente.setEditable(false);
        cbModEstado.setEnabled(false);
        // Delimitador de visualización de datos en Eliminar
        txtElimCodigoPaciente.setEditable(false);
        txtElimCodigoDoctor.setEditable(false);
        txtElimFecha.setEditable(false);
        txtElimMotivo.setEditable(false);
        cbElimHora.setEnabled(false);
        cbElimEstado.setEnabled(false);
        // Delimitador de visualización de datos en Eliminar
        txtEstadoCodigoPaciente.setEditable(false);
        txtEstadoCodigoDoctor.setEditable(false);
        txtEstadoFecha.setEditable(false);
        txtEstadoMotivo.setEditable(false);
        cbEstadoHora.setEnabled(false);
    }
    private void configurarTabla() {
        String[] columnas = {"ID", "Paciente", "Doctor", "Fecha", "Hora", "Motivo", "Estado"};
        modeloTabla = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableConsultaCitas.setModel(modeloTabla);
    }

    public void cargarCitasEnTabla() {
        // Obtener el modelo de la tabla y limpiar las filas anteriores
        DefaultTableModel modelo = (DefaultTableModel) tableConsultaCitas.getModel();
        modelo.setRowCount(0); 

        try {
            // Consultar la lista de citas desde la base de datos usando CitaJDBC
            List<CitaMedica> lista = citaDAO.listarCitas();

            // Recorrer la lista e insertar cada cita como una fila en la JTable
            for (CitaMedica c : lista) {
                Object[] fila = new Object[]{
                    c.getCodigoCita(),
                    c.getCodigoPaciente(),
                    c.getCodigoDoctor(),
                    c.getFechaCita(),
                    c.getHoraCita(),
                    c.getMotivoConsulta(),
                    c.getEstadoCita()
                };
                modelo.addRow(fila);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                this, 
                "Error al cargar los datos en la tabla: " + e.getMessage(), 
                "Error SQL", 
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // Bloque de código para limpiar cajas de texto
    private void limpiarConsulta() {
        txtConsultarCitas.setText("");
    }
    
    private void limpiarFormulario() {
        // Si tienes un campo para buscar por código de cita:
        if (txtConsultarCitas != null) {
            txtConsultarCitas.setText("");
        }

        // Limpieza de cajas de texto
        txtModCodigoCita.setText("");
        txtElimCodigoCita.setText("");
        txtCodPacientes.setText("");
        txtCodDoctor.setText("");
        txtFechaCitas.setText("");
        txtMotivoCitas.setText("");

        if (cbHoraCitas != null && cbHoraCitas.getItemCount() > 0) {
            cbHoraCitas.setSelectedIndex(0);
        }
        if (cbEstado != null && cbEstado.getItemCount() > 0) {
            cbEstado.setSelectedIndex(0);
        }
    }
    
    private void limpiarCamposEstado() {
        txtEstadoCodigoCita.setText("");
        txtEstadoCodigoPaciente.setText("");
        txtEstadoCodigoDoctor.setText("");
        txtEstadoFecha.setText("");
        txtEstadoMotivo.setText("");
        cbEstadoNuevo.setSelectedIndex(0);
        cbEstadoHora.setSelectedIndex(0);
    }
    
    private void limpiarFormularioModificar() {
        // Si tienes un campo para buscar por código de cita:
        if (txtConsultarCitas != null) {
            txtConsultarCitas.setText("");
        }

        // Limpieza de cajas de texto
        txtModCodigoCita.setText("");
        txtModCodigoPaciente.setText("");
        txtModCodigoDoctor.setText("");
        txtModFecha.setText("");
        txtModMotivo.setText("");

        if (cbModHora != null && cbModHora.getItemCount() > 0) {
            cbModHora.setSelectedIndex(0);
        }
        if (cbModEstado != null && cbModEstado.getItemCount() > 0) {
            cbModEstado.setSelectedIndex(0);
        }
    }
    
    private void limpiarFormularioEliminar() {
        if (txtConsultarCitas != null) {
            txtConsultarCitas.setText("");
        }

        // Limpieza de cajas de texto
        txtElimCodigoCita.setText("");
        txtElimCodigoPaciente.setText("");
        txtElimCodigoDoctor.setText("");
        txtElimFecha.setText("");
        txtElimMotivo.setText("");

        if (cbElimHora != null && cbElimHora.getItemCount() > 0) {
            cbElimHora.setSelectedIndex(0);
        }
        if (cbElimEstado != null && cbElimEstado.getItemCount() > 0) {
            cbElimEstado.setSelectedIndex(0);
        }
    }
    
    public void cargarCitasEnTablaEstado() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Código Cita");
        modelo.addColumn("Código Paciente");
        modelo.addColumn("Código Doctor");
        modelo.addColumn("Fecha");
        modelo.addColumn("Hora");
        modelo.addColumn("Motivo");
        modelo.addColumn("Estado");

        try {
            List<CitaMedica> lista = citaDAO.listarCitas(); 
            for (CitaMedica c : lista) {
                Object[] fila = new Object[]{
                    c.getCodigoCita(),
                    c.getCodigoPaciente(),
                    c.getCodigoDoctor(),
                    c.getFechaCita(),
                    c.getHoraCita(),
                    c.getMotivoConsulta(),
                    c.getEstadoCita()
                };
                modelo.addRow(fila);
            }
            tblCitasEstado.setModel(modelo); 
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar citas en la tabla: " + e.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void refrescarTodasLasTablas() {
        cargarCitasEnTabla();       
        cargarCitasEnTablaEstado(); 
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
        tabCitasMedicas = new javax.swing.JTabbedPane();
        tabConsultar = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtConsultarCitas = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableConsultaCitas = new javax.swing.JTable();
        btnConsultarCitas = new javax.swing.JButton();
        btnMostrarCitas = new javax.swing.JButton();
        tabRegistrar = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtCodPacientes = new javax.swing.JTextField();
        txtCodDoctor = new javax.swing.JTextField();
        txtFechaCitas = new javax.swing.JTextField();
        txtMotivoCitas = new javax.swing.JTextField();
        btnRegistrarCitas = new javax.swing.JButton();
        cbEstado = new javax.swing.JComboBox<>();
        cbHoraCitas = new javax.swing.JComboBox<>();
        tabModificar = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtModCodigoPaciente = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtModCodigoDoctor = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtModFecha = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtModMotivo = new javax.swing.JTextField();
        btnModificarCitas = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        txtModCodigoCita = new javax.swing.JTextField();
        cbModEstado = new javax.swing.JComboBox<>();
        cbModHora = new javax.swing.JComboBox<>();
        btnConsultarCitas4 = new javax.swing.JButton();
        tabEliminar = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        txtElimCodigoCita = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtElimCodigoPaciente = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtElimCodigoDoctor = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtElimFecha = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtElimMotivo = new javax.swing.JTextField();
        btnEliminarCitas = new javax.swing.JButton();
        cbElimEstado = new javax.swing.JComboBox<>();
        cbElimHora = new javax.swing.JComboBox<>();
        btnConsultarCitas5 = new javax.swing.JButton();
        tabConsultarDoctores = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        txtEstadoCodigoCita = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtEstadoCodigoPaciente = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txtEstadoFecha = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        cbEstadoHora = new javax.swing.JComboBox<>();
        jLabel30 = new javax.swing.JLabel();
        txtEstadoCodigoDoctor = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        cbEstadoNuevo = new javax.swing.JComboBox<>();
        jLabel32 = new javax.swing.JLabel();
        txtEstadoMotivo = new javax.swing.JTextField();
        btnGuardarEstado = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCitasEstado = new javax.swing.JTable();
        btnBuscarEstado = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Módulo: Citas Médicas");

        tabCitasMedicas.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel2.setText("Buscar citas:");

        txtConsultarCitas.setToolTipText("Código de cita");
        txtConsultarCitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtConsultarCitasActionPerformed(evt);
            }
        });

        tableConsultaCitas.setModel(new javax.swing.table.DefaultTableModel(
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
        tableConsultaCitas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableConsultaCitasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableConsultaCitas);

        btnConsultarCitas.setText("Buscar");
        btnConsultarCitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarCitasActionPerformed(evt);
            }
        });

        btnMostrarCitas.setText("Mostrar Citas");
        btnMostrarCitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarCitasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabConsultarLayout = new javax.swing.GroupLayout(tabConsultar);
        tabConsultar.setLayout(tabConsultarLayout);
        tabConsultarLayout.setHorizontalGroup(
            tabConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabConsultarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(tabConsultarLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtConsultarCitas, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnConsultarCitas)
                        .addGap(18, 18, 18)
                        .addComponent(btnMostrarCitas)
                        .addContainerGap())))
        );
        tabConsultarLayout.setVerticalGroup(
            tabConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabConsultarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtConsultarCitas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsultarCitas)
                    .addComponent(btnMostrarCitas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabCitasMedicas.addTab("Consultar Citas", tabConsultar);

        jLabel6.setText("Código Paciente:");

        jLabel7.setText("Código Doctor:");

        jLabel8.setText("Fecha Cita:");

        jLabel9.setText("Hora Cita:");

        jLabel10.setText("Estado Cita:");

        jLabel11.setText("Motivo Cita:");

        btnRegistrarCitas.setText("Guardar");
        btnRegistrarCitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarCitasActionPerformed(evt);
            }
        });

        cbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Programada", "Atendida", "Cancelada" }));

        cbHoraCitas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "06:00 AM", "07:00 AM", "08:00 AM", "09:00 AM", "10:00 AM", "11:00 AM", "12:00 PM", "01:00 PM", "02:00 PM", "03:00 PM", "04:00 PM", "05:00 PM", "06:00 PM", "07:00 PM" }));

        javax.swing.GroupLayout tabRegistrarLayout = new javax.swing.GroupLayout(tabRegistrar);
        tabRegistrar.setLayout(tabRegistrarLayout);
        tabRegistrarLayout.setHorizontalGroup(
            tabRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabRegistrarLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(tabRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabRegistrarLayout.createSequentialGroup()
                        .addGroup(tabRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tabRegistrarLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMotivoCitas))
                            .addGroup(tabRegistrarLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFechaCitas, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(cbHoraCitas, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(cbEstado, 0, 203, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(tabRegistrarLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 146, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35))))
            .addGroup(tabRegistrarLayout.createSequentialGroup()
                .addGap(305, 305, 305)
                .addComponent(btnRegistrarCitas)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        tabRegistrarLayout.setVerticalGroup(
            tabRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabRegistrarLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(tabRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtCodPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtCodDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(tabRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtFechaCitas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(cbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbHoraCitas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addGroup(tabRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtMotivoCitas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66)
                .addComponent(btnRegistrarCitas)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        tabCitasMedicas.addTab("Registrar", tabRegistrar);

        jLabel12.setText("Código Paciente:");

        jLabel13.setText("Código Doctor:");

        jLabel14.setText("Fecha Cita:");

        jLabel15.setText("Hora Cita:");

        jLabel16.setText("Estado Cita:");

        jLabel17.setText("Motivo Cita:");

        btnModificarCitas.setText("Guardar");
        btnModificarCitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarCitasActionPerformed(evt);
            }
        });

        jLabel18.setText("Código Cita:");

        cbModEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Programada", "Atendida", "Cancelada" }));

        cbModHora.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "06:00 AM", "07:00 AM", "08:00 AM", "09:00 AM", "10:00 AM", "11:00 AM", "12:00 PM", "01:00 PM", "02:00 PM", "03:00 PM", "04:00 PM", "05:00 PM", "06:00 PM", "07:00 PM" }));

        btnConsultarCitas4.setText("Buscar");
        btnConsultarCitas4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarCitas4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabModificarLayout = new javax.swing.GroupLayout(tabModificar);
        tabModificar.setLayout(tabModificarLayout);
        tabModificarLayout.setHorizontalGroup(
            tabModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabModificarLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(tabModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tabModificarLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtModMotivo))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tabModificarLayout.createSequentialGroup()
                        .addGroup(tabModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(tabModificarLayout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtModFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel15)
                                .addGap(18, 18, 18)
                                .addComponent(cbModHora, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(tabModificarLayout.createSequentialGroup()
                                .addGroup(tabModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(tabModificarLayout.createSequentialGroup()
                                        .addComponent(jLabel18)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtModCodigoCita))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tabModificarLayout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtModCodigoPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(btnConsultarCitas4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(tabModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(tabModificarLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel16)
                                .addGap(18, 18, 18)
                                .addComponent(cbModEstado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(tabModificarLayout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtModCodigoDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(37, 37, 37))
            .addGroup(tabModificarLayout.createSequentialGroup()
                .addGap(305, 305, 305)
                .addComponent(btnModificarCitas)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tabModificarLayout.setVerticalGroup(
            tabModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabModificarLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(tabModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtModCodigoCita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsultarCitas4))
                .addGap(18, 18, 18)
                .addGroup(tabModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtModCodigoPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(txtModCodigoDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(tabModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtModFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(cbModEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbModHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(tabModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtModMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnModificarCitas)
                .addContainerGap(147, Short.MAX_VALUE))
        );

        tabCitasMedicas.addTab("Modificar", tabModificar);

        jLabel19.setText("Código Cita:");

        jLabel20.setText("Código Paciente:");

        jLabel21.setText("Código Doctor:");

        jLabel22.setText("Fecha Cita:");

        jLabel23.setText("Hora Cita:");

        jLabel24.setText("Estado Cita:");

        jLabel25.setText("Motivo Cita:");

        btnEliminarCitas.setText("Guardar");
        btnEliminarCitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarCitasActionPerformed(evt);
            }
        });

        cbElimEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Programada", "Atendida", "Cancelada" }));

        cbElimHora.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "06:00 AM", "07:00 AM", "08:00 AM", "09:00 AM", "10:00 AM", "11:00 AM", "12:00 PM", "01:00 PM", "02:00 PM", "03:00 PM", "04:00 PM", "05:00 PM", "06:00 PM", "07:00 PM" }));

        btnConsultarCitas5.setText("Buscar");
        btnConsultarCitas5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarCitas5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabEliminarLayout = new javax.swing.GroupLayout(tabEliminar);
        tabEliminar.setLayout(tabEliminarLayout);
        tabEliminarLayout.setHorizontalGroup(
            tabEliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabEliminarLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(tabEliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tabEliminarLayout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtElimMotivo))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tabEliminarLayout.createSequentialGroup()
                        .addGroup(tabEliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tabEliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(tabEliminarLayout.createSequentialGroup()
                                    .addComponent(jLabel19)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtElimCodigoCita))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tabEliminarLayout.createSequentialGroup()
                                    .addComponent(jLabel20)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtElimCodigoPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(tabEliminarLayout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtElimFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel23)))
                        .addGap(18, 18, 18)
                        .addGroup(tabEliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tabEliminarLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtElimCodigoDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(tabEliminarLayout.createSequentialGroup()
                                .addComponent(cbElimHora, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel24)
                                .addGap(18, 18, 18)
                                .addComponent(cbElimEstado, 0, 143, Short.MAX_VALUE))
                            .addGroup(tabEliminarLayout.createSequentialGroup()
                                .addComponent(btnConsultarCitas5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(46, 46, 46))
            .addGroup(tabEliminarLayout.createSequentialGroup()
                .addGap(303, 303, 303)
                .addComponent(btnEliminarCitas)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tabEliminarLayout.setVerticalGroup(
            tabEliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabEliminarLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(tabEliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtElimCodigoCita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsultarCitas5))
                .addGap(18, 18, 18)
                .addGroup(tabEliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtElimCodigoPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(txtElimCodigoDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(tabEliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(txtElimFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24)
                    .addComponent(cbElimEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbElimHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(tabEliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(txtElimMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnEliminarCitas)
                .addContainerGap(147, Short.MAX_VALUE))
        );

        tabCitasMedicas.addTab("Eliminar", tabEliminar);

        jLabel26.setText("Código Cita:");

        jLabel27.setText("Código Paciente:");

        jLabel28.setText("Fecha Cita:");

        jLabel29.setText("Hora Cita:");

        cbEstadoHora.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "06:00 AM", "07:00 AM", "08:00 AM", "09:00 AM", "10:00 AM", "11:00 AM", "12:00 PM", "01:00 PM", "02:00 PM", "03:00 PM", "04:00 PM", "05:00 PM", "06:00 PM", "07:00 PM" }));

        jLabel30.setText("Código Doctor:");

        jLabel31.setText("Estado Cita:");

        cbEstadoNuevo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Programada", "Atendida", "Cancelada" }));

        jLabel32.setText("Motivo Cita:");

        btnGuardarEstado.setText("Guardar");
        btnGuardarEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarEstadoActionPerformed(evt);
            }
        });

        tblCitasEstado.setModel(new javax.swing.table.DefaultTableModel(
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
        tblCitasEstado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCitasEstadoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCitasEstado);

        btnBuscarEstado.setText("Buscar");
        btnBuscarEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarEstadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabConsultarDoctoresLayout = new javax.swing.GroupLayout(tabConsultarDoctores);
        tabConsultarDoctores.setLayout(tabConsultarDoctoresLayout);
        tabConsultarDoctoresLayout.setHorizontalGroup(
            tabConsultarDoctoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabConsultarDoctoresLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(tabConsultarDoctoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabConsultarDoctoresLayout.createSequentialGroup()
                        .addGroup(tabConsultarDoctoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tabConsultarDoctoresLayout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEstadoCodigoCita, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(tabConsultarDoctoresLayout.createSequentialGroup()
                                .addComponent(jLabel28)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEstadoFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(2, 2, 2)
                        .addGroup(tabConsultarDoctoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tabConsultarDoctoresLayout.createSequentialGroup()
                                .addComponent(btnBuscarEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel27))
                            .addGroup(tabConsultarDoctoresLayout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jLabel29)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbEstadoHora, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(tabConsultarDoctoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabConsultarDoctoresLayout.createSequentialGroup()
                                .addComponent(txtEstadoCodigoPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel30)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEstadoCodigoDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabConsultarDoctoresLayout.createSequentialGroup()
                                .addComponent(jLabel31)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbEstadoNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabConsultarDoctoresLayout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEstadoMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, 593, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(tabConsultarDoctoresLayout.createSequentialGroup()
                .addGap(307, 307, 307)
                .addComponent(btnGuardarEstado)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tabConsultarDoctoresLayout.setVerticalGroup(
            tabConsultarDoctoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabConsultarDoctoresLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(tabConsultarDoctoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(txtEstadoCodigoCita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27)
                    .addComponent(txtEstadoCodigoPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEstadoCodigoDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30)
                    .addComponent(btnBuscarEstado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabConsultarDoctoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(txtEstadoFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29)
                    .addComponent(cbEstadoHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31)
                    .addComponent(cbEstadoNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabConsultarDoctoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(txtEstadoMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGuardarEstado)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabCitasMedicas.addTab("Cambiar Estado", tabConsultarDoctores);

        jButton1.setText("Regresar al Menú Principal");
        jButton1.setMaximumSize(new java.awt.Dimension(115, 23));
        jButton1.setMinimumSize(new java.awt.Dimension(115, 23));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setLabel("Salir del Sistema");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tabCitasMedicas, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabCitasMedicas, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents                        

    private void btnBuscarEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarEstadoActionPerformed                                                
        // TODO add your handling code here:
        String txtCodigo = txtEstadoCodigoCita.getText().trim();

        if (txtCodigo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un código de cita para buscar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int codigoCita = Integer.parseInt(txtCodigo);
            CitaMedica cita = citaDAO.buscarCita(codigoCita);

            if (cita != null) {
                // Llenar las cajas de texto
                txtEstadoCodigoPaciente.setText(String.valueOf(cita.getCodigoPaciente()));
                txtEstadoCodigoDoctor.setText(String.valueOf(cita.getCodigoDoctor()));
                txtEstadoFecha.setText(cita.getFechaCita());
                txtEstadoMotivo.setText(cita.getMotivoConsulta());

                // Seleccionar en ComboBoxes
                cbEstadoNuevo.setSelectedItem(cita.getEstadoCita());
                seleccionarHoraEnComboBox(cbEstadoHora, cita.getHoraCita());

            } else {
                JOptionPane.showMessageDialog(this, "No existe ninguna cita con el código: " + codigoCita, "Sin Resultados", JOptionPane.INFORMATION_MESSAGE);
                limpiarCamposEstado();
                refrescarTodasLasTablas();
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El código de la cita debe ser un número entero.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error de consulta: " + e.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnBuscarEstadoActionPerformed                                               

    private void tblCitasEstadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCitasEstadoMouseClicked                                            
        // TODO add your handling code here:
        int fila = tblCitasEstado.getSelectedRow();

        if (fila != -1) {
            txtEstadoCodigoCita.setText(tblCitasEstado.getValueAt(fila, 0).toString());
            txtEstadoCodigoPaciente.setText(tblCitasEstado.getValueAt(fila, 1).toString());
            txtEstadoCodigoDoctor.setText(tblCitasEstado.getValueAt(fila, 2).toString());
            txtEstadoFecha.setText(tblCitasEstado.getValueAt(fila, 3).toString());

            String hora = tblCitasEstado.getValueAt(fila, 4).toString();
            seleccionarHoraEnComboBox(cbEstadoHora, hora);

            txtEstadoMotivo.setText(tblCitasEstado.getValueAt(fila, 5).toString());
            cbEstadoNuevo.setSelectedItem(tblCitasEstado.getValueAt(fila, 6).toString());
        }
    }//GEN-LAST:event_tblCitasEstadoMouseClicked                                           

    // Registrar datos de citas
    private void btnGuardarEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarEstadoActionPerformed                                                 
        // TODO add your handling code here:
        String txtCodigo = txtEstadoCodigoCita.getText().trim();

        // Validar que se haya seleccionado o buscado una cita
        if (txtCodigo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione una cita de la tabla o busque una por su código.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int codigoCita = Integer.parseInt(txtCodigo);
            String nuevoEstado = cbEstadoNuevo.getSelectedItem().toString();

            // Ejecutar la actualización en la base de datos
            citaDAO.cambiarEstado(codigoCita, nuevoEstado);

            // Confirmación al usuario
            JOptionPane.showMessageDialog(this, "El estado de la cita #" + codigoCita + " se actualizó correctamente a '" + nuevoEstado + "'.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            // Refrescar la tabla y limpiar las cajas
            refrescarTodasLasTablas();
            limpiarCamposEstado();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El código de la cita debe ser un número entero.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar el estado: " + e.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarEstadoActionPerformed                                                

    private void btnConsultarCitas5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarCitas5ActionPerformed                                                   
        // TODO add your handling code here:
        String txtCodigo = txtElimCodigoCita.getText().trim();

        if (txtCodigo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el Código de Cita que desea consultar para eliminar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int codigoCita = Integer.parseInt(txtCodigo);
            CitaMedica cita = citaDAO.buscarCita(codigoCita);

            if (cita != null) {
                // Muestra los datos únicamente como vista previa de confirmación
                txtElimCodigoPaciente.setText(String.valueOf(cita.getCodigoPaciente()));
                txtElimCodigoDoctor.setText(String.valueOf(cita.getCodigoDoctor()));
                txtElimFecha.setText(cita.getFechaCita());
                txtElimMotivo.setText(cita.getMotivoConsulta());
                cbElimEstado.setSelectedItem(cita.getEstadoCita());
                seleccionarHoraEnComboBox(cbElimHora, cita.getHoraCita());

            } else {
                JOptionPane.showMessageDialog(this, "No existe ninguna cita con el código: " + codigoCita, "Sin Resultados", JOptionPane.INFORMATION_MESSAGE);
                limpiarFormularioEliminar();
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El código debe ser numérico.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error de consulta: " + e.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnConsultarCitas5ActionPerformed                                                  

    // Eliminar datos de citas
    private void btnEliminarCitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarCitasActionPerformed                                                 
        // TODO add your handling code here:
        String txtCodigo = txtElimCodigoCita.getText().trim();

        if (txtCodigo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione una cita en la tabla de la pestaña Consultar para eliminar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int codigoCita = Integer.parseInt(txtCodigo);

            int confirmacion = JOptionPane.showConfirmDialog(
                this,
                "¿Desea eliminar definitivamente la cita #" + codigoCita + "?\nEsta acción no se puede deshacer.",
                "Confirmar Eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );

            if (confirmacion == JOptionPane.YES_OPTION) {
                citaDAO.eliminarCita(codigoCita);

                JOptionPane.showMessageDialog(this, "La cita se eliminó correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                limpiarFormularioEliminar();
                refrescarTodasLasTablas();
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar la cita: " + e.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarCitasActionPerformed                                                

    private void btnConsultarCitas4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarCitas4ActionPerformed                                                   
        // TODO add your handling code here:
        String txtCodigo = txtModCodigoCita.getText().trim();

        if (txtCodigo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el Código de la Cita que desea modificar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int codigoCita = Integer.parseInt(txtCodigo);
            CitaMedica cita = citaDAO.buscarCita(codigoCita);

            if (cita != null) {
                // Validar de entrada si la cita se puede modificar
                if (!cita.getEstadoCita().equalsIgnoreCase("Programada")) {
                    JOptionPane.showMessageDialog(this,
                        "La cita #" + codigoCita + " está en estado '" + cita.getEstadoCita() + "' y no se puede modificar.\nSolo se permiten cambios en citas 'Programadas'.",
                        "Acción no permitida",
                        JOptionPane.WARNING_MESSAGE);
                    limpiarFormularioModificar();
                    return;
                }

                // Cargar datos a los campos de edición
                txtModCodigoPaciente.setText(String.valueOf(cita.getCodigoPaciente()));
                txtModCodigoDoctor.setText(String.valueOf(cita.getCodigoDoctor()));
                txtModFecha.setText(cita.getFechaCita());
                txtModMotivo.setText(cita.getMotivoConsulta());
                cbModEstado.setSelectedItem(cita.getEstadoCita());
                seleccionarHoraEnComboBox(cbModHora, cita.getHoraCita());

            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ninguna cita registrada con el código: " + codigoCita, "Sin Resultados", JOptionPane.INFORMATION_MESSAGE);
                limpiarFormularioModificar();
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El código de cita debe ser un número entero.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al consultar la cita: " + e.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnConsultarCitas4ActionPerformed                                                  

    // Modificar datos de citas
    private void btnModificarCitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarCitasActionPerformed                                                  
        // TODO add your handling code here:
        if (txtModCodigoCita.getText().trim().isEmpty() ||
            txtModCodigoPaciente.getText().trim().isEmpty() ||
            txtModCodigoDoctor.getText().trim().isEmpty() ||
            txtModFecha.getText().trim().isEmpty() ||
            txtModMotivo.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(this, "Complete todos los campos para modificar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int idCita = Integer.parseInt(txtModCodigoCita.getText().trim());
            int idPaciente = Integer.parseInt(txtModCodigoPaciente.getText().trim());
            int idDoctor = Integer.parseInt(txtModCodigoDoctor.getText().trim());
            String motivo = txtModMotivo.getText().trim();

            // Formatear Fecha
            String fechaInput = txtModFecha.getText().trim();
            String fechaSQL = "";
            if (fechaInput.matches("\\d{4}-\\d{2}-\\d{2}")) {
                fechaSQL = fechaInput;
            } else if (fechaInput.matches("\\d{1,2}/\\d{1,2}/\\d{4}")) {
                java.text.SimpleDateFormat parser = new java.text.SimpleDateFormat("dd/MM/yyyy");
                java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
                fechaSQL = formatter.format(parser.parse(fechaInput));
            } else {
                JOptionPane.showMessageDialog(this, "Ingrese la fecha en formato YYYY-MM-DD o DD/MM/YYYY", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Formatear Hora (12h AM/PM a 24h)
            String horaInput = cbModHora.getSelectedItem() != null ? cbModHora.getSelectedItem().toString().trim() : "08:00 AM";
            String horaSQL = "08:00:00";
            if (horaInput.toUpperCase().contains("AM") || horaInput.toUpperCase().contains("PM")) {
                java.text.SimpleDateFormat parserHora = new java.text.SimpleDateFormat("hh:mm a", java.util.Locale.US);
                java.text.SimpleDateFormat formatterHora = new java.text.SimpleDateFormat("HH:mm:ss");
                horaSQL = formatterHora.format(parserHora.parse(horaInput));
            } else {
                horaSQL = horaInput.length() == 5 ? horaInput + ":00" : horaInput;
            }

            // Obtener Estado
            String estado = cbModEstado.getSelectedItem() != null ? cbModEstado.getSelectedItem().toString() : "Programada";

            // Instanciar y ejecutar el método actualizarCita
            CitaMedica citaModificada = new CitaMedica(idCita, idPaciente, idDoctor, fechaSQL, horaSQL, motivo, estado);

            citaDAO.actualizarCita(citaModificada);

            JOptionPane.showMessageDialog(this, "¡Cita actualizada exitosamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarFormularioModificar();
            refrescarTodasLasTablas();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Los códigos deben ser números enteros.", "Error de Datos", JOptionPane.ERROR_MESSAGE);
        } catch (java.text.ParseException e) {
            JOptionPane.showMessageDialog(this, "Error al procesar el formato de la fecha o la hora.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            // Muestra el mensaje enviado (ej: "Solo se pueden modificar citas 'Programada'.")
            JOptionPane.showMessageDialog(this, e.getMessage(), "Atención", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnModificarCitasActionPerformed                                                 

    // Registrar datos de citas
    private void btnRegistrarCitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarCitasActionPerformed                                                  
        // TODO add your handling code here:
        // Validar campos vacíos
        if (txtCodPacientes.getText().trim().isEmpty() ||
            txtCodDoctor.getText().trim().isEmpty() ||
            txtFechaCitas.getText().trim().isEmpty() ||
            txtMotivoCitas.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int idPaciente = Integer.parseInt(txtCodPacientes.getText().trim());
            int idDoctor = Integer.parseInt(txtCodDoctor.getText().trim());
            String motivo = txtMotivoCitas.getText().trim();

            // Procesar Fecha (12/10/2026, 12-10-2026 y 2026-10-12)
            String fechaInput = txtFechaCitas.getText().trim();
            String fechaSQL = "";

            if (fechaInput.matches("\\d{4}-\\d{2}-\\d{2}")) {
                fechaSQL = fechaInput;
            } else if (fechaInput.matches("\\d{1,2}/\\d{1,2}/\\d{4}")) {
                java.text.SimpleDateFormat parser = new java.text.SimpleDateFormat("dd/MM/yyyy");
                java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
                fechaSQL = formatter.format(parser.parse(fechaInput));
            } else if (fechaInput.matches("\\d{1,2}-\\d{1,2}-\\d{4}")) {
                java.text.SimpleDateFormat parser = new java.text.SimpleDateFormat("dd-MM-yyyy");
                java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
                fechaSQL = formatter.format(parser.parse(fechaInput));
            } else {
                JOptionPane.showMessageDialog(this, "Formato de fecha no válido. Use DD/MM/YYYY o YYYY-MM-DD", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Control de fecha pasada
            if (esFechaPasada(fechaSQL)) {
                JOptionPane.showMessageDialog(this, "No se puede registrar una cita en una fecha pasada.", "Fecha Inválida", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Procesar Hora (Convierte "08:00 AM" a "08:00:00")
            String horaInput = cbHoraCitas.getSelectedItem() != null ? cbHoraCitas.getSelectedItem().toString().trim() : "08:00 AM";
            String horaSQL = "08:00:00";

            try {
                if (horaInput.toUpperCase().contains("AM") || horaInput.toUpperCase().contains("PM")) {
                    java.text.SimpleDateFormat parserHora = new java.text.SimpleDateFormat("hh:mm a", java.util.Locale.US);
                    java.text.SimpleDateFormat formatterHora = new java.text.SimpleDateFormat("HH:mm:ss");
                    horaSQL = formatterHora.format(parserHora.parse(horaInput));
                } else {
                    horaSQL = horaInput.length() == 5 ? horaInput + ":00" : horaInput;
                }
            } catch (java.text.ParseException e) {
                horaSQL = "08:00:00";
            }

            // Obtener Estado
            String estado = cbEstado.getSelectedItem() != null ? cbEstado.getSelectedItem().toString() : "Programada";

            // Disponibilidad del Doctor
            if (!citaDAO.doctorDisponible(idDoctor, fechaSQL, horaSQL)) {
                JOptionPane.showMessageDialog(this,
                    "El doctor #" + idDoctor + " ya tiene una cita agendada para la fecha " + fechaSQL + " a las " + horaInput + ".",
                    "Doctor Ocupado",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Disponibilidad del Paciente
            if (!citaDAO.pacienteDisponible(idPaciente, fechaSQL, horaSQL)) {
                JOptionPane.showMessageDialog(this,
                    "El paciente #" + idPaciente + " ya tiene otra cita agendada para la fecha " + fechaSQL + " a las " + horaInput + ".",
                    "Paciente Ocupado",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            // Validar si el paciente existe en la BD
            if (!citaDAO.existePaciente(idPaciente)) {
                JOptionPane.showMessageDialog(this,
                    "El código de paciente #" + idPaciente + " no existe registrado.",
                    "Paciente No Encontrado",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Validar si el doctor existe en la BD
            if (!citaDAO.existeDoctor(idDoctor)) {
                JOptionPane.showMessageDialog(this,
                    "El código de doctor #" + idDoctor + " no existe registrado.",
                    "Doctor No Encontrado",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            CitaMedica nuevaCita = new CitaMedica(idPaciente, idDoctor, fechaSQL, horaSQL, motivo, estado);
            citaDAO.insertarCita(nuevaCita);

            JOptionPane.showMessageDialog(this, "¡Cita registrada correctamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarFormulario();
            refrescarTodasLasTablas();

        } catch (java.text.ParseException e) {
            JOptionPane.showMessageDialog(this, "Error al procesar el formato de la fecha u hora.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Los códigos de Paciente y Doctor deben ser números.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error de Base de Datos: " + e.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnRegistrarCitasActionPerformed                                                 

    // Mostrar datos de citas
    private void btnMostrarCitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarCitasActionPerformed                                                
        // TODO add your handling code here:
        limpiarConsulta();
        refrescarTodasLasTablas();

        JOptionPane.showMessageDialog(this, "Se han vuelto a listar todas las citas.", "Tabla Actualizada", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnMostrarCitasActionPerformed                                               

    // Consultar datos por código de citas
    private void btnConsultarCitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarCitasActionPerformed                                                  
        // TODO add your handling code here:
        String txtCodigo = txtConsultarCitas.getText().trim();

        // Validar que la caja de texto no esté vacía
        if (txtCodigo.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Ingrese el código de la cita a buscar.",
                "Aviso",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        try {
            int codigoCita = Integer.parseInt(txtCodigo);
            CitaMedica cita = citaDAO.buscarCita(codigoCita);

            if (cita != null) {
                // Cita encontrada
                modeloTabla.setRowCount(0);

                Object[] fila = new Object[]{
                    cita.getCodigoCita(),
                    cita.getCodigoPaciente(),
                    cita.getCodigoDoctor(),
                    cita.getFechaCita(),
                    cita.getHoraCita(),
                    cita.getMotivoConsulta(),
                    cita.getEstadoCita()
                };

                modeloTabla.addRow(fila);
                JOptionPane.showMessageDialog(
                    this,
                    "Cita encontrada y cargada en la tabla.",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE
                );

            } else {
                // Cita NO encontrada
                modeloTabla.setRowCount(0);
                JOptionPane.showMessageDialog(
                    this,
                    "No existe ninguna cita registrada con el código: " + codigoCita,
                    "Sin resultados",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                this,
                "El código de cita debe ser numérico.",
                "Error de formato",
                JOptionPane.ERROR_MESSAGE
            );
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                this,
                "Error al buscar cita: " + e.getMessage(),
                "Error SQL",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }//GEN-LAST:event_btnConsultarCitasActionPerformed                                                 

    // Seleccionar registros para editar datos de citas
    private void tableConsultaCitasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableConsultaCitasMouseClicked                                                
        // TODO add your handling code here:
        /**/
    }//GEN-LAST:event_tableConsultaCitasMouseClicked                                               

    private void txtConsultarCitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtConsultarCitasActionPerformed                                                  
        // TODO add your handling code here:
    }//GEN-LAST:event_txtConsultarCitasActionPerformed                                                 

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed                                         
        // TODO add your handling code here:
        int confirmacion = JOptionPane.showConfirmDialog(
        this, 
        "¿Está seguro de que desea salir del sistema?", 
        "Confirmar salida", 
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_jButton3ActionPerformed                                        

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed                                         
        // TODO add your handling code here:
        // Instanciar la ventana principal
        FrmPrincipal menuPrincipal = new FrmPrincipal();
        menuPrincipal.setVisible(true);
        menuPrincipal.setLocationRelativeTo(null); // Centrar en pantalla

        // Cerrar la ventana actual de citas
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed                                        
    
    private void seleccionarHoraEnComboBox(javax.swing.JComboBox<String> combo, String horaTabla) {
        if (horaTabla == null || horaTabla.trim().isEmpty()) {
            return;
        }

        try {
            // Limpiar la cadena si trae segundos ("08:00:00" -> "08:00")
            String horaLimpia = horaTabla.length() > 5 ? horaTabla.substring(0, 5) : horaTabla;

            java.text.SimpleDateFormat parser24 = new java.text.SimpleDateFormat("HH:mm");
            java.text.SimpleDateFormat format12 = new java.text.SimpleDateFormat("hh:mm a", java.util.Locale.US);

            java.util.Date date = parser24.parse(horaLimpia);
            String hora12h = format12.format(date).toUpperCase();

            boolean encontrado = false;
            for (int i = 0; i < combo.getItemCount(); i++) {
                String item = combo.getItemAt(i).toString().trim();
                if (item.equalsIgnoreCase(hora12h) || item.equalsIgnoreCase(horaTabla.trim())) {
                    combo.setSelectedIndex(i);
                    encontrado = true;
                    break;
                }
            }

            if (!encontrado) {
                combo.setSelectedItem(horaTabla.trim());
            }

        } catch (java.text.ParseException e) {
            combo.setSelectedItem(horaTabla.trim());
        }
    }
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
            java.util.logging.Logger.getLogger(FrmCitas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmCitas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmCitas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmCitas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmCitas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables                     
    private javax.swing.JButton btnBuscarEstado;
    private javax.swing.JButton btnConsultarCitas;
    private javax.swing.JButton btnConsultarCitas4;
    private javax.swing.JButton btnConsultarCitas5;
    private javax.swing.JButton btnEliminarCitas;
    private javax.swing.JButton btnGuardarEstado;
    private javax.swing.JButton btnModificarCitas;
    private javax.swing.JButton btnMostrarCitas;
    private javax.swing.JButton btnRegistrarCitas;
    private javax.swing.JComboBox<String> cbElimEstado;
    private javax.swing.JComboBox<String> cbElimHora;
    private javax.swing.JComboBox<String> cbEstado;
    private javax.swing.JComboBox<String> cbEstadoHora;
    private javax.swing.JComboBox<String> cbEstadoNuevo;
    private javax.swing.JComboBox<String> cbHoraCitas;
    private javax.swing.JComboBox<String> cbModEstado;
    private javax.swing.JComboBox<String> cbModHora;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane tabCitasMedicas;
    private javax.swing.JPanel tabConsultar;
    private javax.swing.JPanel tabConsultarDoctores;
    private javax.swing.JPanel tabEliminar;
    private javax.swing.JPanel tabModificar;
    private javax.swing.JPanel tabRegistrar;
    private javax.swing.JTable tableConsultaCitas;
    private javax.swing.JTable tblCitasEstado;
    private javax.swing.JTextField txtCodDoctor;
    private javax.swing.JTextField txtCodPacientes;
    private javax.swing.JTextField txtConsultarCitas;
    private javax.swing.JTextField txtElimCodigoCita;
    private javax.swing.JTextField txtElimCodigoDoctor;
    private javax.swing.JTextField txtElimCodigoPaciente;
    private javax.swing.JTextField txtElimFecha;
    private javax.swing.JTextField txtElimMotivo;
    private javax.swing.JTextField txtEstadoCodigoCita;
    private javax.swing.JTextField txtEstadoCodigoDoctor;
    private javax.swing.JTextField txtEstadoCodigoPaciente;
    private javax.swing.JTextField txtEstadoFecha;
    private javax.swing.JTextField txtEstadoMotivo;
    private javax.swing.JTextField txtFechaCitas;
    private javax.swing.JTextField txtModCodigoCita;
    private javax.swing.JTextField txtModCodigoDoctor;
    private javax.swing.JTextField txtModCodigoPaciente;
    private javax.swing.JTextField txtModFecha;
    private javax.swing.JTextField txtModMotivo;
    private javax.swing.JTextField txtMotivoCitas;
    // End of variables declaration//GEN-END:variables                   
    private boolean esFechaPasada(String fecha) {
        try {
            java.time.LocalDate fechaIngresada = java.time.LocalDate.parse(fecha);
            return fechaIngresada.isBefore(java.time.LocalDate.now());
        } catch (Exception e) {
            return false;
        }
    }
}
