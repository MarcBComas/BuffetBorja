import java.util.Random;

public class ParametresSimulacio {
   public static int maxPlatAreaBuffet = 4;
   public static int maxPlatGrill = 2;
   public static int limitPlatsEnCoa = 3;
   public static Rango tempsConsumir = new Rango(5, 10);
   public static Rango tempsCuinat = new Rango(5, 10);
   public static Rango tempsTertulia = new Rango(5, 10);
   public static int tempsCuinantChef = 5;
   public static Rango numComensal = new Rango(12,36);
   public static Rango numChefPerGrill = new Rango(1,3);

    public ParametresSimulacio() {
        maxPlatAreaBuffet = 4;
        maxPlatGrill = 2;
        limitPlatsEnCoa = 3;
        tempsConsumir = new Rango(5, 10);
        tempsCuinat = new Rango(5, 10);
        tempsTertulia = new Rango(5, 10);
        tempsCuinantChef = 5;
        numComensal = new Rango(12, 36);
        numChefPerGrill = new Rango(1, 3);
    }

    public int getValorAleatori(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }


}
