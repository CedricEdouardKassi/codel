package jsf;

import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import domain.IDAOContact;

@ManagedBean(name="DeleteDataAccess")
@ViewScoped
public class DeleteDataAccess implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(DeleteContactGroupDataAccess.class);
	
	@ManagedProperty(value="#{DAOContact}")
	private IDAOContact daoc;
	@ManagedProperty(value="#{SuccesMessage}")
	private SuccesMessage em;
	
	public SuccesMessage getEm() {
		return em;
	}
	public void setEm(SuccesMessage em) {
		this.em = em;
	}
	public IDAOContact getDaoc() {
		return daoc;
	}
	public void setDaoc(IDAOContact daoc) {
		this.daoc = daoc;
	}
	
	public String check(){
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		int id = Integer.parseInt(params.get("id"));
		daoc.deleteContact(id);
		logger.debug("Suppression du contact : "+id);
		em.setMessage("Suppression du contact avec succès !");
		return "succes";
	}

}
