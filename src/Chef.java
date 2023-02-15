import static java.lang.Thread.sleep;

public class Chef implements Runnable{
    private static EstadistiquesChefs estadistiques;
    private int tempsTotalCuinant;
    private int tempsNoDescans;
    private int horariIniciDescans;
    private int tempsTotalDescans;
    private int tempsEspera;
    private int nombrePlatsCuinats;
    private ChefStatus status;
    private Rellotge rellotge;
    private AreaBuffet buffet;
    private Grill grill;

    public Chef(Rellotge rellotge, AreaBuffet buffet, Grill grill) {
        this.tempsTotalCuinant = 0;
        this.tempsNoDescans = 0;
        this.horariIniciDescans = 0;
        this.tempsTotalDescans = 0;
        this.tempsEspera = 0;
        this.nombrePlatsCuinats = 0;
        this.status = ChefStatus.DESCANSANT;
        this.rellotge = rellotge;
        this.buffet = buffet;
        this.grill = grill;
    }
    public synchronized void cocinar() throws InterruptedException {
        if(this.grill.afegirPlat()) {
            setStatus(ChefStatus.CUINANT);
            sleep(ParametresSimulacio.tempsCuinantChef);
            this.nombrePlatsCuinats++;
            this.tempsTotalCuinant += ParametresSimulacio.tempsCuinantChef;
            estadistiques.tempsCuinant += ParametresSimulacio.tempsCuinantChef;
            estadistiques.platsCuinats++;
            entregarPlat();
        }

    }

    public synchronized void descansar() throws InterruptedException {
        setStatus(ChefStatus.DESCANSANT);

    }

    public synchronized void entregarPlat() throws InterruptedException {
        setStatus(ChefStatus.ENTREGANT);
        Boolean entregat = false;
        while (!entregat) {
            entregat = buffet.afegirPlat();
        }
    }

    public void setStatus(ChefStatus status) {
        this.status = status;
        estadistiques.chefsPerEstat[status.ordinal() + 1]++;
    }

    public void setGrill(Grill grill) {
        this.grill = grill;
    }

    @Override
    public void run() {
        while(true) {

        }
    }
}
