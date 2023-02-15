import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        RestaurantModel m = new RestaurantModel();
        RestaurantController c = new RestaurantController();
        RestaurantView v = new RestaurantView(c);
        c.setModel(m);
        c.setView(v);
        new Thread(m).start();
        new Thread(v).start();

    }
}
