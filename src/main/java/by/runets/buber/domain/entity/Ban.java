package by.runets.buber.domain.entity;

import java.util.Optional;

public class Ban extends Entity {
    private Optional<String> banType;
    private Optional<String> banDescription;

    public Ban() {
    }

    public Ban(int id) {
        super(id);
    }

    public Ban(int id, Optional<String> banType, Optional<String> banDescription) {
        super(id);
        this.banType = banType;
        this.banDescription = banDescription;
    }

    public Optional<String> getBanType() {
        return banType;
    }
    public void setBanType(Optional<String> banType) {
        this.banType = banType;
    }
    public Optional<String> getBanDescription() {
        return banDescription;
    }
    public void setBanDescription(Optional<String> banDescription) {
        this.banDescription = banDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Ban ban = (Ban) o;

        if (banType != null ? !banType.equals(ban.banType) : ban.banType != null) return false;
        return banDescription != null ? banDescription.equals(ban.banDescription) : ban.banDescription == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (banType != null ? banType.hashCode() : 0);
        result = 31 * result + (banDescription != null ? banDescription.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Ban{" +
                "banType=" + banType +
                ", banDescription=" + banDescription +
                '}';
    }
}
