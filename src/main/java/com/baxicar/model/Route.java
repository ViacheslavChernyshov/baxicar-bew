package com.baxicar.model;

import com.baxicar.dto.RouteDto;
import com.baxicar.dto.WaypointDto;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "routes")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "route_id")
    private Long routeId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "route_type")
    private RouteType routeType;

    private String startAddress;

    private String endAddress;

    private Double originLatitude;

    private Double originLongitude;

    private Double destinationLatitude;

    private Double destinationLongitude;

    @CreationTimestamp
    private LocalDateTime crDate;

    @UpdateTimestamp
    private LocalDateTime lmDate;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Waypoint> waypoints;

    public void setWaypoints(List<Waypoint> waypoint) {
        if (waypoint != null) {
            waypoint.forEach(a -> {
                a.setRoute(this);
            });
        }
        this.waypoints = waypoint;
    }

    public RouteDto toRouteDto() {

        RouteDto routeDto = new RouteDto();
        routeDto.setRouteId(getRouteId());
        routeDto.setUserId(getUser().getUserId());
        routeDto.setStartAddress(getStartAddress());
        routeDto.setEndAddress(getEndAddress());
        routeDto.setRouteTypeId(getRouteType());
        routeDto.setOriginLatitude(getOriginLatitude());
        routeDto.setOriginLongitude(getOriginLongitude());
        routeDto.setDestinationLatitude(getDestinationLatitude());
        routeDto.setDestinationLongitude(getDestinationLongitude());

        List<WaypointDto> waypointsDto = new ArrayList<>();
        for (Waypoint waypoint : getWaypoints()) {
            WaypointDto waypointDto = waypoint.toWaypoints();
            waypointsDto.add(waypointDto);
        }

        routeDto.setWaypoints(waypointsDto);
        return routeDto;
    }
}
