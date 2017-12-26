package by.runets.buber.domain.entity;

import java.util.Optional;

public class Role extends Entity {
    private Optional<String> roleName;

    public Role(int id) {
        super(id);
    }

    public Role(int id, Optional<String> roleName) {
        super(id);
        this.roleName = roleName;
    }

    public Optional<String> getRoleName() {
        return roleName;
    }
    public void setRoleName(Optional<String> roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Role role = (Role) o;

        return roleName != null ? roleName.equals(role.roleName) : role.roleName == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "roleName: " + roleName;
    }
}
