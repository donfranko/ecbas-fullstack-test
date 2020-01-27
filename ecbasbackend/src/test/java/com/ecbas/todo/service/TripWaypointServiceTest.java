package com.ecbas.todo.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.ecbas.todo.Dao.BookingDao;
import com.ecbas.todo.Dto.TripWaypointDto;
import com.ecbas.todo.entity.Booking;
import com.ecbas.todo.entity.TripWaypoint;

@SpringBootTest
@Transactional
public class TripWaypointServiceTest {

	@Autowired
	BookingDao bookingDao;

	@Autowired
	TripWaypointService tripWaypointService;

	@Test
	public void shouldAddTripWaypoint() {
		Booking booking = createTestBooking();

		TripWaypointDto tripWaypointDto = new TripWaypointDto();
		tripWaypointDto.setIdBooking(booking.getBookingId().toString());
		tripWaypointDto.setLastStop(true);
		tripWaypointDto.setLat(Double.valueOf(123.456));
		tripWaypointDto.setLng(Double.valueOf(456.789));
		tripWaypointDto.setLocality("Localité");
		tripWaypointDto.setTripWayPointTimestamp(Instant.now().toString());
		TripWaypoint tripWaypoint = tripWaypointService.addTripWaypoint(tripWaypointDto);

		TripWaypoint tripWaypointAdd = tripWaypointService.getTripWaypoint(tripWaypoint.getTripWayPointId());

		assertThat(tripWaypointAdd).isNotNull();
		assertThat(tripWaypointAdd).extracting(TripWaypoint::getLat).isEqualTo(tripWaypoint.getLat());
		assertThat(tripWaypointAdd).extracting(TripWaypoint::getLocality).isEqualTo(tripWaypoint.getLocality());
		assertThat(tripWaypointAdd).extracting(TripWaypoint::getLastStop).isEqualTo(tripWaypoint.getLastStop());
	}

	@Test
	public void shouldReturnNullForNotExistingTripWaypoint() {
		TripWaypoint tripWaypoint = tripWaypointService.getTripWaypoint(UUID.randomUUID());

		assertThat(tripWaypoint).isNull();
	}

	private Booking createTestBooking() {
		Booking booking = new Booking();
		booking.setPassengerName("Test passenger name");
		booking.setPassengerContactNumber("Test contact number");
		booking.setPickupTime(OffsetDateTime.now());
		booking.setAsap(true);
		booking.setWaitingTime(2);
		booking.setNoOfPassengers(5);
		booking.setPrice(BigDecimal.valueOf(20000));
		booking.setRating(4);
		bookingDao.addBooking(booking);
		return booking;
	}
}
