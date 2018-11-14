package org.car.rental.model;

public enum CarType {
	SUV("SUV"),
	Truck("Truck"),
	Sedan("Sedan"),
	Van("Van"),
	Coupe("Coupe"),
	Wagon("Wagon"),
	Convertible("Convertible"),
	SportsCar("Sports Car"),
	Diesel("Diesel"),
	Crossover("Crossover"),
	LuxuryCar("Luxury Car"),
	Hybrid("Hybrid");
	
	private String type;
	
	private CarType (String type) {
		this.type = type;
	}
	
	public String getCarType() {
		return type;
	}
	
	public static CarType fromString(String catT) {
		for (CarType t : CarType.values()) {
			if (t.type.equalsIgnoreCase(catT)) {
				return t;
			}
		}
		return null;
	}

}
