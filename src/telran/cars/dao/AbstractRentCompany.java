package telran.cars.dao;

public abstract class AbstractRentCompany implements IRentCompany {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int finePercent = 15;
	protected int gasPrice = 10;

	public int getFinePercent() {
		return finePercent;
	}

	public void setFinePercent(int finePercent) {
		this.finePercent = finePercent;
	}

	public int getGasPrice() {
		return gasPrice;
	}

	public void setGasPrice(int gasPrice) {
		this.gasPrice = gasPrice;
	}
}
