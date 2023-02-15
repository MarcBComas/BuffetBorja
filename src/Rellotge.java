public class Rellotge implements Runnable{
    private static Rellotge rellotge;
    private int minutActual;
    private int multiplicadorTemps;

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

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.minutActual++;
        }
    }
}
