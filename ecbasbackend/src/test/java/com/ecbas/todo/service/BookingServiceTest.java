package com.ecbas.todo.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import org.hibernate.id.UUIDGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.ecbas.todo.Dao.BookingDao;
import com.ecbas.todo.entity.Booking;

@SpringBootTest
@Transactional
//@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = AppConfig.class, loader = AnnotationConfigContextLoader.class)
public class BookingServiceTest {

	@Autowired
	BookingDao bookingDao;

	@Autowired
	BookingService bookingService;

	@Test
	public void shouldReturnCreatedBooking() {
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

		Booking bookingAdd = bookingService.getBooking(booking.getBookingId());

		assertThat(bookingAdd).isNotNull();
		assertThat(bookingAdd).extracting(Booking::getPassengerName).isEqualTo(booking.getPassengerName());
		assertThat(bookingAdd).extracting(Booking::getPrice).isEqualTo(booking.getPrice());
		assertThat(bookingAdd).extracting(Booking::getPickupTime).isEqualTo(booking.getPickupTime());
		assertThat(bookingAdd).extracting(Booking::getRating).isEqualTo(booking.getRating());
	}

	@Test
	public void shouldReturnNullForNotExistingBooking() {
		Booking booking = bookingService.getBooking(UUID.randomUUID());

		assertThat(booking).isNull();
	}
}
