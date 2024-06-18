/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.Lexema;
import view.NewJFrame;

/**
 *
 * @author T490 U88
 */
public class Tabela {

    private double tempofinal;

    public void meteNaTab(List<Lexema> lista1) {
        DefaultTableModel model2 = (DefaultTableModel) NewJFrame.jTable1.getModel();
        List<Lexema> lista = new ArrayList();

//        lista = OrganizadorDeTexo.lista;
        lista = lista1;
        Object rowData[] = new Object[3];
        for (int a = 0; a < lista.size(); a++) {

            if (lista.get(a) != null) {
                rowData[0] = lista.get(a).getLinha();
                rowData[1] = lista.get(a).getNome();
                rowData[2] = lista.get(a).getDescricao();

                model2.addRow(rowData);

            }
        }
        tempofinal = System.currentTimeMillis() - NewJFrame.tempoInicial;
        NewJFrame.tempo.setText(Double.toString(tempofinal));

    }
}
