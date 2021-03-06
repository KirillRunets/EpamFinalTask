package by.runets.buber.domain.entity;

public class Bonus extends Entity {
    private String bonusType;
    private String bonusDescription;

    public Bonus() {
    }

    public Bonus(int id) {
        super(id);
    }

    public Bonus(int id, String bonusType, String bonusDescription) {
        super(id);
        this.bonusType = bonusType;
        this.bonusDescription = bonusDescription;
    }

    public String getBonusType() {
        return bonusType;
    }
    public void setBonusType(String bonusType) {
        this.bonusType = bonusType;
    }
    public String getBonusDescription() {
        return bonusDescription;
    }
    public void setBonusDescription(String bonusDescription) {
        this.bonusDescription = bonusDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Bonus bonus = (Bonus) o;

        if (bonusType != null ? !bonusType.equals(bonus.bonusType) : bonus.bonusType != null) return false;
        return bonusDescription != null ? bonusDescription.equals(bonus.bonusDescription) : bonus.bonusDescription == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (bonusType != null ? bonusType.hashCode() : 0);
        result = 31 * result + (bonusDescription != null ? bonusDescription.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Bonus{" +
                "bonusType=" + bonusType +
                ", bonusDescription=" + bonusDescription +
                '}';
    }
}
