create database AirlineManagement;
use AirlineManagement;

CREATE TABLE Department(
   Department_ID int PRIMARY KEY     NOT NULL AUTO_INCREMENT,
   Department_Name           varchar(50)    NOT NULL,
   Creation_date datetime default CURRENT_TIMESTAMP
);

create table Airline(
NUMSRL int primary key AUTO_INCREMENT,
Airline_Type varchar(50) not null,
Airline_Name varchar(50) not null,
Rating varchar(50) not null,
status VARCHAR(10) not null default 'A',
Creation_Date datetime default CURRENT_TIMESTAMP
);

create table employee(
Employee_ID int primary key AUTO_INCREMENT,
First_Name varchar(50) not null,
Last_Name varchar(50) not null,
Date_of_joining date not null,
Phone_number varchar(50),
Address varchar(250) not null,
Salary decimal(10,2),
Rating varchar(50) not null,
status VARCHAR(10) not null default 'A',
Department_ID int not null,
	constraint fk_departmentid foreign key(department_id) references Department(Department_ID)
);

create table Flight( 
Flight_number int primary key AUTO_INCREMENT,
Arrival_Airport varchar(50) not null,
Departure_Airport varchar(50) not null,
Arrival_Time time not null,
Departure_Time time not null,
Pilot_ID int not null,
Plane_ID int not null,
status VARCHAR(10) not null default 'A',
Creation_Date datetime default CURRENT_TIMESTAMP,
 constraint fk_pilotid foreign key(Pilot_ID) references employee(Employee_ID),
 constraint fk_plaintid foreign key(Plane_ID) references Airline(NUMSRL)
 ); 

create table passenger( 
Passenger_ID int primary key AUTO_INCREMENT,
First_Name varchar(50) not null,
Last_Name varchar(50) not null,
Email varchar(50) not null,
Address varchar(50) not null,
Phone_Number varchar(50) not null,
status VARCHAR(10) not null default 'A',
Creation_Date datetime default CURRENT_TIMESTAMP,
 constraint unq_passenger_info unique (first_name,last_name,email)
);

create table booking(
Booking_ID int primary key AUTO_INCREMENT,
Flight_number int not null,
Passenger_ID int not null,
Booking_Date date not null,
status VARCHAR(10) not null default 'A',
Flight_Date date not null,
 constraint fk_flight_num foreign key(Flight_number) references Flight(Flight_number),
 constraint fk_passenger_num foreign key(Passenger_ID) references passenger(Passenger_ID)
 );
 
 
 
 
INSERT INTO airlinemanagement.department (Department_ID, Department_Name, Creation_date) VALUES(1, 'Marketing', '2022-11-02 23:58:24');
INSERT INTO airlinemanagement.department (Department_ID, Department_Name, Creation_date) VALUES(2, 'Planning', '2022-11-02 23:58:34');
INSERT INTO airlinemanagement.department (Department_ID, Department_Name, Creation_date) VALUES(3, 'Flight Operations', '2022-11-02 23:58:48');
INSERT INTO airlinemanagement.department (Department_ID, Department_Name, Creation_date) VALUES(4, 'Maintenance & Engineering', '2022-11-02 23:59:00');
INSERT INTO airlinemanagement.department (Department_ID, Department_Name, Creation_date) VALUES(5, 'Properties and Facilities/Airport Affairs', '2022-11-02 23:59:19');
INSERT INTO airlinemanagement.department (Department_ID, Department_Name, Creation_date) VALUES(6, 'In-Flight Services', '2022-11-02 23:59:40');
INSERT INTO airlinemanagement.department (Department_ID, Department_Name, Creation_date) VALUES(7, 'Flight Standards and Training', '2022-11-02 23:59:57');




INSERT INTO airlinemanagement.airline (NUMSRL, Airline_Type, Airline_Name, Rating, status, Creation_Date) VALUES(1, 'Passenger', 'Airbus A330', 'FSC', 'A', '2022-11-03 00:10:53');
INSERT INTO airlinemanagement.airline (NUMSRL, Airline_Type, Airline_Name, Rating, status, Creation_Date) VALUES(2, 'Passenger', 'Cessna 172', 'LCC', 'A', '2022-11-03 00:11:50');
INSERT INTO airlinemanagement.airline (NUMSRL, Airline_Type, Airline_Name, Rating, status, Creation_Date) VALUES(3, 'Passenger', 'Bombardier CRJ', 'FSC', 'A', '2022-11-03 00:13:05');
INSERT INTO airlinemanagement.airline (NUMSRL, Airline_Type, Airline_Name, Rating, status, Creation_Date) VALUES(4, 'Passenger', 'Boeing 737', 'FSC', 'A', '2022-11-03 00:13:36');
INSERT INTO airlinemanagement.airline (NUMSRL, Airline_Type, Airline_Name, Rating, status, Creation_Date) VALUES(5, 'Passenger', 'Airbus A320', 'FSC', 'A', '2022-11-03 00:14:00');
INSERT INTO airlinemanagement.airline (NUMSRL, Airline_Type, Airline_Name, Rating, status, Creation_Date) VALUES(6, 'Cargo', 'Antonov An-22 Antei', 'FSC', 'A', '2022-11-03 00:14:58');
INSERT INTO airlinemanagement.airline (NUMSRL, Airline_Type, Airline_Name, Rating, status, Creation_Date) VALUES(7, 'Cargo', 'Boeing 747-400ERF', 'FSC', 'A', '2022-11-03 00:15:17');
INSERT INTO airlinemanagement.airline (NUMSRL, Airline_Type, Airline_Name, Rating, status, Creation_Date) VALUES(8, 'Cargo', 'Boeing C-17 Globemaster III', 'FSC', 'A', '2022-11-03 00:15:38');
INSERT INTO airlinemanagement.airline (NUMSRL, Airline_Type, Airline_Name, Rating, status, Creation_Date) VALUES(9, 'Cargo', 'Lockheed C-130 Hercules', 'FSC', 'A', '2022-11-03 00:16:07');
INSERT INTO airlinemanagement.airline (NUMSRL, Airline_Type, Airline_Name, Rating, status, Creation_Date) VALUES(10, 'Passenger', 'Beechcraft King Air 200', 'LCC', 'A', '2022-11-03 00:17:23');
INSERT INTO airlinemanagement.airline (NUMSRL, Airline_Type, Airline_Name, Rating, status, Creation_Date) VALUES(11, 'Passenger', 'Embraer Phenom 100', 'LCC', 'A', '2022-11-03 00:17:53');


