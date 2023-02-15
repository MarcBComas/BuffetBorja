import java.util.Random;

public class Rango {
    public int min;
    public int max;

    public Rango(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int getValorAleatori() {
        Random r = new Random();
        return r.nextInt(max - min) + min;
    }
}
