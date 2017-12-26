package by.runets.buber.domain.entity;

import java.util.Date;
import java.util.Optional;

public class Order extends Entity{
    private Double distance;
    private Double tripCost;
    private Optional<Point> startPoint;
    private Optional<Point> destinationPoint;
    private Date orderDate;
    private Optional<User> driver;
    private Optional<User> passenger;

    public Order() {
    }

    public Order(int id, Double distance, Double tripCost, Optional<Point> startPoint, Optional<Point> destinationPoint, Date orderDate, Optional<User> driver, Optional<User> passenger) {
        super(id);
        this.distance = distance;
        this.tripCost = tripCost;
        this.startPoint = startPoint;
        this.destinationPoint = destinationPoint;
        this.orderDate = orderDate;
        this.driver = driver;
        this.passenger = passenger;
    }

    public Double getDistance() {
        return distance;
    }
    public void setDistance(Double distance) {
        this.distance = distance;
    }
    public Double getTripCost() {
        return tripCost;
    }
    public void setTripCost(Double tripCost) {
        this.tripCost = tripCost;
    }
    public Optional<Point> getStartPoint() {
        return startPoint;
    }
    public void setStartPoint(Optional<Point> startPoint) {
        this.startPoint = startPoint;
    }
    public Optional<Point> getDestinationPoint() {
        return destinationPoint;
    }
    public void setDestinationPoint(Optional<Point> destinationPoint) {
        this.destinationPoint = destinationPoint;
    }
    public Date getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    public Optional<User> getDriver() {
        return driver;
    }
    public void setDriver(Optional<User> driver) {
        this.driver = driver;
    }
    public Optional<User> getPassenger() {
        return passenger;
    }
    public void setPassenger(Optional<User> passenger) {
        this.passenger = passenger;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Order order = (Order) o;

        if (distance != null ? !distance.equals(order.distance) : order.distance != null) return false;
        if (tripCost != null ? !tripCost.equals(order.tripCost) : order.tripCost != null) return false;
        if (startPoint != null ? !startPoint.equals(order.startPoint) : order.startPoint != null) return false;
        if (destinationPoint != null ? !destinationPoint.equals(order.destinationPoint) : order.destinationPoint != null)
            return false;
        if (orderDate != null ? !orderDate.equals(order.orderDate) : order.orderDate != null) return false;
        if (driver != null ? !driver.equals(order.driver) : order.driver != null) return false;
        return passenger != null ? passenger.equals(order.passenger) : order.passenger == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (distance != null ? distance.hashCode() : 0);
        result = 31 * result + (tripCost != null ? tripCost.hashCode() : 0);
        result = 31 * result + (startPoint != null ? startPoint.hashCode() : 0);
        result = 31 * result + (destinationPoint != null ? destinationPoint.hashCode() : 0);
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
        result = 31 * result + (driver != null ? driver.hashCode() : 0);
        result = 31 * result + (passenger != null ? passenger.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "distance=" + distance +
                ", tripCost=" + tripCost +
                ", startPoint=" + startPoint +
                ", destinationPoint=" + destinationPoint +
                ", orderDate=" + orderDate +
                ", driver=" + driver +
                ", passenger=" + passenger +
                '}';
    }
}
