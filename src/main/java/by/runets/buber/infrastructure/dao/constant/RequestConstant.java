package by.runets.buber.infrastructure.dao.constant;

public class ConstantDAO {
    private ConstantDAO(){}

    public final static String FIND_USER_BY_ID = "SELECT email, first_name, second_name, birth_date, ban, unban_date, phone_number, rating, bonus, trip_amount, r_name FROM user AS u\n" +
            "INNER JOIN user_role_m2m_user AS ur\n" +
            "ON u.id = ur.u_id\n" +
            "INNER JOIN role AS r\n" +
            "ON ur.ur_id = r.r_id\n" +
            "WHERE id=?";
    public final static String DELETE_USER_BY_ID = "DELETE FROM user WHERE id=?";
    public final static String UPDATE_USER_BY_ID = "UPDATE user SET email=?, first_name=?, second_name=?, birth_date=?, ban=?, unban_date=?, phone_number=?, rating, bonus=?, trip_amount=? WHERE id=?";
    public final static String INSTER_INTO_USER = "INSERT INTO user(email, first_name, second_name, birth_date, ban, unban_date, phone_number, rating, bonus, trip_amount) VALUES(?,?,?,?,?,?,?,?,?,?)";
}
