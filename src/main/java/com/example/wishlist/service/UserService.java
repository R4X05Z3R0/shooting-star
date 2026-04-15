package com.example.wishlist.service;

import com.example.wishlist.model.User;
import com.example.wishlist.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService (UserRepository userRepository){
        this.userRepository = userRepository;
    }

    /**Create User
     * @param user
     * @return row affected ('1' for success, '0' for no changes)
     */
    public int createUser(User user){
        return userRepository.createUser(user);
    }

    /**
     * Gets all users
     * @return list of all users
     */
    public List<User> getAllUsers(){
        return userRepository.getAllUsers();
    }

    /**
     * Finding User by ID
     * @param id
     * @return User or Error 404
     */
    public User getUserById(int id){
        try {
            return userRepository.getUserById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found");
        }
    }

    /**
     * Updates User
     * @param id of account to update
     * @param user
     * @return number of rows affected ('1' for success, '0' for no changes)
     */
    public int updateUser(int id, User user){
        return userRepository.updateUser(id, user);
    }

    /**
     * Deletes user
     * @param id userId for deletion
     * @return rows affected ('1' for success, '0' for no changes)
     */
    public int deleteUser(int id){
        int rowCheck = userRepository.deleteUser(id);

        if (rowCheck == 0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found");
        }
        return rowCheck;
    }
}
