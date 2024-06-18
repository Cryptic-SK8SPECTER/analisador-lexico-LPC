/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import view.NewJFrame;

/**
 *
 * @author T490 U88
 */
public class PaintLine extends DefaultTableCellRenderer {

    public PaintLine() {
    }
    public final DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component renderer = DEFAULT_RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        Color background = Color.white;

        String objecto = NewJFrame.jTable1.getValueAt(row, 2).toString();

        try {
            String descricao = objecto;
            if (descricao.equals("Erro")) {
                background = Color.red;
            } else if (descricao.equals("Literal")) {
                background = Color.PINK;
            }
        } catch (Exception e) {
        }

        renderer.setForeground(Color.BLACK);
        renderer.setBackground(background);
        return renderer;
    }

}
