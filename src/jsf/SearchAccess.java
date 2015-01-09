package jsf;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import domain.Contact;
import domain.IDAOContact;

@ManagedBean(name="SearchAccess")
@RequestScoped
public class SearchAccess implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String searchBy;
	private String term;
	private ArrayList<Contact> content;

	@PostConstruct
	public void init(){
		content = daoc.getContactByEmail("");
	}
	
	@ManagedProperty(value="#{DAOContact}")
	private IDAOContact daoc;
	
	public IDAOContact getDaoc() {
		return daoc;
	}
	public void setDaoc(IDAOContact daoc) {
		this.daoc = daoc;
	}
	public ArrayList<Contact> getContent() {
		return content;
	}
	public void setContent(ArrayList<Contact> test) {
		this.content = test;
	}
	public String getSearchBy() {
		return searchBy;
	}
	public void setSearchBy(String searchBy) {
		this.searchBy = searchBy;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	
	public String check(){
		if(searchBy.equals("firstName")){
			content = daoc.getContactByFirstName(term);
		} else if(searchBy.equals("lastName")){
			content = daoc.getContactByLastName(term);
		} else {
			content = daoc.getContactByEmail(term);
		}
		return null;
	}
	
	
}
