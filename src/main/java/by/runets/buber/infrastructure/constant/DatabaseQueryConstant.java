package by.runets.buber.infrastructure.constant;

public class DatabaseQueryConstant {
    private DatabaseQueryConstant(){}
    //Account
    public final static String SELECT_PAY_AMOUNT_FROM_ACCOUNT = "SELECT account_amount FROM account WHERE account_id=?";
    public final static String UPDATE_ACCOUNT = "UPDATE account SET account_amount=? WHERE account_id=?";
    public final static String FIND_ACCOUNT_BY_USER_ID_FROM_USER = "SELECT account_id FROM user WHERE id=?";
    public final static String FIND_ACCOUNT_BY_ID_FROM_ACCOUNT = "SELECT account_id, account_amount FROM account WHERE account_id=?";
    public final static String CREATE_NEW_ACCOUNT = "INSERT INTO account(account_amount) VALUES(10000);";
    //user
    public final static String SET_BAN_TO_USER = "UPDATE user SET ban=?, unban_date=?  WHERE id=?";
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
    public final static String FIND_USER_BY_EMAIL_PASSWORD = "SELECT id, email, password, first_name, second_name, birth_date, ban, unban_date, phone_number, rating, bonus, trip_amount,  r_name, r_id FROM user AS u\n" +
            "INNER JOIN user_m2m_role AS ur\n" +
            "ON u.id = ur.u_id\n" +
            "INNER JOIN role AS r\n" +
            "ON ur.ur_id = r.r_id\n" +
            "WHERE BINARY email=? AND BINARY password=?";
    public final static String SELECT_USER_PASSWORD = "SELECT password FROM user WHERE id=?";
    public final static String UPDATE_PASSWORD_BY_ID = "UPDATE user SET password=? WHERE id=?";
    public final static String FIND_EMAIL_EXIST = "SELECT email FROM user WHERE email=?";
    public final static String DELETE_USER_BY_ID = "DELETE FROM user WHERE id=?";
    public final static String UPDATE_USER_BY_ID = "UPDATE user SET email=?, first_name=?, second_name=?, birth_date=?, ban=?, unban_date=?, phone_number=?, rating=?, bonus=?, trip_amount=? WHERE id=?";
    public final static String INSERT_INTO_USER = "INSERT INTO user(email, password, first_name, second_name, birth_date, ban, unban_date, phone_number, rating, bonus, trip_amount, account_id) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
    public final static String FIND_BANNED_USERS = "SELECT id, email, password, first_name, second_name, ban, unban_date, birth_date, phone_number, rating, trip_amount, bonus, r_name, r_id FROM user AS u\n" +
            "INNER JOIN user_m2m_role AS ur\n" +
            "ON u.id = ur.u_id\n" +
            "INNER JOIN role AS r\n" +
            "ON ur.ur_id = r.r_id\n" +
            "WHERE ban IS NOT NULL AND unban_date IS NOT NULL";
    public final static String UPDATE_USER_RATING = "UPDATE user SET rating=? WHERE id=?";
    //car
    public final static String FIND_ALL_CARS = "SELECT id, mark, model, release_date, license_plate, car_owner, current_location FROM car";
    public final static String FIND_CAR_BY_OWNER = "SELECT id, mark, model, car_owner, release_date, license_plate, current_location FROM car WHERE car_owner=?";
    public final static String DELETE_CAR_BY_ID = "DELETE FROM car WHERE id=?";
    public final static String INSERT_INTO_CAR = "INSERT INTO car(mark, model, release_date, license_plate, car_owner, current_location) VALUES(?,?,?,?,?,?)";
    public final static String UPDATE_CAR_BY_ID = "UPDATE car SET mark=?, model=?, release_date=?, license_plate=?, car_owner=?, current_location=? WHERE id=?";
    //order
    public final static String FIND_ALL_ORDERS = "SELECT t_id, distance, trip_cost, departure_point, destination_point, date, driver_id, passenger_id, isConfirmed, isCompleted, isPaid FROM `order` WHERE isCompleted=1";
    public final static String FIND_ORDER_BY_PASSENGER_ID = "SELECT t_id, distance, trip_cost, departure_point, destination_point, date, driver_id, passenger_id, first_name, second_name, email, birth_date, trip_amount, phone_number, rating, isConfirmed, isCompleted, isPaid FROM `order`\n" +
            "INNER JOIN user AS u\n" +
            "ON u.id = `order`.driver_id " +
            "WHERE passenger_id=? AND isCompleted=1";
    public final static String FIND_ORDER_BY_DRIVER_ID = "SELECT t_id, distance, trip_cost, departure_point, destination_point, date, passenger_id, driver_id, first_name, second_name, email, birth_date, trip_amount, phone_number, rating, isConfirmed, isCompleted, isPaid  FROM `order`\n" +
            "INNER JOIN user AS u\n" +
            "ON u.id = `order`.passenger_id\n" +
            "WHERE driver_id=? AND isCompleted=1;";
    public final static String DELETE_ORDER_BY_ID = "DELETE FROM `order` WHERE t_id=?";
    public final static String INSERT_INTO_ORDER = "INSERT INTO `order`(distance, trip_cost, departure_point, destination_point, date, driver_id, passenger_id, trip_time) VALUES(?,?,?,?,?,?,?,?)";
    public final static String UPDATE_ORDER_BY_ID = "UPDATE `order` SET distance=?, trip_cost=?, departure_point=?, destination_point=?, date=?, driver_id=?, passenger_id=?, trip_time=? WHERE t_id=?";
    public final static String CONFIRM_ORDER_BY_DRIVER = "UPDATE `order` SET isConfirmed=1 WHERE t_id=?";
    public final static String IS_EXIST_ORDER_FOR_DRIVER = "SELECT t_id, distance, trip_cost, departure_point, destination_point, date, passenger_id, driver_id, isConfirmed, isCompleted, isPaid, first_name, second_name, email, birth_date, trip_amount, phone_number, rating FROM `order`\n" +
            "INNER JOIN user AS u\n" +
            "ON u.id = `order`.passenger_id\n" +
            "WHERE driver_id=? AND isCompleted=0";
    public final static String IS_EXIST_ORDER_FOR_PASSENGER = "SELECT t_id, distance, trip_cost, departure_point, destination_point, date, passenger_id, driver_id, isConfirmed, isCompleted, isPaid, first_name, second_name, email, birth_date, trip_amount, phone_number, rating FROM `order`\n" +
            "INNER JOIN user AS u\n" +
            "ON u.id = `order`.driver_id\n" +
            "WHERE passenger_id=? AND isCompleted=0";
    public final static String REVOKE_ORDER_BY_DRIVER = "UPDATE `order` SET driver_id=? WHERE driver_id=? AND t_id=?";
    public final static String REVOKE_ORDER_BY_PASSENGER = "DELETE FROM `order` WHERE passenger_id=? AND t_id=? AND isConfirmed=0 AND isCompleted=0";
    public final static String COMPLETE_ORDER = "UPDATE `order` SET isCompleted=1 WHERE t_id=?";
    public final static String SET_ORDER_IS_PAID = "UPDATE `order` SET isPaid=1 WHERE t_id=?";
    //ban
    public final static String FIND_ALL_BANS = "SELECT b_id, b_name, b_description FROM ban_reason";
    public final static String FIND_BAN_BY_ID = "SELECT b_id, b_name, b_description FROM ban_reason WHERE b_id=?";
    public final static String DELETE_BAN_BY_ID = "DELETE FROM ban_reason WHERE b_id=?";
    public final static String INSERT_INTO_BAN = "INSERT INTO ban_reason(b_name, b_description) VALUES(?, ?)";
    public final static String UPDATE_BAN_BY_ID = "UPDATE ban_reason SET b_name=?, b_description=? WHERE b_id=?";

