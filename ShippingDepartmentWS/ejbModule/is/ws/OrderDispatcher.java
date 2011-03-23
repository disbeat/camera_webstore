package is.ws;

import com.shippingcallback.ShippingCallBack;
import com.shippingcallback.ShippingCallBackService;

import is.objects.Order;

public class OrderDispatcher extends Thread{
	
	private static final Integer TIMESTEP = 1000;
	Order order;
	Integer timesteps;
	
	public OrderDispatcher(Order order, Integer timesteps){
		this.order = order;
		this.timesteps = timesteps;
	}
	
	public void run(){
		for (int i = 0; i < timesteps; i++){
			try {
				Thread.sleep(TIMESTEP);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		ShippingCallBackService scbs = new ShippingCallBackService();
		ShippingCallBack callBack = scbs.getShippingCallBackPort();
		
		callBack.orderReady(order.getOrderID(), timesteps);
		
	}
}
