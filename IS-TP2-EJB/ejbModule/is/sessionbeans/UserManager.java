package is.sessionbeans;

import is.entities.CameraEntity;
import is.entities.OrderEntity;
import is.entities.OrderItemEntity;
import is.entities.UserAccountEntity;
import is.sessionbeans.interfaces.remote.UserManagerRemote;

import java.util.Arrays;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class UserRegister
 */
@Stateless
public class UserManager implements UserManagerRemote {

	@PersistenceContext( unitName="IS_TP2_DB" )
	EntityManager em;
    /**
     * Default constructor. 
     */
    public UserManager() {
    	
    }
    
    public UserManager(EntityManager em){
    	this.em = em;
    }
    

    
    public UserAccountEntity getUser(String name)
    {
    	Query q = em.createQuery("SELECT c FROM UserAccountEntity c WHERE c.name = ?");
    	q.setParameter(1, name);
    	try {
    		UserAccountEntity user = (UserAccountEntity)q.getSingleResult();
    		return user;
    	} catch(NoResultException nr)
    	{
    		return null; //no user was found
    	}
    }
    
    public UserAccountEntity getUser(Integer id){
    	if (id == null)
    		return null;
    	Query q = em.createQuery("SELECT c FROM UserAccountEntity c WHERE c.id = ?");
    	q.setParameter(1, id);
    	try {
    		return (UserAccountEntity) q.getSingleResult();
    	} catch(NoResultException nr){
    		return null; //no user was found
    	}
    }
    public Integer checkLogin(String name, String pass)
    {
    	Query q = em.createQuery("SELECT c.id FROM UserAccountEntity c WHERE c.username = ? and c.password = ?");
    	q.setParameter(1, name);
    	q.setParameter(2, pass);
    	try {
    		return (Integer)q.getSingleResult();
    	} catch(NoResultException nr)
    	{
    		return -1; 
    	}
    }
    
    public void addUser(String username, String password, String email, String address){
    	
		UserAccountEntity user = new UserAccountEntity(username, password, email, address);
		
		em.persist(user);
    	em.flush();
    	
    	
	}
    
    
    public boolean userExists(String name){
    	Query q = em.createQuery("SELECT c.id FROM UserAccountEntity c WHERE c.username = ?");
    	q.setParameter(1, name);
    	try {
    		return q.getSingleResult() != null;
    	} catch(NoResultException nr)
    	{
    		return false; 
    	}
    }
    
}
