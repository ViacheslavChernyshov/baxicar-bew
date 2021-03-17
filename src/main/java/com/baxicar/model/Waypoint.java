package com.baxicar.model;

import com.baxicar.dto.WaypointDto;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Waypoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "waypoints_id")
    private Long waypointsId;

    private Double latitude;

    private Double longitude;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "route_id")
    private Route route;

    public WaypointDto toWaypoints() {

        WaypointDto waypointDto = new WaypointDto();
        waypointDto.setWaypointsId(waypointsId);
        waypointDto.setRouteId(getRoute().getRouteId());
        waypointDto.setLatitude(getLatitude());
        waypointDto.setLongitude(getLongitude());
        return waypointDto;
    }

}
