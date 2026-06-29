/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilerias;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
/**
 *
 * @author luisf
 */

public class EditarButtonRenderer extends JButton implements TableCellRenderer {
 
    public EditarButtonRenderer() {
        setOpaque(true);
        setFocusPainted(false);
        setContentAreaFilled(true);
    }
 
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        
        Object tipoValor = table.getValueAt(row, 2);
        String tipoValorStr = (tipoValor != null) ? tipoValor.toString() : "";
        
        boolean esEditable = !tipoValorStr.equalsIgnoreCase("Positivo/Negativo");
        
        if (esEditable) {
            setText("Editar");
            setEnabled(true);
        } else {
            setText("No editable");
            setEnabled(false);
        }
        
        setFont(new Font("Segoe UI", Font.BOLD, 11));
        setBorder(javax.swing.BorderFactory.createRaisedBevelBorder());
 
        return this;
    }
}