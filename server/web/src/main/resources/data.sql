INSERT INTO skills(name) VALUES('Service MOT');
INSERT INTO skills(name) VALUES('Brake system');
INSERT INTO skills(name) VALUES('Suspension');
INSERT INTO skills(name) VALUES('Steering');
INSERT INTO skills(name) VALUES('Engine');
INSERT INTO skills(name) VALUES('Fuel system');
INSERT INTO skills(name) VALUES('Electrician');
INSERT INTO skills(name) VALUES('Exhaust system');
INSERT INTO skills(name) VALUES('Clutch');
INSERT INTO skills(name) VALUES('Transmission');


INSERT INTO role(name) VALUES('ROLE_CAR_OWNER');
INSERT INTO role(name) VALUES('ROLE_SALES_MANAGER');
INSERT INTO role(name) VALUES('ROLE_TECHNICAL_MANAGER');
INSERT INTO role(name) VALUES('ROLE_DIELER');
INSERT INTO role(name) VALUES('ROLE_WORKER');
INSERT INTO role(name) VALUES('ROLE_ADMIN');

INSERT INTO work_type(cost, name, required_time, skill_id)
VALUES
(140,'Engine oil change', 30, 1),
(150, 'Computer diagnostics', 60, 1),
(60, 'Replacing the engine air filter', 30, 1),
(80, 'Replacing the cabin air filter', 40, 1),
(250, 'Change oil in gearbox', 120, 1),
(100, 'Coolant replacement', 60, 1),
(200, 'Drive Roller Replacement', 100, 1),
(450, 'Initial vehicle inspection', 150, 1),
(280, 'Regular car inspection', 120, 1),
(150, 'Brake Diagnosis', 60, 2),
(200, 'Replacing the front brake pads', 120, 2),
(150, 'Replacing the rear brake pads', 90, 2),
(150, 'Brake fluid replacement', 60, 2),
(250, 'Replacing brake discs', 100, 2),
(150, 'Replacing brake drums', 80, 2),
(150, 'Brake hose replacement', 80, 2),
(280, 'Groove brake discs (with removal)', 120, 2),
(400, 'Replacement of a cable of a manual brake', 180, 2),
(60, 'Diagnostics of the vehicle chassis', 40, 3),
(350, 'Hub bearing replacement', 120, 3),
(650, 'Replacing front shock absorbers', 200, 3),
(400, 'Replacing rear shock absorbers', 150, 3),
(80, 'Ball joint replacement', 40, 3),
(450, 'Replacing stabilizer bush', 180, 3),
(150, 'Replacing stabilizer bar', 90, 3),
(600, 'Replacing suspension springs', 200, 3),
(100, 'Steering diagnostics', 30, 4),
(600, 'Replacing the steering rack and pump', 220, 4),
(400, 'Tie rod replacement', 150, 4),
(100, 'Steering Tip Replacement', 70, 4),
(200, 'Repair of electric power steering', 100, 4),
(300, 'Engine diagnostics', 120, 5),
(180, 'Water pump replacement', 90, 5),
(1000, 'Turbine Replacement', 300, 5),
(250, 'Thermostat replacement', 80, 5),
(370, 'Replacing the radiator cooling', 100, 5),
(1500, 'Replacing the cylinder block gasket (cylinder head)', 350, 5),
(600, 'Timing Chain Replacement', 150, 5),
(150, 'Fuel system diagnostics', 90, 6),
(200, 'Electrician diagnostics', 100, 7),
(200, 'Battery Diagnosis', 100, 7),
(400, 'Generator replacement', 150, 7),
(70, 'Battery Replacement', 20, 7),
(250, 'Starter replacement', 90, 7),
(200, 'Ignition Coil Replacement', 100, 7),
(100, 'Ignition switch replacement', 40, 7),
(150, 'Exhaust system diagnostics', 50, 8),
(120, 'Lambda probe replacement', 40, 8),
(250, 'Catalyst Replacement', 70, 8),
(1000, 'Replacing the particulate filter', 200, 8),
(200, 'Replacement muffler', 60, 8),
(250, 'Installation of flame arrester', 100, 8),
(60, 'Clutch Diagnosis', 30, 9),
(300, 'Clutch Master Cylinder Replacement', 100, 9),
(1200, 'Replacing dual-mass flywheel', 200, 9),
(90, 'Clutch cable adjustment', 20, 9),
(1200, 'Clutch Kit Replacement', 120, 9),
(200, 'Transmission Diagnosis', 50, 10),
(400, 'Repair of front axle gearbox', 100, 10),
(300, 'Repair rear axle', 80, 10),
(400, 'Replacing automatic transmission', 140, 10),
(500, 'Replacement Manual', 180, 10),
(150, 'Replacement of the semi-axis', 60, 10),
(200, 'Replacement CV Joints', 100, 10),
(150, 'Outboard bearing replacement', 50, 10);

INSERT INTO alerts(alert_code, description, alert_type, work_type_id)
VALUES ("B1315", "Problem in Front passenger child seat recognition","Warning", 2),
("C1010","Battery Voltage Low","Warning", 41),
("C1012","Battery Voltage High","Warning", 41),
("C1512", "Brakes Overheated","Warning", 10),
("P1791", "Transmission Range Display Circ. Short to Ground","Warning", 58),
("B1476", "Airbag malfunction indicator and warning lamp is defective","Warning", 2),
("P1296", "Engine/Transmission Control Modules Versions do not match","Warning", 58),
("B1617", "Left/Right license plate lamp is defective","Warning", 2),
("P1659", "Brake Switch Circ. Error Message from Engine Contr.","Warning", 15),
("yearly-inspection", "Last overall inspection of your car was year ago. It is time to repeat it","Information", 8),
("mileage-inspection", "You have ridden more than 10 000 kilometers since last overall inspection of your car. It is time to visit your technical service", "Information", 8);

INSERT INTO alerts(alert_code, description, alert_type)
VALUES
("car_is_ready", "Your vehicle is ready to be picked up", "Information"),
("leave_review", "Are you satisfied with your last service?", "Information"),
("fuel", "Your car needs fuel. Time for a trip to the petrol station. Liters left: ", "Information"),
("glass washer fluid", "You are running out of glass washer fluid. 0.5l left. ", "Information"),
("coolant", "You are running out of coolant. But it is not critical yet. 0.5l left. ", "Information")
;
INSERT INTO alerts(alert_code, description, suggestion, alert_type)
VALUES
("WL001", "You're not wearing your seatbelt",
"This doesn't require a checkup from a professional but it's quite possibly the most important aspect of driving. Seat belt use is one of the most effective ways to save lives and reduce injuries in crashes, yet millions do not buckle up on every trip. Safety first and put on your belt, buddy.",
"Seatbelt indicator"),
("WL002", "Your car's coolant is overheating",
"Quickly pull over and let your car cool down or the heat can destroy your car's engine.",
"Engine temperature warning light"),
("WL003", "It's likely that you left your handbrake on. But if the light is still lit when you are driving, it could mean your car is low on brake fluid or something more severe.",
"If the red brake light blinks, or comes on and off intermittently, and there is no change in the feel of the brake pedal, then have the vehicle properly diagnosed by a qualified repair shop as soon as possible.",
"Brake warning light");

