package is.sessionbeans;

import is.entities.CameraEntity;
import is.entities.OrderEntity;
import is.entities.OrderItemEntity;
import is.entities.UserAccountEntity;
import is.exceptions.CreditCardException;
import is.sessionbeans.interfaces.remote.OrdersManagerRemote;
import is.sharedObjects.Cart;
import is.sharedObjects.OrderItem;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.shippingdepartmentws.Order;
import com.shippingdepartmentws.ShippingBean;
import com.shippingdepartmentws.ShippingBeanService;

/**
 * Session Bean implementation class ordersManager
 */
@Stateless
public class OrdersManager implements OrdersManagerRemote {

	@PersistenceContext( unitName="IS_TP2_DB" )
	EntityManager em;
	
    /**
     * Default constructor. 
     */
    public OrdersManager() {
    }
    
    public OrdersManager(EntityManager em) {
    	this.em = em;
    }
    
    public OrderEntity getOrder(int id){
    	Query q = em.createQuery("SELECT o FROM OrderEntity o WHERE o.id = ?");
    	q.setParameter(1, id);
    	try {
    		return (OrderEntity) q.getSingleResult();
    	} catch(NoResultException nr){
    		return null;
    	}
    }
    
    public void checkOut(Cart cart) throws CreditCardException
    {
    	UserManager userManager;
    	CatalogManager catalogManager;
    	OrderEntity order;
    	List <OrderItemEntity> items;
		UserAccountEntity user;
    	
    	if (Math.random()<0.25)		
    		throw new CreditCardException("Your credit balance is insufficient to purchase this order");
    	
		userManager = new UserManager(em);
		
		user = userManager.getUser(cart.getClientID());
		
		catalogManager = new CatalogManager(em);		
		
		order = new OrderEntity();
		order.setUser(user);
		order.setTime(new Date(System.currentTimeMillis()));
		order.setStatus(OrderEntity.SHIPPING);
		order.setDeliveringDays(-1);
		
		items = new LinkedList<OrderItemEntity>();
		
		em.persist(order);
		
		CameraEntity camEntity;
		OrderItemEntity itemEntity;
		for(OrderItem item : cart.getOrderItems()){
			camEntity = catalogManager.getCameraEntity(item.getCameraID());
			if (camEntity == null)
				continue;
			
			itemEntity = new OrderItemEntity();
			itemEntity.setCam(camEntity);
			
			itemEntity.setQuantity(item.getQtd());
			
			itemEntity.setOrder(order);
			
			items.add(itemEntity);
			
			em.persist(itemEntity);
		}
		
		order.setOrderItems(items);
		
		
		em.flush();
		
		Order orderToWS = new Order();
		
		List<String> cameras = orderToWS.getCameras();
		List<Integer> quantities = orderToWS.getQuantities();
		
		for (OrderItemEntity item : order.getOrderItems()){
			cameras.add(item.getCam().getName());
			quantities.add(item.getQuantity());
		}
		
		orderToWS.setOrderID(order.getId());
		orderToWS.setClientName(user.getUsername());
		orderToWS.setClientAddress(user.getAddress());
		
		ShippingBeanService sbs = new ShippingBeanService();
		ShippingBean SB = sbs.getShippingBeanPort();
		
		SB.shipOrder( orderToWS );
		
    }
    
    
    
    public List<OrderEntity> getOrdersOfUser(UserAccountEntity user){
    	Query q = em.createQuery("SELECT o FROM OrderEntity o WHERE o.user = ?");
    	q.setParameter(1, user);
    	try {
    		return (List<OrderEntity>) q.getResultList();
    	} catch(NoResultException nr){
    		return null;
    	}
    	
    }
    
    
    public List<is.sharedObjects.Order> getOrdersOfUser(Integer userID){
    	List<is.sharedObjects.Order> orders = new LinkedList<is.sharedObjects.Order>();
    	
    	UserManager u = new UserManager(em);
    	UserAccountEntity user = u.getUser(userID);
    	
    	List<OrderEntity> entities = getOrdersOfUser(user);
    	
    	for (OrderEntity entity : entities){
    		orders.add(convertOrderEntityToOrder(entity));
    	}
    	
    	return orders;
    }
    
    public List<OrderItemEntity> getItemsOfOrder(OrderEntity order){
    	Query q = em.createQuery("SELECT o FROM OrderItemEntity o WHERE o.order.id = ?");
    	q.setParameter(1, order.getId());
    	try {
    		return (List<OrderItemEntity>) q.getResultList();
    	} catch(NoResultException nr){
    		return null;
    	}
    	
    }
    
    public OrderEntity updateOrderStatus(Integer orderID, Integer status, Integer deliveringDays){
    	OrderEntity order = getOrder(orderID);
    	order.setStatus(status);
    	order.setDeliveringDays(deliveringDays);
    	em.flush();
    	
    	return order;
    }
    
    public is.sharedObjects.Order convertOrderEntityToOrder(OrderEntity entity){
    	is.sharedObjects.Order order = new is.sharedObjects.Order();
    	
    	order.setId(entity.getId());
    	order.setTime(entity.getTime());
    	order.setStatus(entity.getStatus());
    	order.setDeliveringDays(entity.getDeliveringDays());
    	
    	List<OrderItem> items = new LinkedList<OrderItem>();
    	OrderItem item = null;
    	for (OrderItemEntity itemEntity : entity.getOrderItems()){
    		item = new OrderItem();
    		item.setCameraID(itemEntity.getCam().getId());
    		item.setCameraName(itemEntity.getCam().getName());
    		item.setPrice(itemEntity.getCam().getPrice());
    		item.setQtd(itemEntity.getQuantity());
    		items.add(item);
    	}
    	
    	order.setOrderItems(items);
    	
    	return order;
    	
    }

}
