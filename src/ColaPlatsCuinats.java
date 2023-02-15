public class ColaPlatsCuinats extends BuffetPlats{
    private AreaBuffet area;
    public ColaPlatsCuinats(AreaBuffet area) {
        super(new ParametresSimulacio().limitPlatsEnCoa);
        this.area = area;
    }

    public boolean afegirPlat(Grill grill) {
        if (!super.afegirPlat()) {
            grill.treureDeServei();
            return false;
        } else {
            return true;
        }
    }

    public boolean retirarPlat(Grill grill) {
        if (!super.retirarPlat()) {
            grill.posarEnServei();
            return false;
        } else {
            return true;
        }
    }
}
