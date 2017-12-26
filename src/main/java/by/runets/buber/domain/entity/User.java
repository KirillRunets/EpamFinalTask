package by.runets.buber.domain.entity;

import java.util.Date;
import java.util.Optional;

public class User extends Entity {
    private String email;
    private String firstName;
    private String secondName;
    private Date birthDate;
    private Ban ban;
    private Date unBaneDate;
    private String phoneNumber;
    private Bonus bonus;
    private int tripAmount;
    private double rating;

    public User() {
        ban = new Ban();
        bonus = new Bonus();
    }

    public User(int id, String email, String firstName, String secondName, Date birthDate, Ban ban, Date unBaneDate, String phoneNumber, Bonus bonus, int tripAmount, double rating) {
        super(id);
        this.email = email;
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthDate = birthDate;
        this.ban = ban;
        this.unBaneDate = unBaneDate;
        this.phoneNumber = phoneNumber;
        this.bonus = bonus;
        this.tripAmount = tripAmount;
        this.rating = rating;
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
    public Ban getBan() {
        return ban;
    }
    public void setBan(Ban ban) {
        this.ban = ban;
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
    public Bonus getBonus() {
        return bonus;
    }
    public void setBonus(Bonus bonus) {
        this.bonus = bonus;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        if (tripAmount != user.tripAmount) return false;
        if (Double.compare(user.rating, rating) != 0) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (secondName != null ? !secondName.equals(user.secondName) : user.secondName != null) return false;
        if (birthDate != null ? !birthDate.equals(user.birthDate) : user.birthDate != null) return false;
        if (ban != null ? !ban.equals(user.ban) : user.ban != null) return false;
        if (unBaneDate != null ? !unBaneDate.equals(user.unBaneDate) : user.unBaneDate != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(user.phoneNumber) : user.phoneNumber != null) return false;
        return bonus != null ? bonus.equals(user.bonus) : user.bonus == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + (email != null ? email.hashCode() : 0);
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
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", birthDate=" + birthDate +
                ", ban=" + ban +
                ", unBaneDate=" + unBaneDate +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", bonus=" + bonus +
                ", tripAmount=" + tripAmount +
                ", rating=" + rating +
                '}';
    }
}
