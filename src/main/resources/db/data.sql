INSERT INTO users(username, password, name, email)
VALUES
("John_Carter08", "12345678", "John Carter", "john8@mars.com"),
("Babayaga", "pencil", "John Wick", "dog_lover@movie.com"),
("IronMan_3000", "i_am_ironman", "Tony Stark", "stark@starkindustries.com"),
("The_Chosen_One", "expelliarmus", "Harry Potter", "h.potter@hogwarts.ac.uk"),
("Witcher_Geralt", "tossacoin", "Geralt of Rivia", "geralt@kaermorhen.pl"),
("Frodo_B", "myprecious1", "Frodo Baggins", "frodo@theshire.me"),
("Sherlock_221B", "elementary", "Sherlock Holmes", "consulting@detective.co.uk"),
("DaughterOfThanos", "nebula123", "Gamora", "gamora@guardians.galaxy"),
("Batman_DarkKnight", "iambatman", "Bruce Wayne", "bwayne@waynetech.com");

INSERT INTO wishlist (title, user_id) VALUES 
("Mars Fight Back", 1),			 -- John Carter
("Revenge Ideas", 2),			 -- John Wick
("Avengers Party Planning", 3),  -- Tony Stark
("Potions Supplies", 4),         -- Harry Potter
("Monster Hunting Gear", 5),     -- Geralt
("Detective Tools", 7);          -- Sherlock

INSERT INTO wish (wish_title, price, description, url, image_url, wishlist_id) VALUES 
("Vibranium Shield", 999999.99, "Ask Cap first", "https://stark.com/shield", "https://images.com/shield.png", 3),
("Firebolt Broomstick", 1500.00, "World-class racing broom", "https://qualityquidditch.com", "https://images.com/broom.jpg", 4),
("Silver Sword", 3500.00, "For monsters specifically", "https://witchergear.com/silver", "https://images.com/sword.jpg", 5),
("Magnifying Glass", 45.50, "High magnification for clues", "https://london-optics.com", "https://images.com/glass.jpg", 6);
