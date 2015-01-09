package jsf;

import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import domain.Contact;
import domain.ContactGroup;
import domain.IDAOContact;
import domain.IDAOGroup;

@ManagedBean(name="DeleteContactGroupDataAccess")
@ViewScoped
public class DeleteContactGroupDataAccess  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(DeleteContactGroupDataAccess.class);
	
	@ManagedProperty(value="#{DAOContact}")
	private IDAOContact daoc;
	@ManagedProperty(value="#{DAOGroup}")
	private IDAOGroup daog;
	
	@ManagedProperty(value="#{SuccesMessage}")
	private SuccesMessage sm;
	@ManagedProperty(value="#{ErrorMessage}")
	private ErrorMessage em;
	
	public IDAOContact getDaoc() {
		return daoc;
	}
	public void setDaoc(IDAOContact daoc) {
		this.daoc = daoc;
	}
	public IDAOGroup getDaog() {
		return daog;
	}
	public void setDaog(IDAOGroup daog) {
		this.daog = daog;
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

	public String check(){
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		long idcontact = Integer.parseInt(params.get("idcontact"));
		long idgroup = Integer.parseInt(params.get("idgroup"));
		
		Contact c = daoc.getContact(idcontact);
		daog.deleteContactGroup(c,idgroup);
		
		logger.debug("Suppression du contact : "+idcontact);
		sm.setMessage("Suppression du contact du groupe avec succès !");
		
		return "succes";
	}
	
}
