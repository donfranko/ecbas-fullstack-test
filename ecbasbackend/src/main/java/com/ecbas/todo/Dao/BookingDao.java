package com.ecbas.todo.Dao;

import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ecbas.todo.entity.Booking;

@Repository
public class BookingDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	public List<Booking> getAllBookings() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Booking> bookingList = session.createCriteria(Booking.class).list();
		return bookingList;
	}

	public Booking getBooking(UUID id) {
		Session session = this.sessionFactory.getCurrentSession();
		Booking booking = (Booking) session.get(Booking.class, id);
		return booking;
	}

	public Booking addBooking(Booking booking) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(booking);
		return booking;
	}

	public void updateBooking(Booking booking) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(booking);
	}

	public void deleteBooking(UUID id) {
		Session session = this.sessionFactory.getCurrentSession();
		Booking p = (Booking) session.load(Booking.class, id);
		if (null != p) {
			session.delete(p);
		}
	}
}