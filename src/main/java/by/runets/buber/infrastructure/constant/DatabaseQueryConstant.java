package by.runets.buber.infrastructure.constant;

public class DatabaseQueryConstant {
    private DatabaseQueryConstant(){}
    //user
    public final static String FIND_ALL_USERS = "SELECT id, email, password, first_name, second_name, birth_date, ban, unban_date, phone_number, rating, bonus, trip_amount, r_name, r_id FROM user AS u\n" +
            "INNER JOIN user_m2m_role AS ur\n" +
            "ON u.id = ur.u_id\n" +
            "INNER JOIN role AS r\n" +
            "ON ur.ur_id = r.r_id";
    public final static String FIND_USER_BY_ID = "SELECT id, email, password, first_name, second_name, birth_date, ban, unban_date, phone_number, rating, bonus, trip_amount, r_name, r_id FROM user AS u\n" +
            "INNER JOIN user_m2m_role AS ur\n" +
            "ON u.id = ur.u_id\n" +
            "INNER JOIN role AS r\n" +
            "ON ur.ur_id = r.r_id\n" +
            "WHERE id=?";
    public final static String FIND_USER_BY_EMAIL_PASSWORD = "SELECT id, email, password, first_name, second_name, birth_date, ban, unban_date, phone_number, rating, bonus, trip_amount, r_name, r_id FROM user AS u\n" +
            "INNER JOIN user_m2m_role AS ur\n" +
            "ON u.id = ur.u_id\n" +
            "INNER JOIN role AS r\n" +
            "ON ur.ur_id = r.r_id\n" +
            "WHERE email=? AND password=?";
    public final static String FIND_EMAIL_EXIST = "SELECT email FROM user WHERE email=?";
    public final static String DELETE_USER_BY_ID = "DELETE FROM user WHERE id=?";
    public final static String UPDATE_USER_BY_ID = "UPDATE user SET email=?, first_name=?, second_name=?, birth_date=?, ban=?, unban_date=?, phone_number=?, rating=?, bonus=?, trip_amount=? WHERE id=?";
    public final static String INSERT_INTO_USER = "INSERT INTO user(email, password, first_name, second_name, birth_date, ban, unban_date, phone_number, rating, bonus, trip_amount) VALUES(?,?,?,?,?,?,?,?,?,?,?)";

    //car
    public final static String FIND_ALL_CARS = "SELECT mark, model, release_date, license_plate, car_owner, current_location FROM car";
    public final static String FIND_CAR_BY_OWNER = "SELECT id, mark, model, car_owner, release_date, license_plate, current_location FROM car WHERE car_owner=?";
    public final static String DELETE_CAR_BY_ID = "DELETE FROM car WHERE id=?";
    public final static String INSERT_INTO_CAR = "INSERT INTO car(mark, model, release_date, license_plate, car_owner, current_location) VALUES(?,?,?,?,?,?)";
    public final static String UPDATE_CAR_BY_ID = "UPDATE car SET mark=?, model=?, release_date=?, license_plate=?, car_owner=?, current_location=? WHERE id=?";
    //order
    public final static String FIND_ALL_ORDERS = "SELECT distance, trip_cost, departure_point, destination_point, date, driver_id, passenger_id FROM order";
    public final static String FIND_ORDER_BY_ID = "SELECT distance, trip_cost, departure_point, destination_point, date, driver_id, passenger_id FROM order WHERE t_id=?";
    public final static String DELETE_ORDER_BY_ID = "DELETE FROM order WHERE t_id=?";
    public final static String INSERT_INTO_ORDER = "INSERT INTO order(distance, trip_cost, departure_point,destination_point, date, driver_id, passenger_id) VALUES(?,?,?,?,?,?,?)";
    public final static String UPDATE_ORDER_BY_ID = "UPDATE order SET distance=?, trip_cost=?, departure_point=?, destination_point=?, date=?, driver_id=?, passenger_id=? WHERE t_id=?";

    //ban
    public final static String FIND_ALL_BANS = "SELECT b_name, b_description FROM ban_reason";
    public final static String FIND_BAN_BY_ID = "SELECT b_name, b_description FROM ban_reason WHERE b_id=?";
    public final static String DELETE_BAN_BY_ID = "DELETE FROM ban_reason WHERE b_id=?";
    public final static String INSERT_INTO_BAN = "INSERT INTO ban_reason(b_name, b_description) VALUES(?, ?)";
    public final static String UPDATE_BAN_BY_ID = "UPDATE ban_reason SET b_name=?, b_description=?";

    //bonus
    public final static String  FIND_ALL_BONUSES = "SELECT bonus_name, bonus_description FROM bonus";
    public final static String FIND_BONUS_BY_ID = "SELECT bonus_name, bonus_description FROM bonus WHERE bonus_id=?";
    public final static String DELETE_BONUS_BY_ID = "DELETE FROM bonus WHERE bonus_id=?";
    public final static String INSERT_INTO_BONUS = "INSERT INTO bonus(bonus_name, bonus_description) VALUES(?,?)";
    public final static String UPDATE_BONUS_BY_ID = "UPDATE bonus SET bonus_name?, bonus_description=? WHERE bonus_id=?";

    //user_m2m_role
    public final static String INSERT_INTO_USER_M2M_ROLE = "INSERT INTO user_m2m_role(ur_id, u_id) VALUES(?,?)";
    public final static String UPDATE_USER_M2M_ROLE = "UPDATE user_m2m_role SET ur_id=? WHERE u_id=?";
    public final static String DELETE_USER_M2M_ROLE = "DELETE FROM user_m2m_role WHERE u_id=?";
}
