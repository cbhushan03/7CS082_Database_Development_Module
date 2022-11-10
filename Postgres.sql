CREATE DATABASE airlinemanagement;
use airlinemanagement;

CREATE SCHEMA airsystem;

CREATE TABLE airsystem.Department(
   Department_ID SERIAL PRIMARY KEY     NOT NULL,
   Department_Name           TEXT    NOT NULL,
   Creation_date timestamp default now()
);

create table airsystem.Airline(
NUMSRL serial primary key,
Airline_Type text not null,
Airline_Name text not null,
Rating text not null,
status text not null default 'A',
Creation_Date timestamp default now()
);

create table airsystem.employee(
Employee_ID serial primary key,
First_Name text not null,
Last_Name text not null,
Date_of_joining date not null,
Phone_number text,
Address text not null,
Salary NUMERIC(10,2),
Rating text not null,
status text not null default 'A',
Department_ID int not null,
	constraint fk_departmentid foreign key(department_id) references airsystem.Department(Department_ID)
);

create table airsystem.Flight( 
Flight_number serial primary key,
Arrival_Airport text not null,
Departure_Airport text not null,
Arrival_Time time not null,
Departure_Time time not null,
Pilot_ID int not null,
Plane_ID int not null,
status text not null default 'A',
Creation_Date timestamp default now(),
 constraint fk_pilotid foreign key(Pilot_ID) references airsystem.employee(Employee_ID),
 constraint fk_plaintid foreign key(Plane_ID) references airsystem.Airline(NUMSRL)
 ) ; 
 


create table airsystem.passenger( 
Passenger_ID serial primary key,
First_Name text not null,
Last_Name text not null,
Email text not null,
Address text not null,
Phone_Number text not null,
status text not null default 'A',
Creation_Date timestamp default now(),
 constraint unq_passenger_info unique (first_name,last_name,email)
);



create table airsystem.booking(
Booking_ID serial primary key,
Flight_number int not null,
Passenger_ID int not null,
Booking_Date date not null,
Flight_Date date not null,
status text not null default 'A',
 constraint fk_flight_num foreign key(Flight_number) references airsystem.Flight(Flight_number),
 constraint fk_passenger_num foreign key(Passenger_ID) references airsystem.passenger(Passenger_ID)
 );
 
 
INSERT INTO airsystem.department (Department_ID, Department_Name, Creation_date) VALUES(1, 'Marketing', '2022-11-02 23:58:24');
INSERT INTO airsystem.department (Department_ID, Department_Name, Creation_date) VALUES(2, 'Planning', '2022-11-02 23:58:34');
INSERT INTO airsystem.department (Department_ID, Department_Name, Creation_date) VALUES(3, 'Flight Operations', '2022-11-02 23:58:48');
INSERT INTO airsystem.department (Department_ID, Department_Name, Creation_date) VALUES(4, 'Maintenance & Engineering', '2022-11-02 23:59:00');
INSERT INTO airsystem.department (Department_ID, Department_Name, Creation_date) VALUES(5, 'Properties and Facilities/Airport Affairs', '2022-11-02 23:59:19');
INSERT INTO airsystem.department (Department_ID, Department_Name, Creation_date) VALUES(6, 'In-Flight Services', '2022-11-02 23:59:40');
INSERT INTO airsystem.department (Department_ID, Department_Name, Creation_date) VALUES(7, 'Flight Standards and Training', '2022-11-02 23:59:57');


INSERT INTO airsystem.airline (NUMSRL, Airline_Type, Airline_Name, Rating, status, Creation_Date) VALUES(1, 'Passenger', 'Airbus A330', 'FSC', 'A', '2022-11-03 00:10:53');
INSERT INTO airsystem.airline (NUMSRL, Airline_Type, Airline_Name, Rating, status, Creation_Date) VALUES(2, 'Passenger', 'Cessna 172', 'LCC', 'A', '2022-11-03 00:11:50');
INSERT INTO airsystem.airline (NUMSRL, Airline_Type, Airline_Name, Rating, status, Creation_Date) VALUES(3, 'Passenger', 'Bombardier CRJ', 'FSC', 'A', '2022-11-03 00:13:05');
INSERT INTO airsystem.airline (NUMSRL, Airline_Type, Airline_Name, Rating, status, Creation_Date) VALUES(4, 'Passenger', 'Boeing 737', 'FSC', 'A', '2022-11-03 00:13:36');
INSERT INTO airsystem.airline (NUMSRL, Airline_Type, Airline_Name, Rating, status, Creation_Date) VALUES(5, 'Passenger', 'Airbus A320', 'FSC', 'A', '2022-11-03 00:14:00');
INSERT INTO airsystem.airline (NUMSRL, Airline_Type, Airline_Name, Rating, status, Creation_Date) VALUES(6, 'Cargo', 'Antonov An-22 Antei', 'FSC', 'A', '2022-11-03 00:14:58');
INSERT INTO airsystem.airline (NUMSRL, Airline_Type, Airline_Name, Rating, status, Creation_Date) VALUES(7, 'Cargo', 'Boeing 747-400ERF', 'FSC', 'A', '2022-11-03 00:15:17');
INSERT INTO airsystem.airline (NUMSRL, Airline_Type, Airline_Name, Rating, status, Creation_Date) VALUES(8, 'Cargo', 'Boeing C-17 Globemaster III', 'FSC', 'A', '2022-11-03 00:15:38');
INSERT INTO airsystem.airline (NUMSRL, Airline_Type, Airline_Name, Rating, status, Creation_Date) VALUES(9, 'Cargo', 'Lockheed C-130 Hercules', 'FSC', 'A', '2022-11-03 00:16:07');
INSERT INTO airsystem.airline (NUMSRL, Airline_Type, Airline_Name, Rating, status, Creation_Date) VALUES(10, 'Passenger', 'Beechcraft King Air 200', 'LCC', 'A', '2022-11-03 00:17:23');
INSERT INTO airsystem.airline (NUMSRL, Airline_Type, Airline_Name, Rating, status, Creation_Date) VALUES(11, 'Passenger', 'Embraer Phenom 100', 'LCC', 'A', '2022-11-03 00:17:53');


