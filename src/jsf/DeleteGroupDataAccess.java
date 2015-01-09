package jsf;

import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import domain.IDAOGroup;

@ManagedBean(name="DeleteGroupDataAccess")
@ViewScoped
public class DeleteGroupDataAccess  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty(value="#{DAOGroup}")
	private IDAOGroup dao;
	@ManagedProperty(value="#{SuccesMessage}")
	private SuccesMessage em;
	
	private int id;
	
	public IDAOGroup getDao() {
		return dao;
	}
	public void setDao(IDAOGroup dao) {
		this.dao = dao;
	}
	public SuccesMessage getEm() {
		return em;
	}
	public void setEm(SuccesMessage em) {
		this.em = em;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String check(){
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		dao.deleteGroup(Integer.parseInt(params.get("idgroup")));
		em.setMessage("Suppresion du groupe avec succès !");
		return "succes";
	}
}
