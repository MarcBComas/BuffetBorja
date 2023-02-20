package Controller;

import Model.Estadistiques;
import Model.GeneralStatus;
import Model.RestaurantModel;
import View.RestaurantView;

public class RestaurantController {
    private RestaurantModel model;
    private RestaurantView view;

    public void play() {
        this.model.setStatus(GeneralStatus.RUNNING);
    }

    public void pause() throws InterruptedException {
        this.model.setStatus(GeneralStatus.PAUSED);
    }

    public void stop() {
        this.model.setStatus(GeneralStatus.STOPPED);
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
