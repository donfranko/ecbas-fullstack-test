package com.ecbas.todo.Dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TripWaypointDto {

	private String id;

	private String idBooking;

	private Boolean lastStop;

	private String locality;

	private Double lat;

	private Double lng;

	private String tripWayPointTimestamp;
}