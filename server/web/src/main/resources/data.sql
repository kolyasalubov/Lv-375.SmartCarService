INSERT INTO skills(name) VALUES('Service Technician');
INSERT INTO skills(name) VALUES('Diagnostic Technician');
INSERT INTO skills(name) VALUES('Transmission Technician');
INSERT INTO skills(name) VALUES('Brake Technician');
INSERT INTO skills(name) VALUES('Body Repair Technician');
INSERT INTO skills(name) VALUES('Vehicle Refinisher');
INSERT INTO skills(name) VALUES('Vehicle Inspector');


INSERT INTO role(name) VALUES('ROLE_CAR_OWNER');
INSERT INTO role(name) VALUES('ROLE_SALES_MANAGER');
INSERT INTO role(name) VALUES('ROLE_TECHNICAL_MANAGER');
INSERT INTO role(name) VALUES('ROLE_DIELER');
INSERT INTO role(name) VALUES('ROLE_WORKER');
INSERT INTO role(name) VALUES('ROLE_ADMIN');

INSERT INTO fault_code(fault_code, description, type, skill_id)
VALUES ("B1315", "Problem in Front passenger child seat recognition","Warning", 1),
("C1010","Battery Voltage Low","Warning",2),
("C1012","Battery Voltage High","Warning", 2),
("C1512", "Brakes Overheated","Warning", 4),
("P1791", "Transmission Range Display Circ. Short to Ground","Warning", 3),
("B1476", "Airbag malfunction indicator and warning lamp is defective","Warning",5),
("P1296", "Engine/Transmission Control Modules Versions do not match","Warning", 3),
("B1617", "Left/Right license plate lamp is defective","Warning", 5),
("P1659", "Brake Switch Circ. Error Message from Engine Contr.","Warning", 6),
("yearly-inspection", "Last overall inspection of your car was year ago. It is time to repeat it","Information", 7),
("mileage-inspection", "You have ridden more than 10 000 kilometers since last overall inspection of your car. It is time to visit your technical service", "Information", 7);

INSERT INTO fault_code(fault_code, description, type)
VALUES
("car_is_ready", "Your vehicle is ready to be picked up", "Information"),
("leave_review", "Are you satisfied with your last service?", "Information"),
("fuel", "Your car needs fuel. Time for a trip to the petrol station. Liters left: ", "Information"),
("glass washer fluid", "You are running out of glass washer fluid. 0.5l left. ", "Information"),
("coolant", "You are running out of coolant. But it is not critical yet. 0.5l left. ", "Information")
;