package is.sessionbeans.interfaces.remote;
import java.util.Vector;

import javax.ejb.Remote;
import is.sharedObjects.Camera;

@Remote
public interface SearchCameraRemote {
	public Camera search(String model);
}
