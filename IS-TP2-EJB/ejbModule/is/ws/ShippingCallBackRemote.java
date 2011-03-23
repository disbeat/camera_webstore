package is.ws;
import javax.ejb.Remote;

@Remote
public interface ShippingCallBackRemote {
	public boolean orderReady(int orderID, int delivering_days);
}
