package org.car.rental.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Car {
	
	private String name;
	private List<CarType> type = new ArrayList<CarType>();
	private String model;
	
	public Car(String name, CarType type, String model) {
		this.name = name;
		this.type.add(type);
		this.model = model;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CarType> getType() {
		return type;
	}

	public void setType(CarType type) {
		if (!this.type.contains(type)) {
			this.type.add(type);
		}
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
	public String toString() {
		return name + ", " + Arrays.toString(type.stream().map(sc -> sc.getCarType()).collect(Collectors.toList()).toArray()) + ", " + model;
	}
	
}
