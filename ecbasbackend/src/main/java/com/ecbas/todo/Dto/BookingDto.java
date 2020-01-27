package com.ecbas.todo.Dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class BookingDto {

	private String id;

	private String passengerName;

	private String passengerContactNumber;

	private String pickupTime;

	private Boolean asap;

	private Integer waitingTime;

	private Integer noOfPassengers;

	private BigDecimal price;

	private Integer rating;

	private Instant createdOn;

	private Instant lastModifiedOn;

	private List<TripWaypointDto> tripWaypointDtos = new ArrayList<TripWaypointDto>();

}