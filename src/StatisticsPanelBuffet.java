import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class StatisticsPanelBuffet extends JScrollPane {
    private JTable tablaAreaBuffets;
    private DefaultTableModel dtmAreaBuffets;

    public StatisticsPanelBuffet() {

        dtmAreaBuffets = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        Object[] columnNames = new Object[] {"","Plats en Buffet", "Plats en Cola"};
        Object[][] data = new Object[][] {
                {"Buffet Hamburgueses" , 0, 0},
                {"Buffet Pizzas" , 0, 0},
                {"Buffet Pastas" , 0, 0},
        };
        tablaAreaBuffets = new JTable();
        dtmAreaBuffets.setDataVector(data, columnNames);
        tablaAreaBuffets.setModel(dtmAreaBuffets);
        setViewportView(tablaAreaBuffets);
    }
}
