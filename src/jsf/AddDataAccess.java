package jsf;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import domain.IDAOContact;
import domain.IDAOEntreprise;

@ManagedBean(name="AddDataAccess")
@ViewScoped
public class AddDataAccess implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value="#{DAOContact}")
	private IDAOContact daoc;
	@ManagedProperty(value="#{DAOEntreprise}")
	private IDAOEntreprise daoe;
	@ManagedProperty(value="#{SuccesMessage}")
	private SuccesMessage sm;
	@ManagedProperty(value="#{ErrorMessage}")
	private ErrorMessage em;
	
	private static final Logger logger = Logger.getLogger(AddDataAccess.class);
	
	private String firstName;
	private String lastName;
	private String email;
	private String street;
	private String city;
	private String zip;
	private String country;
	private String mobile;
	private String home;
	private String office;
	private String entreprise;
	private String numSiret;
	
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
	public IDAOContact getDaoc() {
		return daoc;
	}
	public void setDaoc(IDAOContact daoc) {
		this.daoc = daoc;
	}
	public IDAOEntreprise getDaoe() {
		return daoe;
	}
	public void setDaoe(IDAOEntreprise daoe) {
		this.daoe = daoe;
	}
	public String getNumSiret() {
		return numSiret;
	}
	public void setNumSiret(String numSiret) {
		this.numSiret = numSiret;
	}
	public String getEntreprise() {
		return entreprise;
	}
	public void setEntreprise(String entreprise) {
		this.entreprise = entreprise;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getHome() {
		return home;
	}
	public void setHome(String home) {
		this.home = home;
	}
	public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}
	
	public String check(){
		if(numSiret == null || numSiret.equals("")){
			daoc.addContact(firstName, lastName, email, street, city, zip, country, mobile, home, office);
			logger.debug("Création du contact "+firstName+ " "+lastName);
		} else {
			daoe.addEntreprise(numSiret, firstName, lastName, email, street, city, zip, country, mobile, home, office);
			logger.debug("Création de l'entreprise "+firstName+ " "+lastName);
		}
		sm.setMessage("Ajout du contact "+firstName+" avec succès !");
		return "succes";
	}
}
