package com.epam.rd.irctc.persistence;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.epam.rd.irctc.exceptions.InvalidKeyException;
import com.epam.rd.irctc.model.ServiceableDate;

@Component
@Scope(value="prototype")
public class ServiceableDateDaoImpl implements ServiceableDateDao {
	
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.epam.rd.irctc");

	private Logger logger;

	@PersistenceContext
	private EntityManager entityManager;

	public ServiceableDateDaoImpl() {
		logger = Logger.getLogger(ServiceableDateDaoImpl.class);
		entityManager = Persistence.createEntityManagerFactory("irctc_persistence").createEntityManager();
	}

	@Override
	public void ensureDayId(String dayId) {
		
		boolean isPersistentDayId = false;
		
		List<ServiceableDate> serviceableDatesList = entityManager.createNamedQuery("getAllRecords", ServiceableDate.class).getResultList();		
		for(ServiceableDate serviceableDate: serviceableDatesList) {
			if(serviceableDate.getDayId().equals(dayId)) {
				isPersistentDayId = true;
				break;
			}
		}
		
		if(!isPersistentDayId) {
			throw new InvalidKeyException("INVALID KEY: DAY ID : " + dayId);
		}
		
		logger.info("Day Id : " + dayId + " ensured");
	}

	@Override
	public void validateDateFormat(String date) {
		
		if(date.trim().equals("")) {
			throw new InvalidKeyException("INVALID KEY: EMPTY KEY");
		}
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		simpleDateFormat.setLenient(false);
		
		try {
			@SuppressWarnings("unused")
			Date javaDate = simpleDateFormat.parse(date);
		} catch(ParseException parseException) {
			throw new InvalidKeyException("INVALID KEY: UNABLE TO PARSE KEY : " + date + ", " + parseException.getMessage());
		}
		
		logger.info("Date : " + date + " is in a valid format");
	}

	@Override
	public void ensureDateAvailability(String date) {
		
		boolean isPersistentDate = false;
		
		List<ServiceableDate> serviceableDatesList = entityManager.createNamedQuery("getAllRecords", ServiceableDate.class).getResultList();		
		for(ServiceableDate serviceableDate: serviceableDatesList) {
			if(serviceableDate.getDate().equals(date)) {
				isPersistentDate = true;
				break;
			}
		}
		
		if(!isPersistentDate) {
			throw new InvalidKeyException("INVALID KEY: DATE : " + date);
		}
		
		logger.info("Date : " + date + " ensured");
	}

	@Override
	public List<String> getAvailableDates() {
		
		List<String> datesList = new ArrayList<>();
		
		List<ServiceableDate> serviceableDatesList = entityManager.createNamedQuery("getAllRecords", ServiceableDate.class).getResultList();		
		for(ServiceableDate serviceableDate: serviceableDatesList) {
			datesList.add(serviceableDate.getDate());
		}
		
		return datesList;
	}

	@Override
	public String getDayId(String date) {
		
		return entityManager.createNamedQuery("getDayId", ServiceableDate.class).setParameter("date", date).getSingleResult().getDayId();
	}

	@Override
	public String getDate(String dayId) {
		
		return entityManager.find(ServiceableDate.class, dayId).getDate();
	}

}
