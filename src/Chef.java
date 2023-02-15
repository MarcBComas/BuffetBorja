import static java.lang.Thread.sleep;

public class Chef implements Runnable{
    private static EstadistiquesChefs estadistiques = new EstadistiquesChefs();
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
        estadistiques.chefsPerEstat[this.status.ordinal()]++;
        this.rellotge = rellotge;
        this.buffet = buffet;
        this.grill = grill;
    }
    public synchronized void cocinar() throws InterruptedException {
        System.out.println("El chef comença a cuinar");
        if(this.grill.afegirPlat()) {
            setStatus(ChefStatus.CUINANT);
            sleep(rellotge.minutsEnMilisegons(ParametresSimulacio.tempsCuinantChef));
            this.nombrePlatsCuinats++;
            this.tempsTotalCuinant += ParametresSimulacio.tempsCuinantChef;
            estadistiques.tempsCuinant += ParametresSimulacio.tempsCuinantChef;
            estadistiques.platsCuinats++;
            tempsNoDescans += ParametresSimulacio.tempsCuinantChef;
            entregarPlat();
        } else {
            System.out.println("El grill està ple");
            this.tempsEspera += ParametresSimulacio.tempsCuinantChef;
            sleep(rellotge.minutsEnMilisegons(ParametresSimulacio.tempsCuinantChef));
            cocinar();
        }

    }

    public synchronized void descansar() throws InterruptedException {
        System.out.println("El chef comença a descansar");
        setStatus(ChefStatus.DESCANSANT);
        sleep(rellotge.minutsEnMilisegons(ParametresSimulacio.tempsDescansChef));
        this.tempsNoDescans = 0;
    }

    public synchronized void entregarPlat(){
        System.out.println("El chef comença a entregar el plat");
        setStatus(ChefStatus.ENTREGANT);
        grill.retirarPlat();
        Boolean entregat = false;
        while (!entregat) {
            System.out.println("El chef intenta entregar el plat");
            entregat = buffet.afegirPlat();
        }
        System.out.println("El chef ha entregat el plat");
    }

    public void setStatus(ChefStatus status) {
        estadistiques.chefsPerEstat[this.status.ordinal()]--;
        this.status = status;
        estadistiques.chefsPerEstat[this.status.ordinal()]++;
    }

    public void setGrill(Grill grill) {
        this.grill = grill;
    }

    public static EstadistiquesChefs getEstadistiques() {
        return estadistiques;
    }

    @Override
    public void run() {
        while(true) {
            if(tempsNoDescans < 30) {
                try {
                    cocinar();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    descansar();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
