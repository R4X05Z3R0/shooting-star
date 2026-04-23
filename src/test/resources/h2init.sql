-- Drop tables in reverse order of dependency to avoid constraint violations
DROP TABLE IF EXISTS wish;
DROP TABLE IF EXISTS wishlist;
DROP TABLE IF EXISTS users;

-- 2. SCHEMA DEFINITION
CREATE TABLE users (
                       user_id INT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       name VARCHAR(50) NOT NULL,
                       email VARCHAR(50) UNIQUE
);

CREATE TABLE wishlist (
                          wishlist_id INT AUTO_INCREMENT PRIMARY KEY,
                          title VARCHAR(50) NOT NULL,
                          user_id INT,
                          CONSTRAINT fk_wishlist_users
                              FOREIGN KEY (user_id)
                                  REFERENCES users(user_id)
                                  ON DELETE CASCADE
);

CREATE TABLE wish (
                      wish_id INT AUTO_INCREMENT PRIMARY KEY,
                      wish_title VARCHAR(50) NOT NULL,
                      price DECIMAL(10, 2),
                      description TEXT,
                      url VARCHAR(2048),
                      image_url VARCHAR(2048),
                      wishlist_id INT,
                      CONSTRAINT fk_wish_wishlist
                          FOREIGN KEY (wishlist_id)
                              REFERENCES wishlist(wishlist_id)
                              ON DELETE CASCADE
);

-- 3. DATA INSERTION
-- Inserting Users (IDs will be 1 through 9)
INSERT INTO users(username, password, name, email) VALUES
                                                       ('John_Carter08', '12345678', 'John Carter', 'john8@mars.com'),           -- ID: 1
                                                       ('Babayaga', 'pencil', 'John Wick', 'dog_lover@movie.com'),               -- ID: 2
                                                       ('IronMan_3000', 'i_am_ironman', 'Tony Stark', 'stark@starkindustries.com'), -- ID: 3
                                                       ('The_Chosen_One', 'expelliarmus', 'Harry Potter', 'h.potter@hogwarts.ac.uk'), -- ID: 4
                                                       ('Witcher_Geralt', 'tossacoin', 'Geralt of Rivia', 'geralt@kaermorhen.pl'), -- ID: 5
                                                       ('Frodo_B', 'myprecious1', 'Frodo Baggins', 'frodo@theshire.me'),         -- ID: 6
                                                       ('Sherlock_221B', 'elementary', 'Sherlock Holmes', 'consulting@detective.co.uk'), -- ID: 7
                                                       ('DaughterOfThanos', 'nebula123', 'Gamora', 'gamora@guardians.galaxy'),   -- ID: 8
                                                       ('Batman_DarkKnight', 'iambatman', 'Bruce Wayne', 'bwayne@waynetech.com'); -- ID: 9

-- Inserting Wishlists (Referencing User IDs)
INSERT INTO wishlist (title, user_id) VALUES
                                          ('Mars Fight Back', 1),          -- For John Carter
                                          ('Revenge Ideas', 2),            -- For John Wick
                                          ('Avengers Party Planning', 3),  -- For Tony Stark
                                          ('Potions Supplies', 4),         -- For Harry Potter
                                          ('Monster Hunting Gear', 5),     -- For Geralt
                                          ('Detective Tools', 7);          -- For Sherlock

-- Inserting Wishes (Referencing Wishlist IDs)
-- Note: Wishlist IDs are generated in the order inserted (1-6)
INSERT INTO wish (wish_title, price, description, url, image_url, wishlist_id) VALUES
                                                                                   ('Vibranium Shield', 999999.99, 'Ask Cap first', 'https://stark.com/shield', 'https://images.com/shield.png', 3),
                                                                                   ('Firebolt Broomstick', 1500.00, 'World-class racing broom', 'https://qualityquidditch.com', 'https://images.com/broom.jpg', 4),
                                                                                   ('Silver Sword', 3500.00, 'For monsters specifically', 'https://witchergear.com/silver', 'https://images.com/sword.jpg', 5),
                                                                                   ('Magnifying Glass', 45.50, 'High magnification for clues', 'https://london-optics.com', 'https://images.com/glass.jpg', 6);