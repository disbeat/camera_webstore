package is.ws;

import is.entities.OrderEntity;
import is.entities.OrderItemEntity;
import is.entities.UserAccountEntity;
import is.sessionbeans.OrdersManager;
import is.sessionbeans.interfaces.remote.OrdersManagerRemote;
import is.utils.Mailer;

import java.util.Date;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.rmi.PortableRemoteObject;

/**
 * Session Bean implementation class chippingCallBack
 */
@Stateless
@WebService(targetNamespace="www.shippingcallback.com")
public class ShippingCallBack implements ShippingCallBackRemote {

	private static final String MAIL_SUBJECT = "LPCO Order Delivered";
	private static String MAIL_BODY = "Your order has been delivered";
	
	 /**
     * Default constructor. 
     */
    public ShippingCallBack() {
    }
    
    @WebMethod
    public boolean orderReady(int orderID, int delivering_days)
    {
    	OrdersManagerRemote manager = null;
    	InitialContext ic;
		try {
			ic = new InitialContext();
			manager = (OrdersManagerRemote)ic.lookup("OrdersManager/remote");
						
			OrderEntity order = manager.updateOrderStatus(orderID, OrderEntity.DELIVERED, delivering_days);
	    	
	    	UserAccountEntity user = order.getUser();

	    	StringBuffer sb = new StringBuffer();
	    	
	    	sb.append("Caro ").append(user.getUsername());
	    	sb.append("\n\nA sua encomenda de ").append(order.getTime()).append(" foi entregue na sua morada:\n\n");
	    	sb.append(user.getAddress()).append("\n\n");
	    	sb.append("Material Entregue:\n\n");
	    	
	    	for (OrderItemEntity item: order.getOrderItems()){
	    		sb.append(item.getQuantity());
	    		if (item.getQuantity() > 1)
	    			sb.append(" câmaras fotograficas da marca ");
	    		else
	    			sb.append(" câmara fotografica da marca ");
	    		sb.append(item.getCam().getBrand()).append(" modelo ")
	    		.append(item.getCam().getName()).append("\n\n");
	    	}
	    	
	    	sb.append("\t\t------------------------------------------\n\n");
	    	sb.append("Agradecemos a sua preferência.");
	    	
	    	Mailer mailer = new Mailer();
	    	mailer.sendMail(user.getEmail(), MAIL_SUBJECT, sb.toString());
	    	
	    	System.out.println("the goods have been delivered");
	    	return true;
		
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (MessagingException e){
			e.printStackTrace();
		}
    	
    	return false;
    	
    	
    }
    
}
