create table REF_MONTH
(
	ID int auto_increment,
	NAME varchar(100) not null,
	SHORT_NAME VARCHAR(3) null,
	QUARTER_ID int null,
	constraint REF_MONTH_pk primary key (ID)
);

CREATE TABLE REF_YEAR (
	ID INT,
	NAME VARCHAR(4),
	constraint REF_YEAR_pk primary key (ID)
)

CREATE TABLE REF_QUARTER (
	ID INT auto_increment,
	NAME VARCHAR(5),
	constraint REF_QUARTER_pk primary key (ID)
)

CREATE TABLE COUNTER_DATA (
	ID INT auto_increment,
	COUNTER_ID LONG not null,
	PROPERTY_ID LONG not null,
	YEAR_ID INT not null,
	MONTH_ID INT not null,
	VALUE DOUBLE,
	constraint COUNTER_DATA_pk primary key (ID)
)

CREATE TABLE COUNTER (
	ID INT auto_increment,
	NAME VARCHAR(100),
	SERIAL_NUMBER VARCHAR(100),
	constraint COUNTER_pk primary key (ID)
)

CREATE TABLE PROPERTY (
	ID INT auto_increment,
	COUNTRY VARCHAR(100) not null,
	CITY VARCHAR(100) not null,
	STREET VARCHAR(100) not null,
	HOUSE_NO VARCHAR(10) not null,
	FLAT INT,
	constraint PROPERTY_pk primary key (ID)
)

create table REF_COUNTRY (
    ID int auto_increment,
    NAME varchar(100) not null,
    constraint REF_COUNTRY_pk primary key (ID)
);