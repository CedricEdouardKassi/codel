package jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import domain.Contact;
import domain.ContactGroup;
import domain.IDAOContact;
import domain.IDAOGroup;

@ManagedBean(name="GetListGroupAccess")
@SessionScoped
public class GetListGroupAccess implements Serializable {
	
	@ManagedProperty(value="#{DAOGroup}")
	private IDAOGroup dao;
	
	private ArrayList<ContactGroup> cg;
	
	public IDAOGroup getDao() {
		return dao;
	}
	public void setDao(IDAOGroup dao) {
		this.dao = dao;
	}
	public ArrayList<ContactGroup> getCg() {
		return cg;
	}
	public void setCg(ArrayList<ContactGroup> cg) {
		this.cg = cg;
	}

	public String check(){
		cg = dao.getListGroup();
		return "list-group";
	}
	
}
