package com.ecbas.todo.service.impl;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecbas.todo.Dao.TripWaypointDao;
import com.ecbas.todo.Dto.TripWaypointDto;
import com.ecbas.todo.entity.TripWaypoint;
import com.ecbas.todo.service.BookingService;
import com.ecbas.todo.service.TripWaypointService;

@Service("tripWaypointService")
public class TripWaypointServiceImpl implements TripWaypointService {

	@Autowired
	TripWaypointDao tripWaypointDao;

	@Autowired
	BookingService bookingService;

	@Transactional
	@Override
	public List<TripWaypoint> getAllTripWaypoints() {
		return tripWaypointDao.getAllTripWaypoints();
	}

	@Transactional
	@Override
	public TripWaypoint getTripWaypoint(UUID id) {
		if (id == null) {
			throw new IllegalArgumentException("No TripWaypoint found, id is null");
		}
		return tripWaypointDao.getTripWaypoint(id);
	}

	@Transactional
	@Override
	public TripWaypoint addTripWaypoint(TripWaypointDto tripWaypointDto) {
		TripWaypoint tripWaypoint = new TripWaypoint();
		try {
			return tripWaypointDao.addTripWaypoint(this.copyTripWaypoint(tripWaypoint, tripWaypointDto));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	@Transactional
	@Override
	public void updateTripWaypoint(TripWaypoint tripWaypoint) {
		try {
			tripWaypointDao.updateTripWaypoint(tripWaypoint);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Transactional
	@Override
	public void updateTripWaypoint(TripWaypoint tripWaypoint, TripWaypointDto tripWaypointDto) {
		try {
			tripWaypointDao.updateTripWaypoint(this.copyTripWaypoint(tripWaypoint, tripWaypointDto));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Transactional
	@Override
	public void deleteTripWaypoint(UUID id) {
		if (id == null) {
			throw new IllegalArgumentException("No deleted TripWaypoint, id is null");
		}
		tripWaypointDao.deleteTripWaypoint(id);
	}

	private TripWaypoint copyTripWaypoint(TripWaypoint tripWaypoint, TripWaypointDto tripWaypointDto) {
		tripWaypoint.setBooking(bookingService.getBooking(UUID.fromString(tripWaypointDto.getIdBooking())));
		tripWaypoint.setLastStop(tripWaypointDto.getLastStop());
		tripWaypoint.setLat(tripWaypointDto.getLat());
		tripWaypoint.setLng(tripWaypointDto.getLng());
		tripWaypoint.setLocality(tripWaypointDto.getLocality());
		tripWaypoint.setTripWayPointTimestamp(Instant.parse(tripWaypointDto.getTripWayPointTimestamp()));
		return tripWaypoint;
	}
}