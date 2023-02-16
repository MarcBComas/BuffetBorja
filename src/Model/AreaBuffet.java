package Model;

public class AreaBuffet extends BuffetPlats{
    private static EstadistiquesBuffets estadistiques = new EstadistiquesBuffets();
    private String descripcio;
    private ColaPlatsCuinats cola;
    private Grill grill;

    public AreaBuffet(int capacitatMaxima, Grill grill, String descripcio) {
        super(capacitatMaxima);
        this.cola = new ColaPlatsCuinats(this);
        this.grill = grill;
        this.descripcio = descripcio;
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
            switch (this.descripcio) {
                case "Hamburgueses":
                    estadistiques.platsEnColaPerAreaBuffet[0]++;
                    break;
                case "Pizzas":
                    estadistiques.platsEnColaPerAreaBuffet[1]++;
                    break;
                case "Pastas":
                    estadistiques.platsEnColaPerAreaBuffet[2]++;
                    break;
            };
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
        } else if (cola.retirarPlat()) {
            switch (this.descripcio) {
                case "Hamburgueses":
                    estadistiques.platsEnColaPerAreaBuffet[0]--;
                    break;
                case "Pizzas":
                    estadistiques.platsEnColaPerAreaBuffet[1]--;
                    break;
                case "Pastas":
                    estadistiques.platsEnColaPerAreaBuffet[2]--;
                    break;
            };
            return true;
        } else {
            return false;
        }
    }

    public static EstadistiquesBuffets getEstadistiques() {
        return estadistiques;
    }
}
