package telran.cars.domain;

import java.io.Serializable;

public class Driver implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long licenseId;
	private String name;
	private int birthYear;
	private String phone;

	public Driver() {
	}

	public Driver(long licenseId, String name, int birthYear, String phone) {
		this.licenseId = licenseId;
		this.name = name;
		this.birthYear = birthYear;
		this.phone = phone;
	}

	public long getLicenseId() {
		return licenseId;
	}

	public String getName() {
		return name;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Driver [licenseId=" + licenseId + ", name=" + name + ", birthYear=" + birthYear + ", phone=" + phone
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (licenseId ^ (licenseId >>> 32));
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
		if (!(obj instanceof Driver)) {
			return false;
		}
		Driver other = (Driver) obj;
		if (licenseId != other.licenseId) {
			return false;
		}
		return true;
	}

}
