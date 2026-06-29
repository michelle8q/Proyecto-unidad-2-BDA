/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package analisis;

import dto.AnalisisDTO;
import interfaces.IAnalisisNegocio;
import itson.org.negocio.AnalisisNegocio;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import utilerias.ButtonEditor;
import utilerias.ButtonRenderer;
import javax.swing.Timer;

/**
 * Ventana (JFrame) que representa el catálogo de Análisis Clínicos. Muestra una
 * tabla con los análisis registrados, permite buscar por coincidencia y cambiar
 * el estado (activar/desactivar) de cada registro.
 *
 * @author cinca luisf
 */
public class FrmCatalogoAnalisis extends javax.swing.JFrame {

    /**
     * Interfaz de la capa de negocio para las operaciones de análisis.
     */
    private final IAnalisisNegocio analisisNegocio;
    /**
     * Lista de los análisis actualmente cargados y mostrados en la tabla.
     */
    private List<AnalisisDTO> listaAnalisis;

    /**
     * Constructor de la clase FrmCatalogoAnalisis. Inicializa los componentes
     * de la interfaz, instancia la capa de negocio, llena la tabla con los
     * datos iniciales y configura los listeners y el temporizador.
     */
    public FrmCatalogoAnalisis() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.analisisNegocio = new AnalisisNegocio();
        llenarTabla();
        configurarBotonTabla();
        configurarBuscador();
        iniciarTemporizadorActualizacion();

    }

    /**
     * Configura la columna de acciones de la tabla (columna 3) para que
     * renderice y funcione como un botón clickeable. Asocia la acción de
     * cambiar estado lógico al botón de la tabla.
     */
    private void configurarBotonTabla() {
        Runnable accionEstado = () -> cambiarEstadoRegistro();

        jTable1.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());
        jTable1.getColumnModel().getColumn(3).setCellEditor(new ButtonEditor(new JCheckBox(), accionEstado));
    }

    /**
     * Configura los eventos del buscador. Asigna la acción de búsqueda al botón
     * "Buscar" y al evento de presionar la tecla 'Enter' dentro del campo de
     * texto de búsqueda.
     */
    private void configurarBuscador() {
        jButton1.addActionListener(e -> realizarBusqueda());

        txtBuscador.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    realizarBusqueda();
                }
            }
        });
    }

    /**
     * Ejecuta la lógica de búsqueda basándose en el texto introducido en el
     * buscador. Si el texto está vacío, recarga toda la tabla. Si hay texto,
     * consulta la capa de negocio y actualiza la tabla con los resultados
     * obtenidos.
     */
    private void realizarBusqueda() {
        String textoBusqueda = txtBuscador.getText().trim();

        if (textoBusqueda.isEmpty()) {
            llenarTabla();
            return;
        }

        try {
            this.listaAnalisis = analisisNegocio.buscarAnalisisPorParametro(textoBusqueda);

            DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
            modelo.setRowCount(0);

            if (this.listaAnalisis.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "No se encontraron análisis con el criterio: " + textoBusqueda,
                        "Búsqueda sin resultados",
                        JOptionPane.INFORMATION_MESSAGE);
            }

            for (AnalisisDTO dto : this.listaAnalisis) {
                Object[] fila = new Object[4];
                fila[0] = dto.getNombre();
                fila[1] = dto.getNotaDescriptiva();
                fila[2] = (dto.getMuestra() != null) ? dto.getMuestra().getTipo() : "Sin muestra";
                fila[3] = dto.isActivo() ? "Desactivar" : "Activar";
                modelo.addRow(fila);
            }

            jTable1.repaint();
            jTable1.revalidate();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error en la búsqueda: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Identifica el análisis seleccionado en la tabla y cambia su estado lógico
     * (de activo a inactivo y viceversa) a través de la capa de negocio, previa
     * confirmación del usuario.
     */
    private void cambiarEstadoRegistro() {
        Object propiedadFila = jTable1.getClientProperty("filaSeleccionada");

        if (propiedadFila != null) {
            int fila = (int) propiedadFila;

            if (fila >= 0 && fila < this.listaAnalisis.size()) {
                AnalisisDTO analisisSeleccionado = this.listaAnalisis.get(fila);

                String accion = analisisSeleccionado.isActivo() ? "desactivar" : "activar";

                int confirmacion = JOptionPane.showConfirmDialog(this,
                        "¿Deseas " + accion + " el análisis '" + analisisSeleccionado.getNombre() + "'?",
                        "Confirmar " + accion,
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);

                if (confirmacion == JOptionPane.YES_OPTION) {
                    try {
                        analisisNegocio.cambiarEstadoAnalisis(analisisSeleccionado.getId());
                        JOptionPane.showMessageDialog(this, "Estado actualizado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                        javax.swing.SwingUtilities.invokeLater(() -> {
                            // Mantener la búsqueda actual si hay una
                            String textoBusqueda = txtBuscador.getText().trim();
                            if (textoBusqueda.isEmpty()) {
                                llenarTabla();
                            } else {
                                realizarBusqueda();
                            }
                        });

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Error al actualizar el estado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }

    /**
     * Inicia un temporizador que actualiza el contenido de la tabla
     * automáticamente cada 30 segundos, siempre y cuando no haya un filtro de
     * búsqueda activo.
     */
    private void iniciarTemporizadorActualizacion() {
        Timer timer = new Timer(30000, e -> {
            String textoBusqueda = txtBuscador.getText().trim();
            if (textoBusqueda.isEmpty()) {
                llenarTabla();
            }
            System.out.println("Tabla actualizada automáticamente...");
        });
        timer.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMenuLateral1 = new utilerias.pnlMenuLateral();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtBuscador = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnRegistrar = new javax.swing.JButton();
        pnlMenuLateral2 = new utilerias.pnlMenuLateral();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1153, 715));

        jPanel1.setBackground(new java.awt.Color(229, 229, 229));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Buscar analisis:");

        txtBuscador.setText("");

        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nombre", "Descripcion", "Tipo de muestra", "Accion"
            }
        ));
        jTable1.setRowHeight(35);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 828, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscador)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)
                        .addGap(31, 31, 31))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Corbel", 1, 24)); // NOI18N
        jLabel1.setText("Catalogo de analisis");

        btnRegistrar.setBackground(new java.awt.Color(166, 217, 243));
        btnRegistrar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnRegistrar.setText("+ Registrar nuevo ");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(pnlMenuLateral2, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(684, 684, 684)
                        .addComponent(btnRegistrar))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRegistrar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(94, 94, 94))
            .addComponent(pnlMenuLateral2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
 /**
     * Evento desencadenado al presionar el botón de registrar nuevo análisis.
     * Cierra la ventana actual y abre el formulario de registro
     * (FrmRegistroAnalisis).
     *
     * @param evt Evento de acción de la interfaz.
     */
    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        FrmRegistroAnalisis pantallaRegistro = new FrmRegistroAnalisis(this.analisisNegocio);

        pantallaRegistro.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);

        pantallaRegistro.setLocationRelativeTo(this);

        pantallaRegistro.setVisible(true);

        dispose();
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed
    /**
     * Consulta todos los análisis a través de la capa de negocio y llena el
     * modelo de la tabla visual para mostrarlos al usuario.
     */
    private void llenarTabla() {
        this.listaAnalisis = analisisNegocio.obtenerAnalisisParaTabla();

        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        modelo.setRowCount(0);

        for (AnalisisDTO dto : this.listaAnalisis) {

            Object[] fila = new Object[4];

            fila[0] = dto.getNombre();
            fila[1] = dto.getNotaDescriptiva();
            fila[2] = (dto.getMuestra() != null) ? dto.getMuestra().getTipo() : "Sin muestra";

            fila[3] = dto.isActivo() ? "Desactivar" : "Activar";

            modelo.addRow(fila);
        }
        jTable1.repaint();
        jTable1.revalidate();

        txtBuscador.setText("");

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private utilerias.pnlMenuLateral pnlMenuLateral1;
    private utilerias.pnlMenuLateral pnlMenuLateral2;
    private javax.swing.JTextField txtBuscador;
    // End of variables declaration//GEN-END:variables
}
