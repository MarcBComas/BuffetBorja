import java.util.ArrayList;
import java.util.Random;

public class RestaurantModel {
    private ArrayList<Chef> llistaChefs;
    private ArrayList<Comensal> llistaComensals;
    private Rellotge rellotge;
    private AreaBuffet[] areaBuffet;
    private Grill[] grills;


    public RestaurantModel() {
        this.llistaComensals = new ArrayList<>();
        int numComensals = ParametresSimulacio.numComensal.getValorAleatori();
        for (int i = 0; i < numComensals; i++) {
            llistaComensals.add(new Comensal());
        }
        this.rellotge = Rellotge.getRellotge();
        this.areaBuffet = new AreaBuffet[3];
        this.grills = new Grill[3];
        for (int i = 0; i < 3; i++) {
            this.grills[i] = new Grill(ParametresSimulacio.maxPlatGrill);
            this.areaBuffet[i] = new AreaBuffet(ParametresSimulacio.maxPlatAreaBuffet, this.grills[i]);
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

    }
    public void stop(){

    }
    public void pause(){

    }

    public int getBuffetRandom(){
        Random r = new Random();
        return r.nextInt(3);
    }

    public Estadistiques getEstadistiques(){return new Estadistiques();}
}
