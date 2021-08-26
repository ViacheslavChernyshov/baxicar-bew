package com.baxicar.service;

import com.baxicar.dto.RouteDto;
import com.baxicar.model.Route;
import com.baxicar.repository.RouteRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;

    public RouteServiceImpl(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    @Override
    public Route save(Route route) {
        return routeRepository.saveAndFlush(route);
    }

    @Override
    public List<RouteDto> getRoutesByUserId(Long userId) {
        List<Route> routes = routeRepository.findByUserUserId(userId);

        List<RouteDto> routesDto = new ArrayList<>();
        for (Route route : routes) {
            route.getUser();
            RouteDto routeDto = route.toRouteDto();
            routesDto.add(routeDto);
        }
        return routesDto;
    }

    @Override
    public List<RouteDto> getRouteByUserId(Long userId) {
        return null;
    }

    @Override
    public Long deleteRouteByRouteId(Long routeId) {
        Long result = routeRepository.deleteByRouteId(routeId);
        return result;
    }
}
