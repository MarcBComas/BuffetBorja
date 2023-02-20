package Model;

import java.util.ArrayList;

public class RestaurantModel implements Runnable {
    private ArrayList<Chef> llistaChefs;
    private ArrayList<Comensal> llistaComensals;
    private Rellotge rellotge;
    private AreaBuffet[] areaBuffet;
    private Grill[] grills;
    private String[] descripcions = {"Hamburgueses", "Pizzas", "Pastas"};
    private Boolean started;
    private GeneralStatus status;


    public RestaurantModel() {
        this.rellotge = Rellotge.getRellotge();
        this.areaBuffet = new AreaBuffet[3];
        this.grills = new Grill[3];
        status = GeneralStatus.PAUSED;
        this.started = false;
        for (int i = 0; i < 3; i++) {
            this.grills[i] = new Grill(ParametresSimulacio.maxPlatGrill);
            this.areaBuffet[i] = new AreaBuffet(ParametresSimulacio.maxPlatAreaBuffet, this.grills[i], descripcions[i]);
        }
        setChefs();
        setComensals();
    }
    public ArrayList<Chef> getLlistaChefs() {
        return llistaChefs;
    }

    public void setChefs() {
        this.llistaChefs = new ArrayList<>();
        int numChefs = ParametresSimulacio.numChefPerGrill.getValorAleatori();
        for(int i = 0; i < numChefs; i++) {
            llistaChefs.add(new Chef(rellotge, areaBuffet[0], grills[0]));
            llistaChefs.add(new Chef(rellotge, areaBuffet[1], grills[1]));
            llistaChefs.add(new Chef(rellotge, areaBuffet[2], grills[2]));
        }
    }

    public ArrayList<Comensal> getLlistaComensals() {
        return llistaComensals;
    }

    public void setComensals() {
        this.llistaComensals = new ArrayList<>();
        int numComensals = ParametresSimulacio.numComensal.getValorAleatori();
        for (int i = 0; i < numComensals; i++) {
            llistaComensals.add(new Comensal(areaBuffet));
        }
    }


    public Rellotge getRellotge() {
        return rellotge;
    }

    public void setRellotge(Rellotge rellotge) {
        this.rellotge = rellotge;
    }

    public AreaBuffet[] getAreaBuffet() {
        return areaBuffet;
    }

    public void setAreaBuffet() {

    }

    public Grill[] getGrills() {
        return grills;
    }

    public void setGrills(Grill[] grills) {
        this.grills = grills;
    }

    public void setStatus(GeneralStatus status) {
        this.status = status;
    }

    public void stop(){
        rellotge = rellotge.reset();
        for (int i = 0; i < llistaComensals.size(); i++) {
            llistaComensals.get(i).setgStatus(GeneralStatus.STOPPED);
        }
        for (int i = 0; i < llistaChefs.size(); i++) {
            llistaChefs.get(i).setgStatus(GeneralStatus.STOPPED);
        }
        llistaComensals = null;
        new EstadistiquesChefs();
        new EstadistiquesComensals();
        new EstadistiquesBuffets();
        setChefs();
        setComensals();
        started = false;
    }
    public synchronized void pause() throws InterruptedException {
        rellotge.setEstat(GeneralStatus.PAUSED);
        for (int i = 0; i < llistaComensals.size(); i++) {
            llistaComensals.get(i).setgStatus(GeneralStatus.PAUSED);
        }
        for (int i = 0; i < llistaChefs.size(); i++) {
            llistaChefs.get(i).setgStatus(GeneralStatus.PAUSED);
        }
    }

    public synchronized void play() {
        for (int i = 0; i < llistaComensals.size(); i++) {
            llistaComensals.get(i).setgStatus(GeneralStatus.RUNNING);
        }
        for (int i = 0; i < llistaChefs.size(); i++) {
            llistaChefs.get(i).setgStatus(GeneralStatus.RUNNING);
        }
        rellotge.setEstat(GeneralStatus.RUNNING);
        if (!started) {
            for (int i = 0; i < llistaComensals.size(); i++) {
                new Thread(llistaComensals.get(i)).start();
            }
            for (int i = 0; i < llistaChefs.size(); i++) {
                new Thread(llistaChefs.get(i)).start();
            }
            new Thread(rellotge).start();
            started = true;
        }

    }
    public Estadistiques getEstadistiques(){return new Estadistiques(Chef.getEstadistiques(), Comensal.getEstadistiques(), AreaBuffet.getEstadistiques());}

    @Override
    public void run() {
        while (true) {
            switch (status) {
                case RUNNING:
                    play();
                    break;
                case PAUSED:
                    try {
                        pause();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case STOPPED:
                    stop();
                    break;
            }
        }
    }
}
