package com.ecbas.todo.service.impl;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecbas.todo.Dao.BookingDao;
import com.ecbas.todo.Dto.BookingDto;
import com.ecbas.todo.Dto.TripWaypointDto;
import com.ecbas.todo.entity.Booking;
import com.ecbas.todo.entity.TripWaypoint;
import com.ecbas.todo.service.BookingService;

@Service("bookingService")
public class BookingServiceImpl implements BookingService {

	@Autowired
	BookingDao bookingDao;

	@Autowired
	TripWaypointServiceImpl tripWaypointServiceImpl;

	@Transactional
	@Override
	public List<Booking> getAllBookings() {
		return bookingDao.getAllBookings();
	}

	@Transactional
	@Override
	public Booking getBooking(UUID id) {
		if (id == null) {
			throw new IllegalArgumentException("No Booking found, id is null");
		}
		return bookingDao.getBooking(id);
	}

	@Transactional
	@Override
	public Booking addBooking(BookingDto bookingDto) {
		Booking booking = new Booking();
		try {
			bookingDao.addBooking(this.copyBooking(booking, bookingDto));

			bookingDto.getTripWaypointDtos().forEach((TripWaypointDto tripWaypointDto) -> {
				tripWaypointDto.setIdBooking(booking.getBookingId().toString());
				tripWaypointServiceImpl.addTripWaypoint(tripWaypointDto);
			});
			return booking;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	@Transactional
	@Override
	public void updateBooking(UUID id, BookingDto bookingDto) {
		Booking booking = bookingDao.getBooking(id);
		try {
			bookingDao.updateBooking(this.copyBooking(booking, bookingDto));

			// Add new/update old TripWaypoints
			List<TripWaypointDto> listUpdatedTripWaypoints = bookingDto.getTripWaypointDtos();
			List<String> listIdUpdated = new ArrayList<>();
			listUpdatedTripWaypoints.forEach((TripWaypointDto tripWaypointDto) -> {
				tripWaypointDto.setIdBooking(id.toString());
				if (tripWaypointDto.getId() != null && !tripWaypointDto.getId().isEmpty()) {
					TripWaypoint tripWaypoint = tripWaypointServiceImpl
							.getTripWaypoint(UUID.fromString(tripWaypointDto.getId()));
					tripWaypointServiceImpl.updateTripWaypoint(tripWaypoint, tripWaypointDto);
					listIdUpdated.add(tripWaypointDto.getId());
				} else {
					TripWaypoint added = tripWaypointServiceImpl.addTripWaypoint(tripWaypointDto);
					listIdUpdated.add(added.getTripWayPointId().toString());
				}
			});

			// Remove not send TripWaypoints
			List<TripWaypoint> listTripWaypoints = booking.getTripWayPoints();
			listTripWaypoints.forEach((TripWaypoint tripWaypoint) -> {
				if (!listIdUpdated.contains(tripWaypoint.getTripWayPointId().toString())) {
					tripWaypointServiceImpl.deleteTripWaypoint(tripWaypoint.getTripWayPointId());
				}
			});
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Transactional
	@Override
	public void deleteBooking(UUID id) {
		if (id == null) {
			throw new IllegalArgumentException("No deleted Booking, id is null");
		}
		bookingDao.deleteBooking(id);
	}

	private Booking copyBooking(Booking booking, BookingDto bookingDto) {
		booking.setPassengerName(bookingDto.getPassengerName());
		booking.setPassengerContactNumber(bookingDto.getPassengerContactNumber());
		booking.setPickupTime(OffsetDateTime.parse(bookingDto.getPickupTime()));
		booking.setAsap(bookingDto.getAsap());
		booking.setWaitingTime(bookingDto.getWaitingTime());
		booking.setNoOfPassengers(bookingDto.getNoOfPassengers());
		booking.setPrice(bookingDto.getPrice());
		booking.setRating(bookingDto.getRating());

		return booking;
	}
}