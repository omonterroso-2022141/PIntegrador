package com.example.demo;

import com.example.demo.controller.reservation.ReservationsController;
import com.example.demo.model.reservation.Reservation;
import com.example.demo.service.reservation.ReservationsService;
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

class ReservationsControllerTest {

    @InjectMocks
    private ReservationsController reservationsController;

    @Mock
    private ReservationsService reservationsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateReservation() {
        Reservation reservation = new Reservation();
        reservation.setId("1");
        when(reservationsService.createReservation(any(Reservation.class))).thenReturn(reservation);

        Reservation createdReservation = reservationsController.createReservation(reservation);

        assertNotNull(createdReservation);
        assertEquals("1", createdReservation.getId());
        verify(reservationsService, times(1)).createReservation(reservation);
    }

    @Test
    void testGetAllReservations() {
        Reservation reservation1 = new Reservation();
        Reservation reservation2 = new Reservation();
        when(reservationsService.getAllReservations()).thenReturn(Arrays.asList(reservation1, reservation2));

        List<Reservation> reservations = reservationsController.getAllReservations();

        assertNotNull(reservations);
        assertEquals(2, reservations.size());
        verify(reservationsService, times(1)).getAllReservations();
    }

    @Test
    void testGetReservationById() {
        Reservation reservation = new Reservation();
        reservation.setId("1");
        when(reservationsService.getReservationById("1")).thenReturn(reservation);

        Reservation foundReservation = reservationsController.getReservationById("1");

        assertNotNull(foundReservation);
        assertEquals("1", foundReservation.getId());
        verify(reservationsService, times(1)).getReservationById("1");
    }

    @Test
    void testGetReservationsByUserId() {
        Reservation reservation1 = new Reservation();
        Reservation reservation2 = new Reservation();
        when(reservationsService.getReservationsByUserId("user1")).thenReturn(Arrays.asList(reservation1, reservation2));

        List<Reservation> reservations = reservationsController.getReservationsByUserId("user1");

        assertNotNull(reservations);
        assertEquals(2, reservations.size());
        verify(reservationsService, times(1)).getReservationsByUserId("user1");
    }

    @Test
    void testUpdateReservation() {
        Reservation reservation = new Reservation();
        reservation.setId("1");
        when(reservationsService.updateReservation("1", reservation)).thenReturn(reservation);

        Reservation updatedReservation = reservationsController.updateReservation("1", reservation);

        assertNotNull(updatedReservation);
        assertEquals("1", updatedReservation.getId());
        verify(reservationsService, times(1)).updateReservation("1", reservation);
    }

    @Test
    void testDeleteReservation() {
        String reservationId = "1";
        doNothing().when(reservationsService).deleteReservation(reservationId);

        reservationsController.deleteReservation(reservationId);

        verify(reservationsService, times(1)).deleteReservation(reservationId);
    }
}
