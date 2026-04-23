package com.example.wishlist.service;

import com.example.wishlist.model.User;
import com.example.wishlist.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void createUser_returnsOne_whenInsertSucceeds() {
        User user = new User("alice", "pw", "AliceExample", "alice@mail.com");
        when(userRepository.createUser(user)).thenReturn(1);

        int result = userService.createUser(user);

        assertEquals(1, result);
        verify(userRepository).createUser(user);
    }

    @Test
    void createUser_throwsConflict_whenUserAlreadyExists() {
        User user = new User("alice", "pw", "AliceExample", "alice@mail.com");
        when(userRepository.createUser(user)).thenReturn(0);

        ResponseStatusException ex = assertThrows(
                ResponseStatusException.class,
                () -> userService.createUser(user)
        );
        assertEquals(409, ex.getStatusCode().value()); // CONFLICT
    }
}