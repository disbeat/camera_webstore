package is.sessionbeans;

import is.entities.CameraEntity;
import is.sessionbeans.interfaces.remote.SearchCameraRemote;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.camerasupplierws.Camera;
import com.camerasupplierws.CameraSearch;
import com.camerasupplierws.CameraSearchService;

/**
 * Session Bean implementation class SearchCamera
 */
@Stateless
public class SearchCameraClient implements SearchCameraRemote {

	@PersistenceContext( unitName="IS_TP2_DB" )
	EntityManager em;
	
	public is.sharedObjects.Camera search(String model){
		CameraSearchService camSearchService = new CameraSearchService();
		CameraSearch camSearch = camSearchService.getCameraSearchPort();
		
		Camera cam = camSearch.searchCamera(model);
		if (cam == null)
			return null;
		
		CatalogManager manager = new CatalogManager(em);
		CameraEntity camEntity = new CameraEntity(cam); 
		camEntity = manager.addCamera(camEntity);
		
		return manager.convertEntityToSharedObject(camEntity);
		
		
	}
}
