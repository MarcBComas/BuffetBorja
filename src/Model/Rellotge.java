package Model;

public class Rellotge implements Runnable{
    private static Rellotge rellotge;
    private int minutActual;
    private int multiplicadorTemps;
    private GeneralStatus estat;

    public Rellotge() {
        this.minutActual = 0;
        this.multiplicadorTemps = 1;
    }

    public Integer minutsEnMilisegons(int minuts) {
        return minuts * 1000;
    }

    public Integer getMinutoActual() {
        return this.minutActual;
    }

    public Integer getIntervalEnMinuts(int interval) {
        return interval * this.multiplicadorTemps;
    }

    public static Rellotge getRellotge() {
        if (rellotge == null) {
            rellotge = new Rellotge();
        }
        return rellotge;
    }

    public static Rellotge reset() {
        return rellotge = new Rellotge();
    }

    public void setEstat(GeneralStatus estat) {
        this.estat = estat;
    }

    @Override
    public void run() {
        while (estat != GeneralStatus.STOPPED) {
            while (estat == GeneralStatus.RUNNING) {
                try {
                    Thread.sleep(1000);
                    this.minutActual++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
