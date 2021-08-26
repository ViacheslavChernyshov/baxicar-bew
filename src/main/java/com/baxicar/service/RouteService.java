package com.baxicar.service;

import com.baxicar.dto.RouteDto;
import com.baxicar.model.Route;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RouteService {

    Route save(Route route);

    List<RouteDto> getRoutesByUserId(Long userId);

    List<RouteDto> getRouteByUserId(Long userId);

    Long deleteRouteByRouteId(Long routeId);
}
