CREATE SCHEMA IF NOT EXISTS `lm_user_db` DEFAULT CHARACTER SET utf8;
USE lm_user_db;

create table user
(
	id BIGINT auto_increment,
	username VARCHAR(64) not null,
	first_name VARCHAR(64) not null,
	last_name VARCHAR(64) not null,
	mobile_number VARCHAR(11) not null,
	email_address VARCHAR(64) not null,
	date_created DATETIME not null,
	account_status VARCHAR(32) not null,
	version int not null,
	constraint user_pk
		primary key (id)
);

create unique index user_username_uindex
	on user (username);
create unique index user_email_address_uindex
	on user (email_address);


create table login_credentials
(
	id BIGINT auto_increment,
	username VARCHAR(64) not null,
	password VARCHAR(64) not null,
	constraint login_credentials_pk
		primary key (id),
	constraint login_details_user_username_fk
		foreign key (username) references user (username)
);

INSERT INTO lm_user_db.user (username, first_name, last_name, mobile_number, email_address, date_created, account_status, version) VALUES ('gon_frix', 'Gon', 'Frix', '09279124037', 'gon_frix@gmail.com', '2019-10-01 22:12:48', 'ACTIVE', 0);
INSERT INTO lm_user_db.user (username, first_name, last_name, mobile_number, email_address, date_created, account_status, version) VALUES ('kill_zold', 'Killua', 'Zoldyck', '09289123342', 'killua.zoldyck@gmail.com', '2019-10-01 22:12:48', 'ACTIVE', 0);

INSERT INTO lm_user_db.login_credentials (username, password) VALUES ('gon_frix', 'password123');
INSERT INTO lm_user_db.login_credentials (username, password) VALUES ('kill_zold', 'password123');

