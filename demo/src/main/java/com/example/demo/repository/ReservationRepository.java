package com.example.demo.repository;

import com.example.demo.model.reservation.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends MongoRepository<Reservation, String> {

    Optional<Reservation> findById(String id);

    List<Reservation> findByUserId(String userId);

}
