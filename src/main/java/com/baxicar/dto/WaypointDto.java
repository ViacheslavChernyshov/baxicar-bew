package com.baxicar.dto;

import com.baxicar.model.Route;
import com.baxicar.model.Waypoint;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class WaypointDto {

    private Long waypointsId;
    private Double latitude;
    private Double longitude;
    private Long routeId;

    public Waypoint toWaypoints() {

        Waypoint waypoint = new Waypoint();
        waypoint.setLatitude(getLatitude());
        waypoint.setLongitude(getLongitude());
        return waypoint;
    }
}
