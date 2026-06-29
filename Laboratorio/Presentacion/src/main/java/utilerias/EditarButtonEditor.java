/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilerias;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 *
 * @author luisf
 */

public class EditarButtonEditor extends DefaultCellEditor {

    protected JButton button;
    private String label;
    private boolean isPushed;
    private Runnable accion;
    private JTable tabla;
    private int filaActiva;

    public EditarButtonEditor(JCheckBox checkBox, Runnable accion) {
        super(checkBox);
        this.accion = accion;
        button = new JButton();
        button.setOpaque(true);
        button.setFocusPainted(false);
        button.setContentAreaFilled(true);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {

        this.tabla = table;
        this.filaActiva = row;

        Object tipoValor = table.getValueAt(row, 2);
        String tipoValorStr = (tipoValor != null) ? tipoValor.toString() : "";

        boolean esEditable = !tipoValorStr.equalsIgnoreCase("Positivo/Negativo");

        if (esEditable) {
            label = "Editar";
            button.setText(label);
            button.setEnabled(true);
        } else {
            label = "No editable";
            button.setText(label);
            button.setEnabled(false);
        }

        button.setBorder(javax.swing.BorderFactory.createRaisedBevelBorder());

        isPushed = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
            Object tipoValor = tabla.getValueAt(filaActiva, 2);
            String tipoValorStr = (tipoValor != null) ? tipoValor.toString() : "";
            boolean esEditable = !tipoValorStr.equalsIgnoreCase("Positivo/Negativo");

            if (esEditable) {
                if (tabla != null) {
                    tabla.putClientProperty("filaSeleccionada", this.filaActiva);
                }

                if (accion != null) {
                    accion.run();
                }
            }
        }
        isPushed = false;
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }
}
