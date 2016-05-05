#===============================================
# Delete all the database records to prepare it for example insertion.
DELETE FROM users WHERE id = id;
DELETE FROM events_ WHERE id = id;
DELETE FROM userevents WHERE id = id;
DELETE FROM cats_fit WHERE id = id;
DELETE FROM cats_impr WHERE id = id;
DELETE FROM cats_event WHERE id = id;
#===============================================
# Fill the database with example data.
INSERT INTO cats_fit (id, `Name`, Description) VALUES (1, "Muscles", "Improves muscle areas of the body.");
INSERT INTO cats_fit (id, `Name`, Description) VALUES (2, "Ends", "Improves hands etc.");
INSERT INTO cats_fit (id, `Name`, Description) VALUES (3, "Power & Endurance", "Improves body capacity for performing tasks.");
INSERT INTO cats_impr (id, id_fit, `Name`, Description) VALUES (1, 1, "Muscles, Arm","Improves lifting performance.");
INSERT INTO cats_impr (id, id_fit, `Name`, Description) VALUES (2, 1, "Muscles, Leg","Improves stride power (endurance).");
INSERT INTO cats_impr (id, id_fit, `Name`, Description) VALUES (3, 3, "Caridovascular","Improves heart heath through excecise.");
INSERT INTO cats_impr (id, id_fit, `Name`, Description) VALUES (4, 1, "Abdominal Muscles","I have no idea what this does.");
INSERT INTO cats_impr (id, id_fit, `Name`, Description) VALUES (5, 2, "Hands, Power","Improves grip strength.");
INSERT INTO cats_impr (id, id_fit, `Name`, Description) VALUES (6, 2, "Fingers","12345 Helps Typing Passwords.");
INSERT INTO cats_event (id, id_fit, `Name`, Description, Tags) VALUES (1, 1, "Wieghtlifting", "Lifting of various kinds.", "Weights|Dumbells|lbs");
INSERT INTO cats_event (id, id_fit, `Name`, Description, Tags) VALUES (2, 1, "Movement excecises.", "...", "Flexibility|Muscles|Etc");
INSERT INTO cats_event (id, id_fit, `Name`, Description, Tags) VALUES (3, 2, "Typist's Challenges", "Try your hand at extreme typing (as if!!)", "Keyboards|Mice|Flexible_Fingers");
INSERT INTO cats_event (id, id_fit, `Name`, Description, Tags) VALUES (4, 2, "Slip N Grip", "Various pulling related excecises.", "Weights|Pull|Grab");
INSERT INTO cats_event (id, id_fit, `Name`, Description, Tags) VALUES (5, 3, "Heart Healthy", "Excecises meant to improve heart health.", "Heart|Endurance|<3");
INSERT INTO cats_event (id, id_fit, `Name`, Description, Tags) VALUES (6, 3, "Lung Excercises", "Various roundabout ways of improving breathing.", "Lungs|Oxygen_Intake|Endurance");
INSERT INTO users (id, Username, LoginAuthDB) VALUES (1, "Jhon Doe", MD5(RAND()));
INSERT INTO users (id, Username, LoginAuthDB) VALUES (2, "Jane Doe", MD5(RAND()));
INSERT INTO users (id, Username, LoginAuthDB) VALUES (3, "Dr. Deqan Delinquist", MD5(RAND()));
INSERT INTO users (id, Username, LoginAuthDB) VALUES (4, "Fake Oracle America Rep.", MD5(RAND()));
INSERT INTO users (id, Username, LoginAuthDB) VALUES (5, "Quartztet Qulaervich", MD5(RAND()));
INSERT INTO events_ (id, id_cat, `Name`, Description, CostCents) VALUES (1, 1, "Event #0", "Junk data...", 123);
INSERT INTO events_ (id, id_cat, `Name`, Description, CostCents) VALUES (2, 5, "Event #1", "Junk data dfghbdrst...", 234);
INSERT INTO events_ (id, id_cat, `Name`, Description, CostCents) VALUES (3, 3, "Event #2", "adryhsxtfhbta...", 345);
INSERT INTO events_ (id, id_cat, `Name`, Description, CostCents) VALUES (4, 1, "Event #3", "Juszdrtbyzdthbfghfgha...", 456);
INSERT INTO events_ (id, id_cat, `Name`, Description, CostCents) VALUES (5, 2, "Event #4", "Aced Acrobatics...", 777);
INSERT INTO events_ (id, id_cat, `Name`, Description, CostCents) VALUES (6, 6, "Event #5", "74785675667...", 369);
INSERT INTO events_ (id, id_cat, `Name`, Description, CostCents) VALUES (7, 1, "Event #6", "Testingonetwothree...", 1111);
INSERT INTO events_ (id, id_cat, `Name`, Description, CostCents) VALUES (8, 4, "Event #7", "BigExcercise...", 4444);
INSERT INTO events_ (id, id_cat, `Name`, Description, CostCents) VALUES (9, 1, "Event #8", "Pascal's Plethora...", 12421);
INSERT INTO events_ (id, id_cat, `Name`, Description, CostCents) VALUES (10,3, "Event #9", "Failed Doomsday", 2012);
INSERT INTO userevents (id, id_user, id_event, DefinedName) VALUES (1, 5, 8, "Quartz Task #0");
INSERT INTO userevents (id, id_user, id_event, DefinedName) VALUES (2, 5, 9, "Quartz Task #1");
INSERT INTO userevents (id, id_user, id_event, DefinedName) VALUES (3, 5, 5, "Quartz Task #2");
INSERT INTO userevents (id, id_user, id_event, DefinedName) VALUES (4, 3, 7, "myEventNofification");
INSERT INTO userevents (id, id_user, id_event, DefinedName) VALUES (5, 2, 2, "Yoga Class");
INSERT INTO userevents (id, id_user, id_event, DefinedName) VALUES (6, 1, 1, "DUMBELLS");
