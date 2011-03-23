package is.objects;

public class OrderItem {
	int cameraID;
	int qtd;
	int price;
	String cameraName;
	
	OrderItem (int cameraID, String cameraName, int qtd, int price){
		this.cameraID = cameraID;
		this.cameraName = cameraName;
		this.qtd = qtd;
		this.price = price;
	}

	/**
	 * @return the cameraID
	 */
	public int getCameraID() {
		return cameraID;
	}

	/**
	 * @param cameraID the cameraID to set
	 */
	public void setCameraID(int cameraID) {
		this.cameraID = cameraID;
	}

	/**
	 * @return the qtd
	 */
	public int getQtd() {
		return qtd;
	}

	/**
	 * @param qtd the qtd to set
	 */
	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * @return the cameraName
	 */
	public String getCameraName() {
		return cameraName;
	}

	/**
	 * @param cameraName the cameraName to set
	 */
	public void setCameraName(String cameraName) {
		this.cameraName = cameraName;
	}
	
	
}
