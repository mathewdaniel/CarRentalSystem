package org.car.rental.model;

import java.util.List;
import java.util.stream.Collectors;

import org.car.rental.database.CarRentalDB;
import org.car.rental.resources.CarUtil;

public class CarInvoice {
	
	private int carNum;
	private Car car;
	
	public CarInvoice(int num, Car car) {
		this.carNum = num;
		this.car = car;
	}

	public int getCarNum() {
		return carNum;
	}

	public void setCarNum(int carNum) {
		this.carNum = carNum;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		List <CarBooking> carBookingList = CarRentalDB.getCarBookingList().stream().filter(p -> (p.getCarNum() == carNum)).collect(Collectors.toList());
		if (carBookingList != null && carBookingList.size() != 0) {
			for (CarBooking carBooking : carBookingList) {
				sb.append(carNum + ", " + car.toString() + ", " + CarUtil.dateToString(carBooking.getStartDate()) + ", " + CarUtil.dateToString(carBooking.getEndDate()));
				sb.append(System.getProperty("line.separator"));
			}
		} else {
			sb.append(carNum + ", " + car.toString()).append(System.getProperty("line.separator"));
		}
		return sb.toString();
	}

}