    //bonus
    public final static String  FIND_ALL_BONUSES = "SELECT bonus_id, bonus_name, bonus_description FROM bonus";
    public final static String FIND_BONUS_BY_ID = "SELECT bonus_id, bonus_name, bonus_description FROM bonus WHERE bonus_id=?";
    public final static String DELETE_BONUS_BY_ID = "DELETE FROM bonus WHERE bonus_id=?";
    public final static String INSERT_INTO_BONUS = "INSERT INTO bonus(bonus_name, bonus_description) VALUES(?,?)";
    public final static String UPDATE_BONUS_BY_ID = "UPDATE bonus SET bonus_name=?, bonus_description=? WHERE bonus_id=?";

    //user_m2m_role
    public final static String INSERT_INTO_USER_M2M_ROLE = "INSERT INTO user_m2m_role(ur_id, u_id) VALUES(?,?)";
    public final static String UPDATE_USER_M2M_ROLE = "UPDATE user_m2m_role SET ur_id=? WHERE u_id=?";
    public final static String DELETE_USER_M2M_ROLE = "DELETE FROM user_m2m_role WHERE u_id=?";

    //transaction
    public final static String COMMIT_TO_TRANSACTION_STORY = "INSERT INTO transaction_story(from_account, to_account, amount, transaction_date) VALUES(?,?,?,?)";
    public final static String FIND_ALL_TRANSACTION = "SELECT transaction_id, from_account, to_account, amount, transaction_date FROM transaction_story";
    public final static String COMMIT_TRANSACTION = "INSERT INTO transaction_story(from_account, to_account, amount, transaction_date) VALUES(?,?,?,?)";
    public final static String ROLLBACK_TRANSACTION = "SELECT transaction_id, from_account, to_account, amount, transaction_date FROM transaction_story WHERE transaction_id=?";
    public final static String EDIT_TRANSACTION = "UPDATE transaction_story SET amount=?, transaction_date=? WHERE transaction_id=?";
}
