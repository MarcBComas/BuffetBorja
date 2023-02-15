import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class StatisticsPanelBuffet extends JScrollPane {
    private JTable tabla;
    private DefaultTableModel dtm;

    public StatisticsPanelBuffet() {

        dtm = new DefaultTableModel() {
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
        tabla = new JTable();
        dtm.setDataVector(data, columnNames);
        tabla.setModel(dtm);
        setViewportView(tabla);
    }

    public void setStatistics() {
        for(int i=0; i<EstadistiquesBuffets.platsPerAreaBuffet.length; i++) {
            dtm.setValueAt(EstadistiquesBuffets.platsPerAreaBuffet[i], i, 1);
        }
        for(int i=0; i<EstadistiquesBuffets.platsEnColaPerAreaBuffet.length; i++) {
            dtm.setValueAt(EstadistiquesBuffets.platsEnColaPerAreaBuffet[i], i, 2);
        }
    }
}
