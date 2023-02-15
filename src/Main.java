import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        RestaurantController c = new RestaurantController();
        new RestaurantModel();
        new RestaurantView(c);
    }
}
