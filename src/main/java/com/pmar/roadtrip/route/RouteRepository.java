package com.pmar.roadtrip.route;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RouteRepository extends JpaRepository<Route,Long> {
    Optional<List<Route>> findAllByUserId(Long userId);

}
