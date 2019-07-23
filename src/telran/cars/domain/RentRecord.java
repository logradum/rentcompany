package telran.cars.domain;

import java.io.Serializable;
import java.time.LocalDate;

public class RentRecord  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long licenseId;
	private String regNumber;
	private LocalDate rentDate;
	private LocalDate returnDate;
	private int gasTankPercent;
	private int rentDays;
	private double cost;
	private int damages;

	public RentRecord() {
	}

	public RentRecord(long licenseId, String regNumber, LocalDate rentDate, int rentDays) {
		this.licenseId = licenseId;
		this.regNumber = regNumber;
		this.rentDate = rentDate;
		this.rentDays = rentDays;
	}

	public long getLicenseId() {
		return licenseId;
	}

	public String getRegNumber() {
		return regNumber;
	}

	public LocalDate getRentDate() {
		return rentDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public int getGasTankPercent() {
		return gasTankPercent;
	}

	public int getRentDays() {
		return rentDays;
	}

	public double getCost() {
		return cost;
	}

	public int getDamages() {
		return damages;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public void setGasTankPercent(int gasTankPercent) {
		this.gasTankPercent = gasTankPercent;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public void setDamages(int damages) {
		this.damages = damages;
	}

	@Override
	public String toString() {
		return "RentRecord [licenseId=" + licenseId + ", regNumber=" + regNumber + ", rentDate=" + rentDate
				+ ", returnDate=" + returnDate + ", gasTankPercent=" + gasTankPercent + ", rentDays=" + rentDays
				+ ", cost=" + cost + ", damages=" + damages + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (licenseId ^ (licenseId >>> 32));
		result = prime * result + ((regNumber == null) ? 0 : regNumber.hashCode());
		result = prime * result + ((rentDate == null) ? 0 : rentDate.hashCode());
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
		if (!(obj instanceof RentRecord)) {
			return false;
		}
		RentRecord other = (RentRecord) obj;
		if (licenseId != other.licenseId) {
			return false;
		}
		if (regNumber == null) {
			if (other.regNumber != null) {
				return false;
			}
		} else if (!regNumber.equals(other.regNumber)) {
			return false;
		}
		if (rentDate == null) {
			if (other.rentDate != null) {
				return false;
			}
		} else if (!rentDate.equals(other.rentDate)) {
			return false;
		}
		return true;
	}
	
	

}
