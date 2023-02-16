package Model;

public class Estadistiques {
    public EstadistiquesChefs chef;
    public EstadistiquesComensals comensal;
    public EstadistiquesBuffets areaBuffet;

    public Estadistiques(EstadistiquesChefs chef, EstadistiquesComensals comensal, EstadistiquesBuffets areaBuffet) {
        this.chef = chef;
        this.comensal = comensal;
        this.areaBuffet = areaBuffet;
    }

    public EstadistiquesChefs getChef() {
        return chef;
    }

    public void setChef(EstadistiquesChefs chef) {
        this.chef = chef;
    }

    public EstadistiquesComensals getComensal() {
        return comensal;
    }

    public void setComensal(EstadistiquesComensals comensal) {
        this.comensal = comensal;
    }

    public EstadistiquesBuffets getAreaBuffet() {
        return areaBuffet;
    }

    public void setAreaBuffet(EstadistiquesBuffets areaBuffet) {
        this.areaBuffet = areaBuffet;
    }
}
