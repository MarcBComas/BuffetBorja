package View;

import Model.EstadistiquesChefs;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class StatisticsPanelChefs extends JScrollPane {
    private JTable tabla;
    private DefaultTableModel dtm;


    public StatisticsPanelChefs() {
        dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        Object[] columnNames = {"","Cuinant", "Descansant", "Entregant","Temps Descansant", "Temps Cuinant", "Plats Cuinats"};
        Object[][] data = new Object[][] {
                {"Chefs" , 0, 0, 0, 0, 0, 0}
        };
        tabla = new JTable();
        dtm.setDataVector(data, columnNames);
        tabla.setModel(dtm);
        setViewportView(tabla);
    }

    public void setStatistics() {
        dtm.setValueAt(EstadistiquesChefs.chefsPerEstat[0], 0, 1);
        dtm.setValueAt(EstadistiquesChefs.chefsPerEstat[1], 0, 2);
        dtm.setValueAt(EstadistiquesChefs.chefsPerEstat[2], 0, 3);
        dtm.setValueAt(EstadistiquesChefs.tempsDescansant, 0, 4);
        dtm.setValueAt(EstadistiquesChefs.tempsCuinant, 0, 5);
        dtm.setValueAt(EstadistiquesChefs.platsCuinats, 0, 6);
    }
}
