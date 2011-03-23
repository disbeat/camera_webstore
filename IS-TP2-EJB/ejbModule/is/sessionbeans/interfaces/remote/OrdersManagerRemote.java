package is.sessionbeans.interfaces.remote;
import java.util.List;

import is.entities.OrderEntity;
import is.entities.OrderItemEntity;
import is.exceptions.CreditCardException;
import is.sharedObjects.Cart;

import javax.ejb.Remote;

@Remote
public interface OrdersManagerRemote {
	public void checkOut(Cart cart) throws CreditCardException;
	public OrderEntity getOrder(int id);
	public OrderEntity updateOrderStatus(Integer orderID, Integer status, Integer deliveringDays);
	public List<OrderItemEntity> getItemsOfOrder(OrderEntity order);
	public List<is.sharedObjects.Order> getOrdersOfUser(Integer userID);
}
