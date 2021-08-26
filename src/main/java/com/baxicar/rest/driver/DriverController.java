package com.baxicar.rest.driver;

import com.baxicar.dto.RouteDto;
import com.baxicar.model.Route;
import com.baxicar.model.RouteType;
import com.baxicar.model.User;
import com.baxicar.security.JwtTokenProvider;
import com.baxicar.service.RouteService;
import com.baxicar.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/driver")
@CrossOrigin(origins = "http://localhost:4200")
public class DriverController {

    private final UserService userService;
    private final RouteService routeService;
    private final JwtTokenProvider jwtTokenProvider;

    public DriverController(UserService userService, RouteService routeService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.routeService = routeService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    //@PreAuthorize("hasRole('DRIVER') or hasRole('ADMIN')")
    @PreAuthorize("hasAuthority('users:write')")
    @PostMapping("/route")
    public ResponseEntity<?> addDriverRoute(@RequestBody RouteDto routeDto, ServletRequest servletRequest) {
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) servletRequest);
        Long tokenUserid = Long.valueOf(jwtTokenProvider.getUserId(token));
        if (tokenUserid != null && tokenUserid.equals(routeDto.getUserId())) {
            System.out.println("USER_ID: - " + jwtTokenProvider.getUserId(token));
            Long userid = routeDto.getUserId();
            User user = userService.findByUserId(userid);
            Route route = routeDto.toRoute();
            route.setUser(user);
            route.setRouteType(RouteType.D);
            routeService.save(route);
            return new ResponseEntity<>("Save successes", HttpStatus.OK);
        }
        return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasAuthority('users:write')")
    @GetMapping("/routes/{id}")
    public ResponseEntity<?> getRoutesByDriverId(@PathVariable("id") Long id) {
        List<RouteDto> routes = routeService.getRoutesByUserId(id);

        Map<Object, Object> response = new HashMap<>();
        response.put("routes", routes);
        return ResponseEntity.ok(routes);
    }

    @PreAuthorize("hasAuthority('users:write')")
    @DeleteMapping("/route/{id}")
    @Transactional
    public ResponseEntity<?> deleteRouteByRouteId(@PathVariable("id") Long id, ServletRequest servletRequest) {
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) servletRequest);
        Long tokenUserid = Long.valueOf(jwtTokenProvider.getUserId(token));
        if (tokenUserid != null && id != null) {
            Long result = routeService.deleteRouteByRouteId(id);
            if (result == 0) {
                return ResponseEntity.ok(HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(HttpStatus.OK);
        } else return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);
    }
}
