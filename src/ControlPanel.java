import javax.swing.JButton;
import javax.swing.JPanel;

public class ControlPanel extends JPanel{
    public JButton bPlay = new JButton("Play");
    public JButton bPause = new JButton("Pause");
    public JButton bStop = new JButton("Stop");
    RestaurantView view;

    public ControlPanel(RestaurantView view) {
        this.view = view;
        bPlay.addActionListener(e -> view.play());
        bPause.addActionListener(e -> {
            try {
                view.pause();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });
        bStop.addActionListener(e -> view.stop());
        this.add(bPlay);
        this.add(bPause);
        this.add(bStop);
    }
}
