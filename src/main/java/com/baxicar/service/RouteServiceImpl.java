package com.baxicar.service;

import com.baxicar.dto.RouteDto;
import com.baxicar.model.Route;
import com.baxicar.repository.RouteRepository;
import org.springframework.data.annotation.Persistent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collections;
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

//    @Override
//    public List<Route> getRoutesByDriverId(Long userId) {
//        System.out.println(routeRepository.findAllByIdAndRouteType(Collections.singleton(userId)));
//        return routeRepository.findAllById(Collections.singleton(userId));
//    }

//    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
//        User user = userDao.findByUsername(userId);
//        if (user == null) {
//            throw new UsernameNotFoundException("Invalid username or password.");
//        }
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
//    }
//
//
//    private Set<SimpleGrantedAuthority> getAuthority(User user) {
//        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
//        user.getRoles().forEach(role -> {
//            //authorities.add(new SimpleGrantedAuthority(role.getName()));
//            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
//        });
//        return authorities;
//    }
//
//    public List<User> findAll() {
//        List<User> list = new ArrayList<>();
//        userDao.findAll().iterator().forEachRemaining(list::add);
//        return list;
//    }
//
//    @Override
//    public User findOne(long id) {
//        return userDao.findById(id).get();
//    }
//
//    @Override
//    public void delete(long id) {
//        userDao.deleteById(id);
//    }
//
//    @Override
//    public User save(User user) {
//        return userDao.save(user);
//    }
}
