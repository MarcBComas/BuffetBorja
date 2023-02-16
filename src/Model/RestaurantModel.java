package Model;

import java.util.ArrayList;

public class RestaurantModel implements Runnable {
    private ArrayList<Chef> llistaChefs;
    private ArrayList<Comensal> llistaComensals;
    private Rellotge rellotge;
    private AreaBuffet[] areaBuffet;
    private Grill[] grills;
    private String[] descripcions = {"Hamburgueses", "Pizzas", "Pastas"};


    public RestaurantModel() {
        this.rellotge = Rellotge.getRellotge();
        this.areaBuffet = new AreaBuffet[3];
        this.grills = new Grill[3];
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


    public void stop(){
        rellotge.reset();
        setChefs();
        setComensals();
        new EstadistiquesChefs();
        new EstadistiquesComensals();
        new EstadistiquesBuffets();
    }
    public synchronized void pause() throws InterruptedException {
        for (int i = 0; i < llistaComensals.size(); i++) {
                llistaComensals.get(i).setgStatus(GeneralStatus.PAUSED);
        }
        for (int i = 0; i < llistaChefs.size(); i++) {
            llistaChefs.get(i).setgStatus(GeneralStatus.PAUSED);
        }
    }

    public synchronized void play() throws InterruptedException {
        for (int i = 0; i < llistaComensals.size(); i++) {
            llistaComensals.get(i).setgStatus(GeneralStatus.RUNNING);
            new Thread(llistaComensals.get(i)).start();
        }
        for (int i = 0; i < llistaChefs.size(); i++) {
            llistaChefs.get(i).setgStatus(GeneralStatus.RUNNING);
            new Thread(llistaChefs.get(i)).start();
        }
        new Thread(rellotge).start();
    }
    public Estadistiques getEstadistiques(){return new Estadistiques(Chef.getEstadistiques(), Comensal.getEstadistiques(), AreaBuffet.getEstadistiques());}

    @Override
    public void run() {

    }
}
