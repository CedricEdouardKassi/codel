package jsf;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import domain.IDAOGroup;

@ManagedBean(name="AddGroupDataAccess")
@ViewScoped
public class AddGroupDataAccess  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty(value="#{DAOGroup}")
	private IDAOGroup dao;
	@ManagedProperty(value="#{SuccesMessage}")
	private SuccesMessage sm;
	@ManagedProperty(value="#{ErrorMessage}")
	private ErrorMessage em;
	
	private String groupName;
	
	private static final Logger logger = Logger.getLogger(AddGroupDataAccess.class);

	public IDAOGroup getDao() {
		return dao;
	}
	public void setDao(IDAOGroup dao) {
		this.dao = dao;
	}
	public SuccesMessage getSm() {
		return sm;
	}
	public void setSm(SuccesMessage sm) {
		this.sm = sm;
	}
	
	public ErrorMessage getEm() {
		return em;
	}
	public void setEm(ErrorMessage em) {
		this.em = em;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String check(){
		try{
			dao.addGroup(groupName);
		} catch(Exception e2){
			em.setMessage("Erreur: Le groupe existe déjà.");
			return "error";
		}
		
		logger.debug("Création du groupe "+groupName);
		sm.setMessage("Création du groupe avec succès !");
		return "succes";
	}
}
