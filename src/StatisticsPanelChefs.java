import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class StatisticsPanelChefs extends JScrollPane {
    private JTable tablaChefs;
    private DefaultTableModel dtmChefs;


    public StatisticsPanelChefs() {
        dtmChefs = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        Object[] columnNames = {"","Cuinant", "Descansant", "Entregant","Temps Descansant", "Temps Cuinant", "Plats Cuinats"};
        Object[][] data = new Object[][] {
                {"Chefs" , 0, 0, 0, 0, 0, 0}
        };
        tablaChefs = new JTable();
        dtmChefs.setDataVector(data, columnNames);
        tablaChefs.setModel(dtmChefs);
        setViewportView(tablaChefs);
    }
}
