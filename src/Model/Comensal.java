package Model;

import java.util.Random;

import static java.lang.Thread.sleep;

public class Comensal implements Runnable{
    private static EstadistiquesComensals estadistiques = new EstadistiquesComensals();
    private int platsMenjats;
    private int tempsMenjant;
    private int tempsTertulia;
    private int tempsEspera;
    private ComensalStatus status;
    private Rellotge rellotge;
    private AreaBuffet[] buffets;
    private GeneralStatus gStatus;

    public Comensal(AreaBuffet[] buffets) {
        this.platsMenjats = 0;
        this.tempsMenjant = 0;
        this.tempsTertulia = 0;
        this.tempsEspera = 0;
        this.status = ComensalStatus.AGAFANTPLAT;
        estadistiques.comensalsPerEstat[this.status.ordinal()]++;
        this.rellotge = Rellotge.getRellotge();
        this.buffets = buffets;
    }

    public static EstadistiquesComensals getEstadistiques() {
        return estadistiques;
    }

    public static void resetEstadistiques() {
        estadistiques = new EstadistiquesComensals();
    }

    public synchronized void menjar() throws InterruptedException {
        setStatus(ComensalStatus.MENJANT);
        int tempsMenjant = ParametresSimulacio.tempsConsumir.getValorAleatori();
        sleep(rellotge.minutsEnMilisegons(tempsMenjant));
        this.tempsMenjant += tempsMenjant;
        estadistiques.tempsMenjant += tempsMenjant;
        this.platsMenjats++;
    }

    public synchronized void tertulia() throws InterruptedException {
        setStatus(ComensalStatus.XERRANT);
        int tempsTertulia = ParametresSimulacio.tempsTertulia.getValorAleatori();
        sleep(rellotge.minutsEnMilisegons(tempsTertulia));
        this.tempsTertulia += tempsTertulia;
        estadistiques.tempsTertulia += tempsTertulia;
    }

    public synchronized void agafarPlat() {
        setStatus(ComensalStatus.AGAFANTPLAT);
        Boolean agafat = false;
        int areaBuffet = getBuffetRandom();
        int iniciEspera = rellotge.getMinutoActual();
        while(!agafat) {
            agafat = this.buffets[areaBuffet].retirarPlat();
            if(!agafat) {
                areaBuffet = getBuffetRandom();
            }
        }
        this.tempsEspera += rellotge.getIntervalEnMinuts(iniciEspera);
        estadistiques.tempsEsperant += rellotge.getIntervalEnMinuts(iniciEspera);
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

    public void setgStatus(GeneralStatus gStatus) {
        this.gStatus = gStatus;
    }

    @Override
    public void run() {
        while(!(gStatus == GeneralStatus.STOPPED)) {

            while(gStatus == GeneralStatus.RUNNING) {
                agafarPlat();
                try {
                    menjar();
                    tertulia();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
