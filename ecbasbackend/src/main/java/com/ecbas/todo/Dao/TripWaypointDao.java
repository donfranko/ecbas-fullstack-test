package com.ecbas.todo.Dao;

import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ecbas.todo.entity.TripWaypoint;

@Repository
public class TripWaypointDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	public List<TripWaypoint> getAllTripWaypoints() {
		Session session = this.sessionFactory.getCurrentSession();
		// List<TripWaypoint> tripWaypointDaoList = session.createQuery("from
		// TripWaypoint").list();
		List<TripWaypoint> tripWaypointDaoList = session.createCriteria(TripWaypoint.class).list();
		return tripWaypointDaoList;
	}

	public TripWaypoint getTripWaypoint(UUID id) {
		Session session = this.sessionFactory.getCurrentSession();
		TripWaypoint tripWaypointDao = (TripWaypoint) session.get(TripWaypoint.class, id);
		return tripWaypointDao;
	}

	public TripWaypoint addTripWaypoint(TripWaypoint tripWaypointDao) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(tripWaypointDao);
		return tripWaypointDao;
	}

	public void updateTripWaypoint(TripWaypoint tripWaypointDao) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(tripWaypointDao);
	}

	public void deleteTripWaypoint(UUID id) {
		Session session = this.sessionFactory.getCurrentSession();
		TripWaypoint p = (TripWaypoint) session.load(TripWaypoint.class, id);
		if (null != p) {
			session.delete(p);
		}
	}
}