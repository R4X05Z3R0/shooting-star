CREATE DATABASE IF NOT EXISTS wishlist;
USE wishlist;

DROP TABLE IF EXISTS wish;
DROP TABLE IF EXISTS wishlist;
DROP TABLE IF EXISTS users;

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
                          CONSTRAINT fk_users
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
                      CONSTRAINT fk_wishlist
                          FOREIGN KEY (wishlist_id)
                              REFERENCES wishlist(wishlist_id)
                              ON DELETE CASCADE
);