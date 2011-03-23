package is.sharedObjects;

import java.io.Serializable;
import java.util.ArrayList;

public class Cart implements Serializable{
	
	
	ArrayList<OrderItem> orderItems;
	int clientID;
	
	public Cart(int clientID)
	{
		this.clientID = clientID;
		this.orderItems = new ArrayList<OrderItem>();
	}
	
	public void addCamera(int cameraID, String cameraName, float price)
	{
		addCamera(cameraID,cameraName, 1, price);
	}
	
	public void addCamera(int cameraID, String cameraName, int qtd, float price)
	{
		for (OrderItem itr : orderItems)
			if (itr.cameraID == cameraID)
			{
				itr.qtd += qtd;
				return;
			}
		this.orderItems.add(new OrderItem(cameraID, cameraName, qtd, price));
	}
	
	public void removeCamera(String cameraName){
		int i=0;
		for(i=0;i<orderItems.size();i++){
			if(orderItems.get(i).getCameraName().equals(cameraName)){
				orderItems.remove(i);
				i--;
			}
		}
	}
	
	public ArrayList<OrderItem> getOrderItems()
	{
		return this.orderItems;
	}

	/**
	 * @return the clientID
	 */
	public int getClientID() {
		return clientID;
	}

	/**
	 * @param clientID the clientID to set
	 */
	public void setClientID(int clientID) {
		this.clientID = clientID;
	}

	

}
