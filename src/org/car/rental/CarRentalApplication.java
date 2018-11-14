package org.car.rental;

import org.car.rental.service.CarRentalService;

public class CarRentalApplication {
	
	private CarRentalService service;
	
	public CarRentalService getService() {
		if (service == null) {
			service = new CarRentalService();
		}
		return service;
	}
	
}
