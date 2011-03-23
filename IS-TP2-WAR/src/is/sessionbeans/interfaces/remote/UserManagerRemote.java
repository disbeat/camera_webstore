package is.sessionbeans.interfaces.remote;
import javax.ejb.Remote;

@Remote
public interface UserManagerRemote {
	public void addUser(String username, String password, String email, String address);
	public Integer checkLogin(String name, String pass);
	public boolean userExists(String name);
}
