import java.util.ArrayList;
import java.util.Random;

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
        this.llistaComensals = new ArrayList<>();
        int numComensals = ParametresSimulacio.numComensal.getValorAleatori();
        for (int i = 0; i < numComensals; i++) {
            llistaComensals.add(new Comensal(areaBuffet));
        }
        for (int i = 0; i < 3; i++) {
            this.grills[i] = new Grill(ParametresSimulacio.maxPlatGrill);
            this.areaBuffet[i] = new AreaBuffet(ParametresSimulacio.maxPlatAreaBuffet, this.grills[i], descripcions[i]);
        }
        this.llistaChefs = new ArrayList<>();
        int numChefs = ParametresSimulacio.numChefPerGrill.getValorAleatori();
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < numChefs; j++) {
                llistaChefs.add(new Chef(rellotge, areaBuffet[i], grills[i]));
            }
        }
    }
    public ArrayList<Chef> getLlistaChefs() {
        return llistaChefs;
    }

    public void setLlistaChefs(ArrayList<Chef> llistaChefs) {
        this.llistaChefs = llistaChefs;
    }

    public ArrayList<Comensal> getLlistaComensals() {
        return llistaComensals;
    }

    public void setLlistaComensals(ArrayList<Comensal> llistaComensals) {
        this.llistaComensals = llistaComensals;
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

    public void setAreaBuffet(AreaBuffet[] areaBuffet) {
        this.areaBuffet = areaBuffet;
    }

    public Grill[] getGrills() {
        return grills;
    }

    public void setGrills(Grill[] grills) {
        this.grills = grills;
    }

    public void play(){
        this.run();
        notifyAll();
    }
    public void stop(){

    }
    public synchronized void pause() throws InterruptedException {
        for (int i = 0; i < llistaComensals.size(); i++) {
            llistaComensals.get(i).wait();
        }
        for (int i = 0; i < llistaChefs.size(); i++) {
            llistaChefs.get(i).wait();
        }
    }

    public Estadistiques getEstadistiques(){return new Estadistiques(Chef.getEstadistiques(), Comensal.getEstadistiques(), AreaBuffet.getEstadistiques());}

    @Override
    public void run() {
        for (int i = 0; i < llistaComensals.size(); i++) {
            new Thread(llistaComensals.get(i)).start();
        }
        for (int i = 0; i < llistaChefs.size(); i++) {
            new Thread(llistaChefs.get(i)).start();
        }
        new Thread(rellotge).start();
    }
}
