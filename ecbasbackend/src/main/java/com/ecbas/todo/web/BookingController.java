package com.ecbas.todo.web;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ecbas.todo.Dto.BookingDto;
import com.ecbas.todo.entity.Booking;
import com.ecbas.todo.service.BookingService;

@CrossOrigin
@RestController
@RequestMapping("/booking")
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@GetMapping("/{id}")
	public Booking getBooking(@PathVariable UUID id) {
		return bookingService.getBooking(id);
	}

	@GetMapping("/all")
	public List<Booking> getAllBookings() {
		return bookingService.getAllBookings();
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/add")
	public void addBooking(@RequestBody BookingDto bookingDto) {
		System.out.println(bookingDto);
		bookingService.addBooking(bookingDto);
	}

	@PutMapping("/{id}")
	public void updateBooking(@PathVariable UUID id, @RequestBody BookingDto bookingDto) {
		bookingService.updateBooking(id, bookingDto);
	}

	@DeleteMapping("/{id}")
	public void deleteBooking(@PathVariable UUID id) {
		bookingService.deleteBooking(id);
	}
}