package com.example.wishlist.repository;

import com.example.wishlist.model.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.wishlist.mapper.UserRowMapper;

import java.util.List;

@Repository
public class UserRepository {
    private JdbcTemplate jdbcTemplate;
    private final UserRowMapper userRowMapper;

    public UserRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
        this.userRowMapper = new UserRowMapper();
    }

    /**Create User
     * Inserts User object into User table
     * @param user
     * @return row affected ('1' for success, '0' for no changes)
     */
    public int createUser(User user){
        String sql = "INSERT IGNORE INTO users (username, password, name, email) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getEmail());
    }

    /**
     * Getting all users
     * @return List of User from user table
     */
    public List<User> getAllUsers(){
        String sql =  "SELECT * FROM users";
        return jdbcTemplate.query(sql, userRowMapper);
    }

    /**
     * Getting User by ID
     * @return single user object from SQL query
     */
    public User getUserById(int id){
        String sql = "SELECT * FROM users WHERE user_id = ?";
        return jdbcTemplate.queryForObject(sql, userRowMapper, id);
    }

    /**
     * Updates User object in User table by ID
     * @param id of account to update
     * @param user
     * @return number of rows affected ('1' for success, '0' for no changes)
     */
    public int updateUser(int id, User user){
        String sql = "UPDATE users SET username = ?, password = ?,  name = ?, email = ? " +
                    "WHERE user_id = ?";

        return jdbcTemplate.update(sql,
                user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getEmail(),
                id);
    }

    /**
     * Deletes user from User table
     * @param id userId for deletion
     * @return rows affected ('1' for success, '0' for no changes)
     */
    public int deleteUser(int id){
        String sql = "DELETE FROM users WHERE user_id = ?";
        return jdbcTemplate.update(sql, id);
    }

    /**
     * SQL script for login check
     * @param username username of user
     * @param password password of user
     * @return user from SQL table
     */
    public User userLogin(String username, String password){
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

        try{
            return jdbcTemplate.queryForObject(sql, userRowMapper, username, password);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }
}
