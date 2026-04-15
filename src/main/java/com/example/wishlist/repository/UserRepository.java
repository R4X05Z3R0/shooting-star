package com.example.wishlist.repository;

import com.example.wishlist.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    private JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    /** Create User
     * Inserting User object information to SQL database
     * @param user
     */
    public void createUser(User user){
        String sql = "INSERT IGNORE INTO users (username, password, name, email) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,
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
        return jdbcTemplate.query(sql, (rs, rownum) -> {
            User user = new User();

            user.setUserId(rs.getInt("user_id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            return user;
        });
    }

    /**
     * Getting User by ID
     * @return single user object from SQL query
     */
    public User getUserByiD(int id){
        String sql = "SELECT * FROM users WHERE user_id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) ->{
            User user = new User();

            user.setUserId(rs.getInt("user_id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            return user;
        }, id);
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
     */
    public void deleteUser(int id){
        String sql = "DELETE FROM users WHERE user_id = ?";
        jdbcTemplate.update(sql, id);
    }
}
