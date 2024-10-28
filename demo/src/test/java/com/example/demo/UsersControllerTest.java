package com.example.demo;

import com.example.demo.controller.user.UsersController;
import com.example.demo.model.user.User;
import com.example.demo.service.user.UsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UsersControllerTest {

    @InjectMocks
    private UsersController usersController;

    @Mock
    private UsersService usersService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser() {
        User user = new User();
        user.setId("1");
        when(usersService.createUser(any(User.class))).thenReturn(user);

        ResponseEntity<User> response = usersController.createUser(user);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("1", response.getBody().getId());
        verify(usersService, times(1)).createUser(user);
    }

    @Test
    void testGetAllUsers() {
        User user1 = new User();
        User user2 = new User();
        when(usersService.getAllUsers()).thenReturn(Arrays.asList(user1, user2));

        ResponseEntity<List<User>> response = usersController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        verify(usersService, times(1)).getAllUsers();
    }

    @Test
    void testGetUserById() {
        User user = new User();
        user.setId("1");
        when(usersService.getUserById("1")).thenReturn(user);

        ResponseEntity<User> response = usersController.getUserById("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("1", response.getBody().getId());
        verify(usersService, times(1)).getUserById("1");
    }

    @Test
    void testGetUserByIdNotFound() {
        when(usersService.getUserById("1")).thenReturn(null);

        ResponseEntity<User> response = usersController.getUserById("1");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(usersService, times(1)).getUserById("1");
    }

    @Test
    void testUpdateUser() {
        User user = new User();
        user.setId("1");
        when(usersService.updateUser("1", user)).thenReturn(user);

        ResponseEntity<User> response = usersController.updateUser("1", user);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("1", response.getBody().getId());
        verify(usersService, times(1)).updateUser("1", user);
    }

    @Test
    void testUpdateUserNotFound() {
        User user = new User();
        user.setId("1");
        when(usersService.updateUser("1", user)).thenReturn(null);

        ResponseEntity<User> response = usersController.updateUser("1", user);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(usersService, times(1)).updateUser("1", user);
    }

    @Test
    void testDeleteUser() {
        String userId = "1";
        doNothing().when(usersService).deleteUser(userId);

        ResponseEntity<Void> response = usersController.deleteUser(userId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(usersService, times(1)).deleteUser(userId);
    }
}
