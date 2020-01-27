package com.ecbas.todo.entity;

import java.time.Instant;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "TRIPWAYPOINT")
public class TripWaypoint {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID tripWayPointId;

	@ManyToOne
	@JoinColumn(name = "booking_id")
	@JsonIgnore
	private Booking booking;

	@Column(name = "last_stop")
	private Boolean lastStop;

	@Column(name = "locality")
	private String locality;

	@Column(name = "lat")
	private Double lat;

	@Column(name = "lng")
	private Double lng;

	@Column(name = "trip_way_point_timestamp")
	private Instant tripWayPointTimestamp;
}