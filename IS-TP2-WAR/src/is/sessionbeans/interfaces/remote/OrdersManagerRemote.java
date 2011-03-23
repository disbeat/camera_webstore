package is.sessionbeans.interfaces.remote;
import is.exceptions.CreditCardException;
import is.sharedObjects.Cart;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface OrdersManagerRemote {
	public void checkOut(Cart cart) throws CreditCardException;
	public List<is.sharedObjects.Order> getOrdersOfUser(Integer userID);
}
