package com.baxicar.repository;

import com.baxicar.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface RouteRepository extends JpaRepository<Route, Long> {

    Route saveAndFlush(Route route);

    List<Route> findAllByUserUserId(Long userId);

    List<Route> findByUserUserId(Long userId);
}
