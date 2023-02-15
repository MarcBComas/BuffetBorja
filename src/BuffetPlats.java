public class BuffetPlats {
    protected int capacitatMaxima;
    protected int quantitatActual;

    public BuffetPlats(int capacitatMaxima) {
        this.capacitatMaxima = capacitatMaxima;
        this.quantitatActual = 0;
    }

    public Boolean afegirPlat() {
        if (quantitatActual < capacitatMaxima) {
            quantitatActual++;
            return true;
        }
        return false;
    }

    public Boolean retirarPlat() {
        if (quantitatActual > 0) {
            quantitatActual--;
            return true;
        }
        return false;
    }

}
