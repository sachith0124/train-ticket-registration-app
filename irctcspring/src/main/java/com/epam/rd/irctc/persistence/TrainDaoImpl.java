package com.epam.rd.irctc.persistence;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.epam.rd.irctc.exceptions.InconsistentDataException;
import com.epam.rd.irctc.exceptions.InvalidKeyException;
import com.epam.rd.irctc.model.Train;

@Transactional
@Component
@Scope(value="prototype")
public class TrainDaoImpl implements TrainDao {
	
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.epam.rd.irctc");
	
	private Logger logger;

	@PersistenceContext
	private EntityManager entityManager;

	public TrainDaoImpl(){
		logger = Logger.getLogger(TrainDaoImpl.class);
		entityManager = Persistence.createEntityManagerFactory("irctc_persistence").createEntityManager();
	}

	@Override
	public void ensureTrain(String trainId) {
		if( entityManager.find(Train.class, trainId) == null) {
			throw new InvalidKeyException("INVALID KEY: TRAIN ID : " + trainId);
		}
		logger.info("Train with train ID : " + trainId + " ensured");
	}

	@Override
	public void ensureTrains(List<String> trainIdsList) {
		
		List<String> invalidTrainIdsList = new ArrayList<>();
		
		for(String trainId: trainIdsList) {
			if( entityManager.find(Train.class, trainId) == null) {
				invalidTrainIdsList.add(trainId);
			}
		}
		
		if(!invalidTrainIdsList.isEmpty()) {
			throw new InvalidKeyException("INVALID KEY: TRAIN IDs : " + invalidTrainIdsList.toString());
		}
		
		logger.info("Trains with train IDs : " + trainIdsList.toString() + " ensured");
	}

	@Override
	public void ensureSeatType(String seatType) {
		
		List<Train> trainsList = getAllTrains();
		
		boolean isValidSeatType = false;
		
		for(Train train: trainsList) {
			if(train.getSeatsLog().containsKey(seatType)) {
				isValidSeatType = true;
				break;
			}
		}
		
		if(!isValidSeatType) {
			throw new InvalidKeyException("INVALID KEY: SEAT TYPE : " + seatType);
		}
		
		logger.info("Seat type : " + seatType + " ensured");
	}

	@Override
	public void ensureSeatType(String trainId, String seatType) {
		
		if(!entityManager.find(Train.class, trainId).getSeatsLog().containsKey(seatType)) {
			throw new InconsistentDataException("INCONSISTENT DATA: TRAIN ID : " + trainId + " AND SEAT TYPE : " + seatType);
		}
		
		logger.info("Seat type " + seatType + " for Train with train ID : " + trainId + " ensured");
	}

	@Override
	public List<String> getSeatTypes(String trainId) {	
		
		return entityManager.find(Train.class, trainId).getSeatsLog().keySet().stream().collect(Collectors.toList());
	}
	
	@Override
	public List<String> getAllSeatTypes() {
		
		List<Train> trainsList = getAllTrains();
		
		Set<String> allSeatTypes = new HashSet<>();
		
		for(Train train: trainsList) {
			allSeatTypes.addAll(train.getSeatsLog().keySet());
		}
		
		return allSeatTypes.stream().collect(Collectors.toList());
	}

	@Override
	public Train getTrain(String trainId) {
		
		return entityManager.find(Train.class, trainId);
	}

	@Override
	public List<Train> getTrains(List<String> trainIdsList) {
		
		return entityManager.createNamedQuery("getTrainsByTrainIds", Train.class).setParameter("ids", trainIdsList).getResultList();
	}
	
	@Override
	public List<Train> getTrainsByTrainName(String trainName) {
		
		return entityManager.createNamedQuery("getTrainsByTrainName", Train.class).setParameter(1, trainName).getResultList();
	}

	@Override
	public List<Train> getAllTrains() {
		
		return entityManager.createNamedQuery("getAllTrains", Train.class).getResultList();
	}

	@Override
	public int getSeatFare(String trainId, String seatType) {
		
		return getTrain(trainId).getSeatsLog().get(seatType).getSeatFare();
	}

}
