package by.runets.buber.domain.entity;

import java.util.Date;
import java.util.Optional;

public class Car extends Entity {
    private Optional<String> mark = Optional.empty();;
    private Optional<String> model;
    private Optional<Date> releaseDate;
    private Optional<String> licensePlate;
    private Optional<User> carOwner;
    private Optional<Point> currentLocation;

    public Car() {
    }

    public Car(int id, Optional<String> mark, Optional<String> model, Optional<Date> releaseDate, Optional<String> licensePlate, Optional<User> carOwner, Optional<Point> currentLocation) {
        super(id);
        this.mark = mark;
        this.model = model;
        this.releaseDate = releaseDate;
        this.licensePlate = licensePlate;
        this.carOwner = carOwner;
        this.currentLocation = currentLocation;
    }

    public Optional<String> getMark() {
        return mark;
    }
    public void setMark(Optional<String> mark) {
        this.mark = mark;
    }
    public Optional<String> getModel() {
        return model;
    }
    public void setModel(Optional<String> model) {
        this.model = model;
    }
    public Optional<Date> getReleaseDate() {
        return releaseDate;
    }
    public void setReleaseDate(Optional<Date> releaseDate) {
        this.releaseDate = releaseDate;
    }
    public Optional<String> getLicensePlate() {
        return licensePlate;
    }
    public void setLicensePlate(Optional<String> licensePlate) {
        this.licensePlate = licensePlate;
    }
    public Optional<User> getCarOwner() {
        return carOwner;
    }
    public void setCarOwner(Optional<User> carOwner) {
        this.carOwner = carOwner;
    }
    public Optional<Point> getCurrentLocation() {
        return currentLocation;
    }
    public void setCurrentLocation(Optional<Point> currentLocation) {
        this.currentLocation = currentLocation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Car car = (Car) o;

        if (mark != null ? !mark.equals(car.mark) : car.mark != null) return false;
        if (model != null ? !model.equals(car.model) : car.model != null) return false;
        if (releaseDate != null ? !releaseDate.equals(car.releaseDate) : car.releaseDate != null) return false;
        if (licensePlate != null ? !licensePlate.equals(car.licensePlate) : car.licensePlate != null) return false;
        if (carOwner != null ? !carOwner.equals(car.carOwner) : car.carOwner != null) return false;
        return currentLocation != null ? currentLocation.equals(car.currentLocation) : car.currentLocation == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (mark != null ? mark.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (releaseDate != null ? releaseDate.hashCode() : 0);
        result = 31 * result + (licensePlate != null ? licensePlate.hashCode() : 0);
        result = 31 * result + (carOwner != null ? carOwner.hashCode() : 0);
        result = 31 * result + (currentLocation != null ? currentLocation.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Car{" +
                "mark=" + mark +
                ", model=" + model +
                ", releaseDate=" + releaseDate +
                ", licensePlate=" + licensePlate +
                ", carOwner=" + carOwner +
                ", currentLocation=" + currentLocation +
                '}';
    }
}
