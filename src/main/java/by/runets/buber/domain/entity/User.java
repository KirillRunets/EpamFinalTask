package by.runets.buber.domain.entity;

import java.util.Date;
import java.util.Optional;

public class User extends Entity {
    private String email;
    private String password;
    private String firstName;
    private String secondName;
    private Date birthDate;
    private Ban ban;
    private Date unBaneDate;
    private String phoneNumber;
    private Bonus bonus;
    private int tripAmount;
    private double rating;
    private Role role;
    private Car car;

    public User() {
    }

    public User(int id) {
        super(id);
    }

    public User(int id, Role role) {
        super(id);
        this.role = role;
    }

    public User(int id, String email, String password, String firstName, String secondName, Date birthDate, Ban ban, Date unBaneDate, String phoneNumber, Bonus bonus, int tripAmount, double rating, Role role, Car car) {
        super(id);
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthDate = birthDate;
        this.ban = ban;
        this.unBaneDate = unBaneDate;
        this.phoneNumber = phoneNumber;
        this.bonus = bonus;
        this.tripAmount = tripAmount;
        this.rating = rating;
        this.role = role;
        this.car = car;
    }

    public User(String email, String password, String firstName, String secondName, Role role) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
        this.role = role;
    }

    public User(int id, String email, String password, String firstName, String secondName, Role role) {
        super(id);
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
        this.role = role;
    }

    public User(int id, String email, String firstName, String secondName, Date birthDate, double rating, int tripAmount, String phoneNumber, Role role) {
        super(id);
        this.email = email;
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.tripAmount = tripAmount;
        this.rating = rating;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getSecondName() {
        return secondName;
    }
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }
    public Date getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    public Date getUnBaneDate() {
        return unBaneDate;
    }
    public void setUnBaneDate(Date unbaneDate) {
        this.unBaneDate = unbaneDate;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public int getTripAmount() {
        return tripAmount;
    }
    public void setTripAmount(int tripAmount) {
        this.tripAmount = tripAmount;
    }
    public double getRating() {
        return rating;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Ban getBan() {
        return ban;
    }
    public void setBan(Ban ban) {
        this.ban = ban;
    }
    public Bonus getBonus() {
        return bonus;
    }
    public void setBonus(Bonus bonus) {
        this.bonus = bonus;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public Car getCar() {
        return car;
    }
    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        if (tripAmount != user.tripAmount) return false;
        if (Double.compare(user.rating, rating) != 0) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (secondName != null ? !secondName.equals(user.secondName) : user.secondName != null) return false;
        if (birthDate != null ? !birthDate.equals(user.birthDate) : user.birthDate != null) return false;
        if (ban != null ? !ban.equals(user.ban) : user.ban != null) return false;
        if (unBaneDate != null ? !unBaneDate.equals(user.unBaneDate) : user.unBaneDate != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(user.phoneNumber) : user.phoneNumber != null) return false;
        if (bonus != null ? !bonus.equals(user.bonus) : user.bonus != null) return false;
        if (role != null ? !role.equals(user.role) : user.role != null) return false;
        return car != null ? car.equals(user.car) : user.car == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        result = 31 * result + (ban != null ? ban.hashCode() : 0);
        result = 31 * result + (unBaneDate != null ? unBaneDate.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (bonus != null ? bonus.hashCode() : 0);
        result = 31 * result + tripAmount;
        temp = Double.doubleToLongBits(rating);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (car != null ? car.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", birthDate=" + birthDate +
                ", ban=" + ban +
                ", unBaneDate=" + unBaneDate +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", bonus=" + bonus +
                ", tripAmount=" + tripAmount +
                ", rating=" + rating +
                ", role=" + role +
                ", car=" + car +
                '}';
    }
}
