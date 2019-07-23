package telran.cars.domain;

import java.io.Serializable;

import telran.cars.dto.State;

public class Car implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String regNumber;
	private String color;
	private State state;
	private String modelName;
	private boolean inUse;
	private boolean flRemoved;

	public Car() {
	}

	public Car(String regNumber, String color, String modelName) {
		this.regNumber = regNumber;
		this.color = color;
		this.modelName = modelName;
		this.state = State.EXCELLENT;
	}

	public String getRegNumber() {
		return regNumber;
	}

	public String getColor() {
		return color;
	}

	public State getState() {
		return state;
	}

	public String getModelName() {
		return modelName;
	}

	public boolean isInUse() {
		return inUse;
	}

	public boolean isFlRemoved() {
		return flRemoved;
	}

	public void setState(State state) {
		this.state = state;
	}

	public void setInUse(boolean inUse) {
		this.inUse = inUse;
	}

	public void setFlRemoved(boolean flRemoved) {
		this.flRemoved = flRemoved;
	}

	@Override
	public String toString() {
		return "Car [regNumber=" + regNumber + ", color=" + color + ", state=" + state + ", modelName=" + modelName
				+ ", inUse=" + inUse + ", flRemoved=" + flRemoved + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((regNumber == null) ? 0 : regNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Car)) {
			return false;
		}
		Car other = (Car) obj;
		if (regNumber == null) {
			if (other.regNumber != null) {
				return false;
			}
		} else if (!regNumber.equals(other.regNumber)) {
			return false;
		}
		return true;
	}
	
	

}
