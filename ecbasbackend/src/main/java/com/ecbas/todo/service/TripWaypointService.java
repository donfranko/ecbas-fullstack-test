package com.ecbas.todo.service;

import java.util.List;
import java.util.UUID;

import com.ecbas.todo.Dto.TripWaypointDto;
import com.ecbas.todo.entity.TripWaypoint;

public interface TripWaypointService {

	public List<TripWaypoint> getAllTripWaypoints();

	public TripWaypoint getTripWaypoint(UUID id);

	public TripWaypoint addTripWaypoint(TripWaypointDto tripWaypointDto);

	public void updateTripWaypoint(TripWaypoint tripWaypoint);

	public void updateTripWaypoint(TripWaypoint tripWaypoint, TripWaypointDto tripWaypointDto);

	public void deleteTripWaypoint(UUID id);
}