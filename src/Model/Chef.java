package Model;

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
    private static GeneralStatus gStatus;

    public Chef(Rellotge rellotge, AreaBuffet buffet, Grill grill) {
        this.tempsTotalCuinant = 0;
        this.tempsNoDescans = 0;
        this.horariIniciDescans = 0;
        this.tempsTotalDescans = 0;
        this.tempsEspera = 0;
        this.nombrePlatsCuinats = 0;
        this.status = ChefStatus.DESCANSANT;
        synchronized (estadistiques) {
            estadistiques.chefsPerEstat[this.status.ordinal()]++;
        }
        this.rellotge = rellotge;
        this.buffet = buffet;
        this.grill = grill;
    }
    public synchronized void cocinar() throws InterruptedException {
        if(this.grill.afegirPlat()) {
            setStatus(ChefStatus.CUINANT);
            sleep(rellotge.minutsEnMilisegons(ParametresSimulacio.tempsCuinantChef));
            this.nombrePlatsCuinats++;
            this.tempsTotalCuinant += ParametresSimulacio.tempsCuinantChef;
            synchronized (estadistiques) {
                estadistiques.tempsCuinant += ParametresSimulacio.tempsCuinantChef;
                estadistiques.platsCuinats++;
            }
            tempsNoDescans += ParametresSimulacio.tempsCuinantChef;
            grill.retirarPlat();
            entregarPlat();
        } else {
            this.tempsEspera += ParametresSimulacio.tempsCuinantChef;
            sleep(rellotge.minutsEnMilisegons(ParametresSimulacio.tempsCuinantChef));
            cocinar();
        }
    }

    public synchronized void descansar() throws InterruptedException {
        setStatus(ChefStatus.DESCANSANT);
        sleep(rellotge.minutsEnMilisegons(ParametresSimulacio.tempsDescansChef));
        synchronized (estadistiques) {
            estadistiques.tempsDescansant += ParametresSimulacio.tempsDescansChef;
        }
        this.tempsNoDescans = 0;
    }

    public synchronized void entregarPlat() throws InterruptedException {
        setStatus(ChefStatus.ENTREGANT);
        Boolean entregat = false;
        while (!entregat) {
            entregat = buffet.afegirPlat();
        }
    }

    public void setStatus(ChefStatus status) {
        synchronized (estadistiques) {
            estadistiques.chefsPerEstat[this.status.ordinal()]--;
            estadistiques.chefsPerEstat[status.ordinal()]++;
        }
        this.status = status;
    }

    public void setGrill(Grill grill) {
        this.grill = grill;
    }

    public static EstadistiquesChefs getEstadistiques() {
        return estadistiques;
    }

    public void pause() throws InterruptedException {
        wait();
    }

    public static void setgStatus(GeneralStatus status) {
        gStatus = status;
    }

    @Override
    public void run() {
        while(!(gStatus == GeneralStatus.STOPPED)) {
            while (gStatus == GeneralStatus.RUNNING) {
                try {
                    if (this.tempsNoDescans < 30) {
                        cocinar();

                    } else {
                        descansar();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