INSERT INTO airlinemanagement.employee (Employee_ID, First_Name, Last_Name, Date_of_joining, Phone_number, Address, Salary, Rating, status, Department_ID) VALUES(1, 'Vidya', 'Tiwari', '2023-03-08', '545214114', 'Manchester', 94644.45, '', 'A', 2);
INSERT INTO airlinemanagement.employee (Employee_ID, First_Name, Last_Name, Date_of_joining, Phone_number, Address, Salary, Rating, status, Department_ID) VALUES(2, 'Vidit', 'Tiwari', '2022-11-03', '34234', 'London', 3454345.00, 'FSC', 'A', 3);
INSERT INTO airlinemanagement.employee (Employee_ID, First_Name, Last_Name, Date_of_joining, Phone_number, Address, Salary, Rating, status, Department_ID) VALUES(3, 'Chandrabhushan', 'Tiwari', '2022-11-03', '545214114', 'Derby', 64644.45, '', 'A', 1);


INSERT INTO airlinemanagement.flight (Flight_number, Arrival_Airport, Departure_Airport, Arrival_Time, Departure_Time, Pilot_ID, Plane_ID, status, Creation_Date) VALUES(1, 'LHR', 'IGN', '07:30:00', '21:30:00', 2, 1, 'A', '2022-11-10 19:02:00');
INSERT INTO airlinemanagement.flight (Flight_number, Arrival_Airport, Departure_Airport, Arrival_Time, Departure_Time, Pilot_ID, Plane_ID, status, Creation_Date) VALUES(2, 'TGD', 'STN', '06:30:00', '10:15:00', 2, 1, 'A', '2022-11-10 19:04:57');
INSERT INTO airlinemanagement.flight (Flight_number, Arrival_Airport, Departure_Airport, Arrival_Time, Departure_Time, Pilot_ID, Plane_ID, status, Creation_Date) VALUES(3, 'LPL', 'BVA', '06:45:00', '09:05:00', 2, 1, 'A', '2022-11-10 19:07:04');
INSERT INTO airlinemanagement.flight (Flight_number, Arrival_Airport, Departure_Airport, Arrival_Time, Departure_Time, Pilot_ID, Plane_ID, status, Creation_Date) VALUES(4, 'MAN', 'CDG', '19:25:00', '21:55:00', 2, 1, 'A', '2022-11-10 19:08:27');


INSERT INTO airlinemanagement.passenger (Passenger_ID, First_Name, Last_Name, Email, Address, Phone_Number, status, Creation_Date) VALUES(1, 'Kings', 'Singh', 'kings@gmail.com', 'Nottingham', '5213623656', 'A', '2022-11-03 13:35:52');
INSERT INTO airlinemanagement.passenger (Passenger_ID, First_Name, Last_Name, Email, Address, Phone_Number, status, Creation_Date) VALUES(2, 'Nagesh', 'Mishra', 'nagesh@gmail.com', 'Playmouth', '253645364', 'A', '2022-11-03 13:35:15');
INSERT INTO airlinemanagement.passenger (Passenger_ID, First_Name, Last_Name, Email, Address, Phone_Number, status, Creation_Date) VALUES(3, 'Sandeep', 'Tiwari', 'sandeep@gmail.com', 'Exeter', '5213623656', 'A', '2022-11-03 13:36:46');



INSERT INTO airlinemanagement.booking (Booking_ID, Flight_number, Passenger_ID, Booking_Date, status, Flight_Date) VALUES(1, 1, 3, '2022-10-08', 'A', '2022-12-25');
INSERT INTO airlinemanagement.booking (Booking_ID, Flight_number, Passenger_ID, Booking_Date, status, Flight_Date) VALUES(2, 2, 2, '2022-09-22', 'A', '2022-12-11');
INSERT INTO airlinemanagement.booking (Booking_ID, Flight_number, Passenger_ID, Booking_Date, status, Flight_Date) VALUES(3, 3, 1, '2022-11-10', 'A', '2023-01-11');
INSERT INTO airlinemanagement.booking (Booking_ID, Flight_number, Passenger_ID, Booking_Date, status, Flight_Date) VALUES(4, 4, 3, '2022-12-26', 'A', '2023-03-03');
