package is.frontBeans;

import is.exceptions.CreditCardException;
import is.sessionbeans.interfaces.remote.CatalogManagerRemote;
import is.sessionbeans.interfaces.remote.OrdersManagerRemote;
import is.sessionbeans.interfaces.remote.SearchCameraRemote;
import is.sessionbeans.interfaces.remote.UserManagerRemote;
import is.sharedObjects.Camera;
import is.sharedObjects.Cart;
import is.sharedObjects.Order;

import java.util.LinkedList;
import java.util.List;

import javax.naming.InitialContext;

import is.sharedObjects.Camera;

public class SessionBean {
	private String username = "";
	private String password = "";
	private String email;
	private String address;
	private int id_user = -1;
	
	private UserManagerRemote account = null;
	private SearchCameraRemote search = null;
	private CatalogManagerRemote catalog = null;
	private OrdersManagerRemote orders = null;
	
	List<String> brandList = null;
	
	Cart cart=null;
	
	public SessionBean(){
         try {
        	 InitialContext ic = new InitialContext();
		     account = (UserManagerRemote) ic.lookup("UserManager/remote");
		     catalog = (CatalogManagerRemote) ic.lookup("CatalogManager/remote");
		     search = (SearchCameraRemote) ic.lookup("SearchCameraClient/remote");
		     orders = (OrdersManagerRemote) ic.lookup("OrdersManager/remote");
		 } catch (Exception e) {
		 	e.printStackTrace();
		 }
	}
	
	public void setUserName(String username){
		this.username = username;
	}
	
	public void setPassWord(String password){
		this.password = password;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public void setAddress(String address){
		this.address=address;
	}
	
	public boolean login(){
		id_user = account.checkLogin(username, password);
		cart = new Cart(id_user);

		return id_user > 0;
	}
	
	public boolean register(){
		if (account.userExists(username)){
			return false;
		}
		account.addUser(username, password, email, address);
		return true;
	}
	
	public void loadBrands(){
		this.brandList = catalog.getAllBrands();
	}
	
	public List<String> getBrands(){

		brandList=catalog.getAllBrands();

		return brandList;
	}

	public List<Camera> getCameras(String brand){
		return catalog.getCameras(brand);
	}
	
	public Camera getCamera(Integer id){
		return catalog.getCamera(id);
	}

	public void removeCameraFromCart(String cameraid){
		cart.removeCamera(cameraid);
	}
	
	public void addToCart(Camera camera, int quantity){
		cart.addCamera(Integer.parseInt(camera.getIdCamera()), camera.getName(), quantity, camera.getPrice());
	}
	
	public Cart getCart(){
		return cart;
	}
	
	public boolean isLogged(){
		return id_user>0;
	}
	
	public boolean checkout(){
		try {
			orders.checkOut(cart);
			cart = new Cart(id_user);
			return true;
		} catch (CreditCardException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Camera search(String model){
		return search.search(model);
	}
	
	public List<Order> getOrders(){
		return orders.getOrdersOfUser(id_user);
	}
	
	public void logout(){
		id_user = -1;
	}
	
}
