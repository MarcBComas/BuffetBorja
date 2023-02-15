import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class StatisticsPanelComensal extends JScrollPane {
    private JTable tablaComensals;
    private DefaultTableModel dtmComensals;

    public StatisticsPanelComensal() {
        dtmComensals = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        Object[] columnNames = new Object[] {"", "Menjant", "Tertulia", "AgafantPlat", "Temps Menjant", "Temps Tertulia", "Temps Esperant" };
        Object[][] data = new Object[][] {
                {"Comensals" , 0, 0, 0, 0, 0, 0}
        };
        tablaComensals = new JTable();
        dtmComensals.setDataVector(data, columnNames);
        tablaComensals.setModel(dtmComensals);
        setViewportView(tablaComensals);
    }
}
