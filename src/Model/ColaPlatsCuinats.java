package Model;

public class ColaPlatsCuinats extends BuffetPlats{
    private AreaBuffet area;
    public ColaPlatsCuinats(AreaBuffet area) {
        super(new ParametresSimulacio().limitPlatsEnCoa);
        this.area = area;
    }

    public boolean afegirPlat(Grill grill) {
        if (super.afegirPlat()) {
            return true;
        } else {
            grill.treureDeServei();
            return false;
        }
    }

    public boolean retirarPlat(Grill grill) {
        if (super.retirarPlat()) {
            return true;
        } else {
            grill.posarEnServei();
            return false;
        }
    }
}
