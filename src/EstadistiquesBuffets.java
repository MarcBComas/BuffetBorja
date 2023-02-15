public class EstadistiquesBuffets {
    public static int[] platsPerAreaBuffet;
    public static int[] platsEnColaPerAreaBuffet;

    public EstadistiquesBuffets() {
        platsPerAreaBuffet = new int[3];
        platsEnColaPerAreaBuffet = new int[3];
    }

    public static int[] getPlatsPerAreaBuffet() {
        return platsPerAreaBuffet;
    }

    public static int[] getPlatsEnColaPerAreaBuffet() {
        return platsEnColaPerAreaBuffet;
    }
}
