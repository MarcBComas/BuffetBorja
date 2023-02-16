package Model;

public class EstadistiquesChefs {
    public static int tempsDescansant;
    public static int tempsCuinant;
    public static int platsCuinats;
    public static int[] chefsPerEstat;

    public EstadistiquesChefs() {
        tempsDescansant = 0;
        tempsCuinant = 0;
        platsCuinats = 0;
        chefsPerEstat = new int[ChefStatus.values().length];
    }

    public int getTempsDescansant() {
        return tempsDescansant;
    }

    public int getTempsCuinant() {
        return tempsCuinant;
    }

    public int getPlatsCuinats() {
        return platsCuinats;
    }

    public int[] getChefsPerEstat() {
        return chefsPerEstat;
    }
}
