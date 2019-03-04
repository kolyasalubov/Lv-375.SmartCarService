INSERT INTO skills(name)
VALUES('Service MOT'),
      ('Brake system'),
      ('Suspension'),
      ('Steering'),
      ('Engine'),
      ('Fuel system'),
      ('Electrician'),
      ('Exhaust system'),
      ('Clutch'),
      ('Transmission');


INSERT INTO role(name)
VALUES('ROLE_CAR_OWNER'),
      ('ROLE_SALES_MANAGER'),
      ('ROLE_TECHNICAL_MANAGER'),
      ('ROLE_DIELER'),
      ('ROLE_WORKER'),
      ('ROLE_ADMIN');

INSERT INTO work_type(cost, name, required_time, skill_id)
VALUES
(140,'Engine oil change', 30, 1),
(1500, 'Vehicle inspection', 300, 1),
(60, 'Replacing the engine air filter', 30, 1),
(80, 'Replacing the cabin air filter', 40, 1),
(100, 'Coolant replacement', 60, 1),
(200, 'Drive Roller Replacement', 100, 1),
(450, 'Initial vehicle inspection', 150, 1),
(280, 'Regular car inspection', 120, 1),
(150, 'Brake Diagnosis', 60, 2),
(200, 'Replacing the front brake pads', 120, 2),
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
(150, 'Outboard bearing replacement', 50, 10);


INSERT INTO work_dependency(main_work, dependent_work)
VALUES
(17, 2), (30, 18), (17, 54), (56, 5), (21, 14), (42, 41), (13, 37), (51, 20), (54, 21), (57, 48),
(32, 13), (35, 44), (33, 57), (4, 53), (49, 23), (54, 25), (58, 31), (55, 51), (41, 30), (8, 44),
(53, 19), (52, 41), (40, 60), (45, 12), (16, 11), (18, 25), (22, 12), (36, 25), (16, 20), (50, 19),
(9, 21), (51, 30), (47, 18), (42, 8), (43, 26), (3, 26), (30, 3), (9, 23), (19, 36), (26, 22),
(52, 10), (60, 29), (24, 45), (36, 18), (36, 47), (50, 58), (58, 26), (15, 16), (17, 24), (11, 52),
(11, 46), (27, 36), (25, 49), (50, 20), (33, 35), (38, 57), (60, 53), (15, 5), (59, 52), (31, 18),
(13, 55), (22, 59), (45, 43), (19, 49), (10, 39), (37, 60), (36, 2), (9, 44), (33, 22), (52, 19),
(6, 24), (38, 7), (38, 33), (22, 28), (40, 41), (31, 36), (9, 55), (14, 40), (56, 41), (36, 24),
(44, 6), (59, 27), (26, 31), (51, 42), (10, 3), (48, 53), (36, 45), (16, 1), (34, 59), (7, 28),
(50, 31), (32, 35), (13, 24), (21, 32), (59, 58), (53, 52), (36, 42), (45, 7), (19, 51), (6, 5),
(1, 17), (4, 13), (12, 9), (28, 1), (45, 58), (17, 4), (51, 60), (55, 42), (40, 30), (33, 52),
(43, 18), (7, 41), (42, 14), (11, 3), (49, 47), (48, 49), (34, 16), (30, 5), (32, 25), (46, 7),
(51, 49), (27, 18), (9, 33), (25, 50), (3, 15), (45, 19), (53, 51), (40, 50), (24, 24), (42, 2),
(12, 18), (36, 5), (31, 59), (56, 13), (53, 40), (16, 41), (7, 2), (1, 21), (52, 2), (22, 46),
(25, 8), (17, 50), (54, 39), (53, 18), (6, 20), (30, 13), (36, 55), (52, 30), (38, 22), (19, 52),
(37, 19), (44, 25), (28, 28), (31, 53), (31, 45), (7, 60), (44, 56), (31, 52), (59, 25), (49, 33),
(52, 10), (36, 15), (25, 15), (38, 26), (42, 51), (54, 31), (52, 36), (2, 33), (52, 33), (10, 45),
(60, 36), (46, 45), (27, 53), (41, 46), (27, 31), (16, 26), (23, 60), (52, 21), (53, 50), (3, 45),
(50, 3), (41, 4), (29, 5), (42, 16), (49, 50), (39, 45), (51, 31), (8, 12), (30, 58), (59, 30),
(14, 12), (1, 12), (14, 3), (32, 55), (57, 11), (51, 37), (4, 42), (12, 2), (14, 36), (53, 30),
(18, 47), (8, 49), (44, 10), (51, 3), (24, 31), (1, 32), (45, 11), (21, 54), (19, 1), (49, 16),
(26, 7), (15, 41), (2, 60), (26, 7), (9, 24), (28, 49), (50, 48), (39, 19), (14, 54), (57, 17),
(41, 8), (16, 2), (53, 18), (50, 55), (35, 49), (54, 31), (24, 18), (60, 30), (2, 54), (9, 2),
(21, 28), (27, 37), (48, 32), (31, 44), (45, 58), (29, 9), (30, 40), (4, 16), (24, 38), (19, 49),
(59, 29), (59, 56), (43, 9), (40, 8), (20, 1), (17, 34), (50, 13), (34, 39), (2, 23), (14, 29),
(51, 53), (29, 30), (36, 39), (40, 21), (24, 37), (11, 27), (42, 16), (51, 24), (59, 47), (17, 4),
(40, 29), (41, 51), (47, 19), (15, 42), (40, 20), (28, 5), (43, 13), (9, 15), (25, 54), (33, 3),
(12, 42), (53, 15), (39, 5), (24, 6), (40, 23), (9, 22), (1, 13), (9, 54), (55, 52), (39, 31),
(16, 56), (24, 25), (55, 27), (22, 12), (29, 18), (6, 7), (21, 59), (41, 51), (5, 12), (41, 35),
(23, 48), (51, 3), (41, 23), (36, 28), (12, 44), (9, 2), (7, 38), (12, 28), (20, 14), (19, 60),
(9, 59), (26, 34), (60, 15), (40, 53), (41, 8), (59, 56), (40, 20), (41, 44), (29, 8), (38, 3),
(49, 12), (53, 53), (48, 15), (52, 4), (32, 47), (29, 28), (24, 36), (29, 27), (22, 16), (2, 6),
(43, 44), (17, 19), (41, 48), (8, 33), (22, 32), (8, 14), (16, 53), (43, 60), (7, 51), (17, 9),
(8, 36), (51, 5), (2, 58), (24, 45), (39, 16), (36, 39), (29, 45), (54, 42), (28, 52), (45, 17),
(6, 52), (23, 12), (54, 47), (22, 42), (4, 43), (28, 56), (38, 24), (19, 47), (6, 56), (9, 32),
(59, 39), (32, 46), (4, 21), (27, 47), (15, 37), (13, 44), (25, 2), (13, 31), (33, 45), (42, 45),
(19, 39), (9, 44), (24, 19), (27, 55), (30, 24), (18, 27), (51, 3), (24, 26), (19, 6), (58, 23),
(3, 20), (43, 26), (10, 19), (32, 16), (27, 2), (31, 58), (41, 32), (32, 38), (52, 20), (22, 37),
(58, 44), (14, 18), (35, 37), (24, 40), (59, 6), (22, 44), (39, 36), (24, 29), (55, 19), (1, 17),
(27, 14), (20, 49), (60, 3), (24, 48), (8, 58), (60, 16), (7, 23), (26, 5), (9, 25), (53, 41);

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


