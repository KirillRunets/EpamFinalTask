package by.runets.buber.domain.entity;

import java.util.Date;
import java.util.Optional;

public class Order extends Entity{
    private Double distance;
    private Double tripCost;
    private Double tripTime;
    private Point startPoint;
    private Point destinationPoint;
    private Date orderDate;
    private User driver;
    private User passenger;
    private boolean isConfirmed;
    private boolean isCompleted;
    private boolean isPaid;

    public Order() {
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
    public Point getStartPoint() {
        return startPoint;
    }
    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }
    public Point getDestinationPoint() {
        return destinationPoint;
    }
    public void setDestinationPoint(Point destinationPoint) {
        this.destinationPoint = destinationPoint;
    }
    public Date getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    public Double getTripTime() {
        return tripTime;
    }
    public void setTripTime(Double tripTime) {
        this.tripTime = tripTime;
    }
    public boolean isConfirmed() {
        return isConfirmed;
    }
    public boolean getConfirmed() {
        return isConfirmed;
    }
    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }
    public boolean isCompleted() {
        return isCompleted;
    }
    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
    public boolean getCompleted(){
        return isCompleted;
    }
    public boolean isPaid() {
        return isPaid;
    }
    public boolean getPaid() {
        return isPaid;
    }
    public void setPaid(boolean paid) {
        isPaid = paid;
    }
    public User getDriver() {
        return driver;
    }
    public void setDriver(User driver) {
        this.driver = driver;
    }
    public User getPassenger() {
        return passenger;
    }
    public void setPassenger(User passenger) {
        this.passenger = passenger;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Order order = (Order) o;

        if (isConfirmed != order.isConfirmed) return false;
        if (isCompleted != order.isCompleted) return false;
        if (isPaid != order.isPaid) return false;
        if (distance != null ? !distance.equals(order.distance) : order.distance != null) return false;
        if (tripCost != null ? !tripCost.equals(order.tripCost) : order.tripCost != null) return false;
        if (tripTime != null ? !tripTime.equals(order.tripTime) : order.tripTime != null) return false;
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
        result = 31 * result + (tripTime != null ? tripTime.hashCode() : 0);
        result = 31 * result + (startPoint != null ? startPoint.hashCode() : 0);
        result = 31 * result + (destinationPoint != null ? destinationPoint.hashCode() : 0);
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
        result = 31 * result + (driver != null ? driver.hashCode() : 0);
        result = 31 * result + (passenger != null ? passenger.hashCode() : 0);
        result = 31 * result + (isConfirmed ? 1 : 0);
        result = 31 * result + (isCompleted ? 1 : 0);
        result = 31 * result + (isPaid ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "distance=" + distance +
                ", tripCost=" + tripCost +
                ", tripTime=" + tripTime +
                ", startPoint=" + startPoint +
                ", destinationPoint=" + destinationPoint +
                ", orderDate=" + orderDate +
                ", driver=" + driver +
                ", passenger=" + passenger +
                ", isConfirmed=" + isConfirmed +
                ", isCompleted=" + isCompleted +
                ", isPaid=" + isPaid +
                '}';
    }
}
