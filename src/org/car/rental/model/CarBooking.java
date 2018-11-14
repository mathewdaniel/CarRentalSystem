package org.car.rental.model;

import java.util.Date;

import org.car.rental.resources.CarUtil;

public class CarBooking {
	
	private int carNum;
	private Date startDate;
	private Date endDate;
	private String startDateStr;
	private String startTimeStr;
	private int bookedDays;

	public CarBooking(int carNum, String startDateStr, String startTimeStr, int bookedDays) {
		this.carNum = carNum;
		this.startDateStr = startDateStr;
		this.startTimeStr = startTimeStr;
		this.bookedDays = bookedDays;
		setStartDate(startDateStr, startTimeStr);
		setEndDate(CarUtil.addDays(startDate, bookedDays));
	}

	public int getCarNum() {
		return carNum;
	}

	public void setCarNum(int carNum) {
		this.carNum = carNum;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(String date, String time) {
		this.startDate = CarUtil.convertToDate(date, time);
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getStartDateStr() {
		return startDateStr;
	}

	public void setStartDateStr(String startDateStr) {
		this.startDateStr = startDateStr;
	}

	public String getStartTimeStr() {
		return startTimeStr;
	}

	public void setStartTimeStr(String startTimeStr) {
		this.startTimeStr = startTimeStr;
	}

	public int getBookedDays() {
		return bookedDays;
	}

	public void setBookedDays(int bookedDays) {
		this.bookedDays = bookedDays;
		setEndDate(CarUtil.addDays(startDate, bookedDays));
	}
	
	public String toString() {
		return CarUtil.dateToString(startDate) + ", " + CarUtil.dateToString(endDate);
	}

}