INSERT INTO airsystem.employee (Employee_ID, First_Name, Last_Name, Date_of_joining, Phone_number, Address, Salary, Rating, status, Department_ID) VALUES(1, 'Vidya', 'Tiwari', '2023-03-08', '545214114', 'Manchester', 94644.45, '', 'A', 2);
INSERT INTO airsystem.employee (Employee_ID, First_Name, Last_Name, Date_of_joining, Phone_number, Address, Salary, Rating, status, Department_ID) VALUES(2, 'Vidit', 'Tiwari', '2022-11-03', '34234', 'London', 3454345.00, 'FSC', 'A', 3);
INSERT INTO airsystem.employee (Employee_ID, First_Name, Last_Name, Date_of_joining, Phone_number, Address, Salary, Rating, status, Department_ID) VALUES(3, 'Chandrabhushan', 'Tiwari', '2022-11-03', '545214114', 'Derby', 64644.45, '', 'A', 1);


INSERT INTO airsystem.flight (Flight_number, Arrival_Airport, Departure_Airport, Arrival_Time, Departure_Time, Pilot_ID, Plane_ID, status, Creation_Date) VALUES(1, 'LHR', 'IGN', '07:30:00', '21:30:00', 2, 1, 'A', '2022-11-10 19:02:00');
INSERT INTO airsystem.flight (Flight_number, Arrival_Airport, Departure_Airport, Arrival_Time, Departure_Time, Pilot_ID, Plane_ID, status, Creation_Date) VALUES(2, 'TGD', 'STN', '06:30:00', '10:15:00', 2, 1, 'A', '2022-11-10 19:04:57');
INSERT INTO airsystem.flight (Flight_number, Arrival_Airport, Departure_Airport, Arrival_Time, Departure_Time, Pilot_ID, Plane_ID, status, Creation_Date) VALUES(3, 'LPL', 'BVA', '06:45:00', '09:05:00', 2, 1, 'A', '2022-11-10 19:07:04');
INSERT INTO airsystem.flight (Flight_number, Arrival_Airport, Departure_Airport, Arrival_Time, Departure_Time, Pilot_ID, Plane_ID, status, Creation_Date) VALUES(4, 'MAN', 'CDG', '19:25:00', '21:55:00', 2, 1, 'A', '2022-11-10 19:08:27');


INSERT INTO airsystem.passenger (Passenger_ID, First_Name, Last_Name, Email, Address, Phone_Number, status, Creation_Date) VALUES(1, 'Kings', 'Singh', 'kings@gmail.com', 'Nottingham', '5213623656', 'A', '2022-11-03 13:35:52');
INSERT INTO airsystem.passenger (Passenger_ID, First_Name, Last_Name, Email, Address, Phone_Number, status, Creation_Date) VALUES(2, 'Nagesh', 'Mishra', 'nagesh@gmail.com', 'Playmouth', '253645364', 'A', '2022-11-03 13:35:15');
INSERT INTO airsystem.passenger (Passenger_ID, First_Name, Last_Name, Email, Address, Phone_Number, status, Creation_Date) VALUES(3, 'Sandeep', 'Tiwari', 'sandeep@gmail.com', 'Exeter', '5213623656', 'A', '2022-11-03 13:36:46');

INSERT INTO airsystem.booking (Booking_ID, Flight_number, Passenger_ID, Booking_Date, status, Flight_Date) VALUES(1, 1, 3, '2022-10-08', 'A', '2022-12-25');
INSERT INTO airsystem.booking (Booking_ID, Flight_number, Passenger_ID, Booking_Date, status, Flight_Date) VALUES(2, 2, 2, '2022-09-22', 'A', '2022-12-11');
INSERT INTO airsystem.booking (Booking_ID, Flight_number, Passenger_ID, Booking_Date, status, Flight_Date) VALUES(3, 3, 1, '2022-11-10', 'A', '2023-01-11');
INSERT INTO airsystem.booking (Booking_ID, Flight_number, Passenger_ID, Booking_Date, status, Flight_Date) VALUES(4, 4, 3, '2022-12-26', 'A', '2023-03-03');
