-- var password = username;
INSERT INTO _user(email, username, password, first_name, last_name, role, is_account_locked) VALUES
    ('nikolicveljko01@gmail.com', 'veljko', '$2a$10$kVZ6MkAdppKk5GtbzjsgbubBx1Uos0EOTdiY9mIBA5XwWy1ezVQam', 'Veljko', 'Nikolić', 0, false),
    ('vule.dok@gmail.com', 'dokma', '$2a$10$jicdgzoNyODhfwtEuXsYHelIRjzFZ5zIgqcXlHfoy9BROPAnWV2y2', 'Vukašin', 'Dokmanović', 0, false),
    ('spale01@gmail.com', 'spale', '$2a$10$NhX4GkvqpZTas4mZa9wxuevS.8VCi70t3tKXVekdD5/VCg2Hf.kAu', 'Spasoje', 'Brborić', 0, false),
    ('kuzminacn@gmail.com', 'nina', '$2a$10$XxmC.cIEK6XvYUVOa2HvTOlMQlIk0J6oQYouW2iBdgob.sRv78W9C', 'Nina', 'Kuzminac', 0, false),
    ('admin@gmail.com', 'admin', '$2a$12$zSJwNzNr/uFw.a9H5YRus.gQhZ.hjaNxhads7618x/U8KTFCPUnde', 'Admin', 'Adminovski', 4, false),
    ('organizer@gmail.com', 'organizer', '$2a$10$KVAfGs.glFRaekHlH3OWaeUASPrUMoh7MBuNu8DYT/SzRnvD9ZjL6', 'Organizator', 'Organizatorović', 1, false),
    ('curator@gmail.com', 'curator', '$2a$10$JGckcPY5eO8/1.xE7h7KmuH0A4gNif6PDSxBzUbxsThjQTTu.Nyai', 'Kustos', 'Kustosović', 2, false),
    ('restaurateur@gmail.com', 'restaurateur', '$2a$10$tdoK34QObn2cUrANXLUzveWSSdkMuaT/EkN4RCzqmIjaAH0ze8PNW', 'Restaurator', 'Restaruatorović', 3, false);

INSERT INTO guest(id, biography) VALUES
    (1, 'I am passionate about ancient civilizations, always seeking to uncover the mysteries of the past within museum walls.'),
    (2, 'I am interested in the captivating world of ancient civilizations and the rich tapestry of mythologies they cherished.'),
    (3, 'I am passionate about unraveling ancient civilizations and love exploring museums for insights.'),
    (4, 'Dedicated to preserving cultural heritage, advocating for the importance of museums in safeguarding our collective history.');

INSERT INTO organizer(id, biography) VALUES
    (6, 'I love organizing stuff.');

INSERT INTO exhibition
(name, picture, short_description, long_description, theme, start_date, end_date, price, organizer_id)
VALUES
    ('Ancient Civilizations Unveiled',
     'https://cdn.pixabay.com/photo/2024/03/27/01/21/ai-generated-8657978_640.png',
     'Discover the secrets of ancient civilizations',
     'Unearth the legacies of ancient societies through immersive exhibits and authentic artifacts that tell a story of innovation and discovery.',
     'ANCIENT_HISTORY', '2024-04-01', '2024-04-30', 10, 6);

INSERT INTO exhibition
(name, picture, short_description, long_description, theme, start_date, end_date, price, organizer_id)
VALUES
    ('Medieval Treasures',
     'https://cdn.pixabay.com/photo/2024/04/02/02/24/ai-generated-8669925_640.png',
     'Explore the medieval era',
     'Delve into the Middle Ages with treasures that reveal the daily life, art, and the grandeur of a time cloaked in both darkness and enlightenment.',
     'MEDIEVAL_HISTORY', '2024-05-15', '2024-06-15', 12, 6);

INSERT INTO exhibition
(name, picture, short_description, long_description, theme, start_date, end_date, price, organizer_id)
VALUES
    ('Military History: Triumphs and Tragedies',
     'https://cdn.pixabay.com/photo/2020/04/19/17/44/war-5064697_640.jpg',
     'Explore the triumphs and tragedies of military history',
     'Explore pivotal moments of military history, showcasing the tactics, artifacts, and personal stories that echo through time.',
     'MILITARY_HISTORY', '2024-09-01', '2024-09-30', 12, 6);
