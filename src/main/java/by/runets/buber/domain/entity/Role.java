package by.runets.buber.domain.entity;

import by.runets.buber.infrastructure.constant.UserRoleType;

public class Role extends Entity {
    private String roleName;

    public Role(String roleName) {
        switch (roleName.toUpperCase()){
            case UserRoleType.ADMIN:
                setId(1);
                break;
            case UserRoleType.DRIVER:
                setId(2);
                break;
            case UserRoleType.PASSENGER:
                setId(3);
                break;

        }
        this.roleName = roleName;
    }

    public Role(int id) {
        super(id);
    }

    public Role(int id, String roleName) {
        super(id);
        this.roleName = roleName;
    }
    public String getRoleName() {
        return roleName;
    }
    public void setRoleName(String roleName) {
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
        return "Role{" +
                "roleName='" + roleName + '\'' +
                '}';
    }
}
