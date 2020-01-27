package com.ecbas.todo.entity;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "BOOKINIG")
public class Booking {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID bookingId;

	@Column(name = "passenger_name")
	private String passengerName;

	@Column(name = "passenger_contact_number")
	private String passengerContactNumber;

	@Column(name = "pickup_time")
	private OffsetDateTime pickupTime;

	@Column(name = "asap")
	private Boolean asap = true;

	@Column(name = "waiting_time")
	private Integer waitingTime;

	@Column(name = "no_of_passengers")
	private Integer noOfPassengers;

	@Column(name = "price")
	private BigDecimal price;

	@Column(name = "rating")
	private Integer rating;

	@Column(name = "created_on")
	private Instant createdOn;

	@Column(name = "last_modified_on")
	private Instant lastModifiedOn;

	@OneToMany(mappedBy = "booking", cascade = CascadeType.REMOVE)
	private List<TripWaypoint> tripWayPoints;

	@PrePersist
	public void prePersist() {
		this.createdOn = Instant.now();
		this.lastModifiedOn = this.createdOn;
	}

	@PreUpdate
	public void preUpdate() {
		this.lastModifiedOn = Instant.now();
	}
}