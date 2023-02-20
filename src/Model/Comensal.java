package Model;

import java.util.Random;

import static java.lang.Thread.sleep;

public class Comensal implements Runnable{
    private volatile static EstadistiquesComensals estadistiques = new EstadistiquesComensals();
    private int platsMenjats;
    private int tempsMenjant;
    private int tempsTertulia;
    private int tempsEspera;
    private ComensalStatus status;
    private Rellotge rellotge;
    private AreaBuffet[] buffets;
    private volatile GeneralStatus gStatus;

    public Comensal(AreaBuffet[] buffets) {
        this.platsMenjats = 0;
        this.tempsMenjant = 0;
        this.tempsTertulia = 0;
        this.tempsEspera = 0;
        this.status = ComensalStatus.AGAFANTPLAT;
        synchronized (estadistiques) {
            estadistiques.comensalsPerEstat[this.status.ordinal()]++;
        }
        this.rellotge = Rellotge.getRellotge();
        this.buffets = buffets;
    }

    public static EstadistiquesComensals getEstadistiques() {
        return estadistiques;
    }

    public static void resetEstadistiques() {
        estadistiques = new EstadistiquesComensals();
    }

    public void menjar() throws InterruptedException {
        setStatus(ComensalStatus.MENJANT);
        int tempsMenjant = ParametresSimulacio.tempsConsumir.getValorAleatori();
        sleep(rellotge.minutsEnMilisegons(tempsMenjant));
        this.tempsMenjant += tempsMenjant;
        synchronized (estadistiques) {
            estadistiques.tempsMenjant += tempsMenjant;
        }
        this.platsMenjats++;
    }

    public void tertulia() throws InterruptedException {
        setStatus(ComensalStatus.XERRANT);
        int tempsTertulia = ParametresSimulacio.tempsTertulia.getValorAleatori();
        sleep(rellotge.minutsEnMilisegons(tempsTertulia));
        this.tempsTertulia += tempsTertulia;
        synchronized (estadistiques) {
            estadistiques.tempsTertulia += tempsTertulia;
        }
    }

    public void agafarPlat() throws InterruptedException {
        setStatus(ComensalStatus.AGAFANTPLAT);
        Boolean agafat = false;
        int areaBuffet = getBuffetRandom();
        int iniciEspera = rellotge.getMinutoActual();
        while(!agafat) {
            sleep(2000);
            agafat = this.buffets[areaBuffet].retirarPlat();
            if(!agafat) {
                areaBuffet = getBuffetRandom();
            }
        }
        this.tempsEspera += rellotge.getIntervalEnMinuts(iniciEspera);
        synchronized (estadistiques) {
            estadistiques.tempsEsperant += rellotge.getIntervalEnMinuts(iniciEspera);
        }
    }

    public void setStatus(ComensalStatus status) {
        synchronized (estadistiques) {
            estadistiques.comensalsPerEstat[this.status.ordinal()]--;
            estadistiques.comensalsPerEstat[status.ordinal()]++;
        }
        this.status = status;
    }

    public int getBuffetRandom(){
        Random r = new Random();
        return r.nextInt(3);
    }

    public void setgStatus(GeneralStatus status) {
        this.gStatus = status;
    }

    @Override
    public void run() {
        while(!(gStatus == GeneralStatus.STOPPED)) {
            while(gStatus == GeneralStatus.PAUSED) {
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            while(gStatus == GeneralStatus.RUNNING) {
                try {
                    agafarPlat();
                    menjar();
                    tertulia();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
