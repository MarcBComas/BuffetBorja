package View;

import Controller.RestaurantController;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RestaurantView extends JFrame implements ActionListener, Runnable {
    private RestaurantController controller;
    private final Viewer viewer = new Viewer();
    private final ControlPanel controlPanel = new ControlPanel(this);
    private final StatisticsPanelChefs panelChefs = new StatisticsPanelChefs();
    private final StatisticsPanelComensal panelComensals = new StatisticsPanelComensal();
    private final StatisticsPanelBuffet panelBuffet = new StatisticsPanelBuffet();

    public RestaurantView(RestaurantController controller) {
        this.controller = controller;
        this.setName("Buffet");
        this.setVisible(true);
        this.setSize(new Dimension(1800, 700));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.weighty = 1.0;
        add(this.panelBuffet, c);
        c.gridy = 1;
        add(this.panelChefs, c);
        c.gridy = 2;
        add(this.panelComensals, c);
        c.gridy = 3;
        add(controlPanel, c);
        c.gridx = 1;
        c.gridy = 0;
        add(viewer,c);
    }

    public void refresh() {
        this.panelBuffet.setStatistics();
        this.panelChefs.setStatistics();
        this.panelComensals.setStatistics();
    }

    public void play() throws InterruptedException {
        this.controller.play();
    }

    public void pause() throws InterruptedException {
        this.controller.pause();
    }

    public void stop() {
        this.controller.stop();
    }

    @Override
    public void run() {
        while (true) {
            refresh();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
