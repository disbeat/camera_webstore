package is.sessionbeans.interfaces.remote;
import javax.ejb.Remote;

@Remote
public interface SearchCameraRemote {
	public is.sharedObjects.Camera search(String model);
}
