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
    //List<Route> findAllByIdAndRouteType(Long driverId, String "D");


//    List<User> findAll();
//    User findOne(long id);
//    void delete(long id);
}
