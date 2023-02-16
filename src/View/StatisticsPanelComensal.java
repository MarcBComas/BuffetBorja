package View;

import Model.EstadistiquesComensals;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class StatisticsPanelComensal extends JScrollPane {
    private JTable tabla;
    private DefaultTableModel dtm;

    public StatisticsPanelComensal() {
        dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        Object[] columnNames = new Object[] {"", "Menjant", "Tertulia", "AgafantPlat", "Temps Menjant", "Temps Tertulia", "Temps Esperant" };
        Object[][] data = new Object[][] {
                {"Comensals" , 0, 0, 0, 0, 0, 0}
        };
        tabla = new JTable();
        dtm.setDataVector(data, columnNames);
        tabla.setModel(dtm);
        setViewportView(tabla);
    }

    public void setStatistics() {
        dtm.setValueAt(EstadistiquesComensals.comensalsPerEstat[0], 0, 1);
        dtm.setValueAt(EstadistiquesComensals.comensalsPerEstat[1], 0, 2);
        dtm.setValueAt(EstadistiquesComensals.comensalsPerEstat[2], 0, 3);
        dtm.setValueAt(EstadistiquesComensals.tempsMenjant, 0, 4);
        dtm.setValueAt(EstadistiquesComensals.tempsTertulia, 0, 5);
        dtm.setValueAt(EstadistiquesComensals.tempsEsperant, 0, 6);
    }
}
