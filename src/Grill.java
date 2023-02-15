public class Grill extends BuffetPlats{
    private boolean enServei;

    public Grill(int capacitatMaxima) {
        super(capacitatMaxima);
        this.enServei = true;
    }

    public synchronized Boolean afegirPlat() {
        if (enServei) {
            return super.afegirPlat();
        } else {
            return false;
        }
    }

    public void posarEnServei() {
        this.enServei = true;
    }

    public void treureDeServei() {
        this.enServei = false;
    }

}
