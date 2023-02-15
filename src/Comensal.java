import static java.lang.Thread.sleep;

public class Comensal implements Runnable{
    private static EstadistiquesComensals estadistiques;
    private int platsMenjats;
    private int tempsMenjant;
    private int tempsTertulia;
    private int tempsEspera;
    private ComensalStatus status;
    private Rellotge rellotge;

    public static EstadistiquesComensals getEstadistiques() {
        return estadistiques;
    }

    public static void resetEstadistiques() {
        estadistiques = new EstadistiquesComensals();
    }

    public void menjar() throws InterruptedException {
        this.status = status.MENJANT;
        estadistiques.comensalsPerEstat[0]++;
        int tempsMenjant = ParametresSimulacio.tempsConsumir.getValorAleatori();
        sleep(tempsMenjant);
        this.tempsMenjant += tempsMenjant;
        estadistiques.tempsMenjant += tempsMenjant;
        this.platsMenjats++;
    }

    public void tertulia() throws InterruptedException {
        this.status = status.XERRANT;
        estadistiques.comensalsPerEstat[1]++;
        int tempsTertulia = ParametresSimulacio.tempsTertulia.getValorAleatori();
        sleep(tempsTertulia);
        this.tempsTertulia += tempsTertulia;
        estadistiques.tempsTertulia += tempsTertulia;
    }

    public void agafarPlat() {

    }

    @Override
    public void run() {

    }
}
