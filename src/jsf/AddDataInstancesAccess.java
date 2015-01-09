package jsf;

import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sun.istack.internal.logging.Logger;

import domain.Contact;
import domain.IDAOContact;

@ManagedBean(name="AddDataInstancesAccess")
@ViewScoped
public class AddDataInstancesAccess implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value="#{DAOContact}")
	private IDAOContact dao;
	
	@ManagedProperty(value="#{contact1}")
	private Contact contact1;
	
	@ManagedProperty(value="#{contact2}")
	private Contact contact2;
	
	@ManagedProperty(value="#{SuccesMessage}")
	private SuccesMessage em;
	
	public SuccesMessage getEm() {
		return em;
	}
	public void setEm(SuccesMessage em) {
		this.em = em;
	}
	public Contact getContact1() {
		return contact1;
	}
	public void setContact1(Contact contact1) {
		this.contact1 = contact1;
	}
	public Contact getContact2() {
		return contact2;
	}
	public void setContact2(Contact contact2) {
		this.contact2 = contact2;
	}
	public IDAOContact getDao() {
		return dao;
	}
	public void setDao(IDAOContact dao) {
		this.dao = dao;
	}

	public String check(){
		
		dao.addContact(
				contact1.getFirstName(), 
				contact1.getLastName(), 
				contact1.getEmail(), 
				contact1.getAddress().getStreet(),
				contact1.getAddress().getCity(),
				contact1.getAddress().getZip(),
				contact1.getAddress().getCountry(),
				contact1.getPhoneMobile(),
				contact1.getPhoneHome(),
				contact1.getPhoneOffice()
		);
		
		dao.addContact(
				contact2.getFirstName(), 
				contact2.getLastName(), 
				contact2.getEmail(), 
				contact2.getAddress().getStreet(),
				contact2.getAddress().getCity(),
				contact2.getAddress().getZip(),
				contact2.getAddress().getCountry(),
				contact2.getPhoneMobile(),
				contact2.getPhoneHome(),
				contact2.getPhoneOffice()
		);

		em.setMessage("Ajout des instances avec succès !");
		return "succes";
	}
}
