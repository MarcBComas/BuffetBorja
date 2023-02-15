public class RestaurantController {
    private RestaurantModel model;
    private RestaurantView view;

    public void play()  {
        this.model.play();
    }

    public void pause() throws InterruptedException {
        this.model.pause();
    }

    public void stop() {
        this.model.stop();
    }

    public Estadistiques getStatistics() {
        return this.model.getEstadistiques();
    }

    public void canviStatusComensal() {

    }

    public void canviStatusAreaBuffet() {

    }

    public void canviStatusGrill() {

    }

    public void setModel(RestaurantModel model) {
        this.model = model;
    }

    public void setView(RestaurantView view) {
        this.view = view;
    }
}
