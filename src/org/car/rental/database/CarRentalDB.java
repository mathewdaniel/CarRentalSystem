package org.car.rental.database;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.car.rental.model.Car;
import org.car.rental.model.CarBooking;
import org.car.rental.model.CarInvoice;
import org.car.rental.model.CarType;
import org.car.rental.resources.CarUtil;

public class CarRentalDB {
	

	private static List <CarInvoice> carList = new ArrayList<CarInvoice>();
	private static List <CarBooking> bookingList = new ArrayList<CarBooking>();
	private static int carInvoiceNum = 101;
	
	public static void addCarToDB(Car car, int quantity) {
		for (int i = 0; i < quantity; i++) {
			carList.add(new CarInvoice(carInvoiceNum++, car));
		}
	}
	
	public static void addCarToDB(String name, CarType type, String model, int quantity) {
		for (int i = 0; i < quantity; i++) {
			carList.add(new CarInvoice(carInvoiceNum++, new Car(name, type, model)));
		}
	}
	
	public static List <CarInvoice> getCarInvoiceList() {
		return carList;
	}
	
	public static CarInvoice getCarInvoice(int carNum) {
		return carList.stream().filter(p -> (p.getCarNum() == carNum)).findFirst().get();
	}
	
	public static List <CarBooking> getCarBookingList() {
		return bookingList;
	}
	
	public static boolean reserverBooking(int carNum, String sDate, String sTime, int numDays) {
		Date startDate = CarUtil.convertToDate(sDate, sTime);
		Date endDate = CarUtil.addDays(startDate, numDays);
		boolean isBooked = true;
		List <CarBooking> booking = bookingList.stream().filter(p -> (p.getCarNum() == carNum)).collect(Collectors.toList());
		if ((booking != null) && (booking.size() != 0)) {
			for (CarBooking carBooking : booking) {
				if (CarUtil.dateBetween(startDate, carBooking.getStartDate(), carBooking.getEndDate()) || 
						CarUtil.dateBetween(endDate, carBooking.getStartDate(), carBooking.getEndDate()) ||
						CarUtil.dateBetween(carBooking.getStartDate(), startDate, endDate) ||
						CarUtil.dateBetween(carBooking.getEndDate(), startDate, endDate)) {
					isBooked = false;
				}
			}
		}
		if (isBooked) {
			bookingList.add(new CarBooking(carNum, sDate, sTime, numDays));
		}
		return isBooked;
	}
}
