public class AreaBuffet extends BuffetPlats{
    private static EstadistiquesBuffets estadistiques;
    private String descripcio;
    private ColaPlatsCuinats cola;
    private Grill grill;

    public AreaBuffet(int capacitatMaxima, Grill grill) {
        super(capacitatMaxima);
        this.cola = new ColaPlatsCuinats(this);
        this.grill = grill;
    }

    @Override
    public synchronized Boolean afegirPlat() {
        if (super.afegirPlat()) {
            switch (this.descripcio) {
                case "Hamburgueses":
                    estadistiques.platsPerAreaBuffet[0]++;
                    break;
                case "Pizzas":
                    estadistiques.platsPerAreaBuffet[1]++;
                    break;
                case "Pastas":
                    estadistiques.platsPerAreaBuffet[2]++;
                    break;
            };
            return true;
        } else if (cola.afegirPlat()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public synchronized Boolean retirarPlat() {
        if (super.retirarPlat()) {
            switch (this.descripcio) {
                case "Hamburgueses":
                    estadistiques.platsPerAreaBuffet[0]--;
                    break;
                case "Pizzas":
                    estadistiques.platsPerAreaBuffet[1]--;
                    break;
                case "Pastas":
                    estadistiques.platsPerAreaBuffet[2]--;
                    break;
            };
            return true;
        } else if (cola.retirarPlat()){
            return true;
        } else {
            return false;
        }
    }

}
