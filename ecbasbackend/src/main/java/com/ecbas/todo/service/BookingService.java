package com.ecbas.todo.service;

import java.util.List;
import java.util.UUID;

import com.ecbas.todo.Dto.BookingDto;
import com.ecbas.todo.entity.Booking;

public interface BookingService {

	public List<Booking> getAllBookings();

	public Booking getBooking(UUID id);

	public Booking addBooking(BookingDto bookingDto);

	public void updateBooking(UUID id, BookingDto bookingDto);

	public void deleteBooking(UUID id);
}