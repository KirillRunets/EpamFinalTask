create table ban_reason
(
	b_id bigint auto_increment
		primary key,
	b_name varchar(255) null,
	b_description text null,
	constraint b_name_UNIQUE
		unique (b_name)
)
;

create table bonus
(
	bonus_id int auto_increment
		primary key,
	bonus_name varchar(255) null,
	bonus_description varchar(255) null,
	constraint bonus_name_UNIQUE
		unique (bonus_name)
)
;

create table car
(
	id int auto_increment
		primary key,
	mark varchar(255) null,
	model varchar(255) null,
	release_date date null,
	license_plate varchar(45) null,
	car_owner bigint null,
	current_location varchar(10) null,
	constraint license_plate_UNIQUE
		unique (license_plate)
)
;

create index FK_car_user_idx
	on car (car_owner)
;

create table `order`
(
	t_id int auto_increment
		primary key,
	distance double null,
	trip_cost double null,
	departure_point varchar(10) null,
	destination_point varchar(10) null,
	date date null,
	passenger_id bigint null,
	driver_id bigint null
)
;

create index order_user_id_fk
	on `order` (driver_id)
;

create index passenger_user_id_fk
	on `order` (passenger_id)
;

create table role
(
	r_id int auto_increment
		primary key,
	r_name varchar(255) null,
	constraint r_name_UNIQUE
		unique (r_name)
)
;

create table user
(
	id bigint auto_increment
		primary key,
	email varchar(255) null,
	password varchar(40) not null,
	first_name varchar(255) null,
	second_name varchar(255) null,
	birth_date date null,
	ban bigint null,
	unban_date date null,
	phone_number varchar(50) null,
	rating double null,
	bonus int null,
	trip_amount int null,
	constraint phone_number_UNIQUE
		unique (phone_number),
	constraint FK_user_ban_reason
		foreign key (ban) references ban_reason (b_id),
	constraint FK_user_bonus
		foreign key (bonus) references bonus (bonus_id)
			on delete set null
)
;

create index FK_user_ban_reason_idx
	on user (ban)
;

create index FK_user_bonus_idx
	on user (bonus)
;

alter table car
	add constraint FK_car_user
		foreign key (car_owner) references user (id)
			on delete cascade
;

alter table `order`
	add constraint passenger_user_id_fk
		foreign key (passenger_id) references user (id)
			on delete set null
;

alter table `order`
	add constraint order_user_id_fk
		foreign key (driver_id) references user (id)
			on delete set null
;

create table user_m2m_role
(
	ur_id int not null,
	u_id bigint not null,
	constraint FK_user_role_m2m_user_role
		foreign key (ur_id) references role (r_id),
	constraint FK_user_role_m2m_user_user
		foreign key (u_id) references user (id)
)
;

create index FK_user_role_m2m_user_role
	on user_m2m_role (ur_id)
;

create index u_id_idx
	on user_m2m_role (u_id)
;

CREATE TRIGGER `calculate_passenger_trip_amount` AFTER INSERT ON `order`
FOR EACH ROW BEGIN
	DECLARE count INT;
	SET count = (SELECT COUNT(*) FROM `order` WHERE passenger_id = NEW.passenger_id AND isCompleted=1);
	UPDATE user SET trip_amount = count WHERE id = new.passenger_id;
END;

CREATE TRIGGER `calculate_driver_trip_amount` AFTER INSERT ON `order`
FOR EACH ROW BEGIN
	DECLARE count INT;
	SET count = (SELECT COUNT(*) FROM `order` WHERE driver_id = NEW.driver_id AND isCompleted=1);
	UPDATE user SET trip_amount = count WHERE id = new.driver_id;
END;

