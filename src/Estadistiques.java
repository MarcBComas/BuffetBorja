public class Estadistiques {
    public EstadistiquesChefs chef;
    public EstadistiquesComensals comensal;
    public EstadistiquesBuffets areaBuffet;

    public Estadistiques() {
        chef = new EstadistiquesChefs();
        comensal = new EstadistiquesComensals();
        areaBuffet = new EstadistiquesBuffets();
    }
}
