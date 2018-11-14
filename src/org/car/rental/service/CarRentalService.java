package org.car.rental.service;

import org.car.rental.database.CarRentalDB;
import org.car.rental.model.CarInvoice;

public class CarRentalService {
	
	public CarInvoice getCarInvoice(int carNum) {
		return CarRentalDB.getCarInvoice(carNum);
	}
	
	public boolean bookACar(int carNum, String startDate, String startTime, int bookedDays) {
		return CarRentalDB.reserverBooking(carNum, startDate, startTime, bookedDays);
	}
	
}
