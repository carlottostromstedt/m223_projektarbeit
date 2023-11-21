INSERT INTO role (type)
VALUES 
('Member'),
('Administrator');

INSERT INTO application_user (firstname, lastname, email, username, password, role_id)
VALUES
('Luke', 'Skywalker', 'luke@jedi.com', 'skywalker', '$2a$12$tFC3Hqn/YDkmWYwdn5yaIeXlMcILj0E0hJI.PTBLX01OCPciHg6OG', 2),
('Treasure', 'Hunter', 'treasurehunter@email.com', 'treasureHunter', '$2a$12$fudMJx8ZZrXBx/QqmZwiqOWfykAC.4f453DwTl6YOBKSVX4hnHnfO', 1),
('Cyber', 'Ninja', 'cyberninja@email.com', 'cyberNinja', '$2a$12$xGkvxSE63B1a3fYdtZ5tQ.UFPdKz8JfS/WkcAw/nVg/7JkJTPj3m.', 1),
('Music', 'Maestro', 'musicmaestro@email.com', 'musicMaestro', '$2a$12$k5i98QmJz/P2Hn3CSnMWy.oiD2ZfjuLQgH2nX5U2x80t0qZCviYYi', 1),
('Wander', 'lust23', 'wander@email.com', 'wanderlust23', '$2a$12$CeOXyO34IF1Drz1Z3mf6Uex2vz6zpRZErQYe/ub3V2bpqo7KKCUaW', 1),
('Code', 'Wizard', 'codewizard@email.com', 'codeWizard', '$2a$12$csVljFjaV2aoW8attpAxc.GvaWIcOQ4SsWYk1GiEvxXNFuO9UYaCy', 1),
('Coffee', 'Addict', 'coffee@email.com', 'coffeeAddict', '$2a$12$VcC9pIWDMs6LjJqXdq2vc.Hm7nSKnsWyvLnoK/PB.HQU9IIlDNt7G', 1),
('Book', 'Worm', 'bookworm@email.com', 'bookworm', '$2a$12$VrYsEdJ3l.Gm7y/xCZx2Ze7aplwaTkhWnKrGjFvSHSaJj4RFYXoQS', 1),
('Garden', 'Guru', 'garden@email.com', 'gardenGuru', '$2a$12$ezcMACiDoqzdRnyzG1V9cOiZENv4DBz.SIHQGgwAXiy6rkViKZL5a', 1),
('Midnight', 'Rider', 'midnight@email.com', 'midnightRider', '$2a$12$1Qs6OGZB4ATwbg5f1Rxr2ulIpc2uawA2pGu6IRLENl1p1J4K/Vwye', 1);

-- INSERT INTO application_user (firstname, lastname, email, username, password, role_id)
-- VALUES
-- ('Luke', 'Skywalker', 'luke@jedi.com', 'skywalker', 'Force123', 2),
-- ('Treasure', 'Hunter', 'treasurehunter@email.com', 'treasureHunter', 'XmarkstheSpot!', 1),
-- ('Cyber', 'Ninja', 'cyberninja@email.com', 'cyberNinja', 'S3cur3P@ss', 1),
-- ('Music', 'Maestro', 'musicmaestro@email.com', 'musicMaestro', 'Melody123$', 1),
-- ('Wander', 'lust23', 'wander@email.com', 'wanderlust23', 'GlobeTrotter!', 1),
-- ('Code', 'Wizard', 'codewizard@email.com', 'codeWizard', 'MagicCode$', 1),
-- ('Coffee', 'Addict', 'coffee@email.com', 'coffeeAddict', 'Caf3in@te', 1),
-- ('Book', 'Worm', 'bookworm@email.com', 'bookworm', 'Read1234', 1),
-- ('Garden', 'Guru', 'garden@email.com', 'gardenGuru', 'Fl0w3rP0w3r!', 1),
-- ('Midnight', 'Rider', 'midnight@email.com', 'midnightRider', 'N1ghtOwl&', 1);



INSERT INTO meeting_room (roomname)
VALUES
('Alpha'),
('Bravo'),
('Charlie'),
('Delta');

INSERT INTO time_slot (type)
VALUES
('Morning'),
('Afternoon'),
('Whole-Day');

INSERT INTO nocco (name, caffeine)
VALUES
('Mango', 180),
('Limon del Sol', 180),
('Blood Orange', 180),
('Black Orange', 180);

INSERT INTO booking (bookingdate, state ,acceptancestate, meetingroom_id, nocco_id, timeslot_id)
VALUES
('2022-03-10', 'ACTIVE', 'PENDING', 1, 2, 1), 
('2022-03-10', 'ACTIVE', 'PENDING', 2, 3, 2),
('2022-03-12', 'ACTIVE', 'PENDING', 3, 4, 3),
('2022-03-13', 'ACTIVE', 'PENDING', 4, 1, 1);

INSERT INTO application_user_booking (application_user_id, booking_id)
VALUES
(1,2),
(2,2),
(3,1),
(4,3),
(1,4);
