package com.baxicar.dto;

import com.baxicar.model.Route;
import com.baxicar.model.RouteType;
import com.baxicar.model.User;
import com.baxicar.model.Waypoint;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class RouteDto {

    private Long routeId;
    private Long userId;
    private RouteType routeTypeId;
    private String startAddress;
    private String endAddress;
    private Double originLatitude;
    private Double originLongitude;
    private Double destinationLatitude;
    private Double destinationLongitude;
    private List<WaypointDto> waypoints;

    public Route toRoute() {

        Route route = new Route();
        route.setStartAddress(getStartAddress());
        route.setEndAddress(getEndAddress());
        route.setOriginLatitude(getOriginLatitude());
        route.setOriginLongitude(getOriginLongitude());
        route.setDestinationLatitude(getDestinationLatitude());
        route.setDestinationLongitude(getDestinationLongitude());

        List<Waypoint> waypointsEntities = new ArrayList<>();
        for (WaypointDto waypointDto : waypoints) {
            Waypoint waypointsEntity = waypointDto.toWaypoints();
            waypointsEntities.add(waypointsEntity);
        }

        route.setWaypoints(waypointsEntities);
        return route;
    }


}
