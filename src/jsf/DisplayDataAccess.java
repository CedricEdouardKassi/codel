package jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import domain.Contact;
import domain.ContactGroup;
import domain.Entreprise;
import domain.IDAOContact;
import domain.IDAOEntreprise;
import domain.IDAOGroup;

@ManagedBean(name="DisplayDataAccess")
@SessionScoped
public class DisplayDataAccess implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private Contact c;
	private Entreprise e;
	
	private String telOffice;
	private String telHome;
	private String telMobile;
	private String siret;
	
	@ManagedProperty(value="#{DAOContact}")
	private IDAOContact daoc;
	@ManagedProperty(value="#{DAOGroup}")
	private IDAOGroup daog;
	@ManagedProperty(value="#{DAOEntreprise}")
	private IDAOEntreprise daoe;
	@ManagedProperty(value="#{SuccesMessage}")
	private SuccesMessage sm;
	@ManagedProperty(value="#{ErrorMessage}")
	private ErrorMessage em;
	
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
	public IDAOEntreprise getDaoe() {
		return daoe;
	}
	public void setDaoe(IDAOEntreprise daoe) {
		this.daoe = daoe;
	}
	public String getSiret() {
		return siret;
	}
	public void setSiret(String siret) {
		this.siret = siret;
	}
	public Entreprise getE() {
		return e;
	}
	public void setE(Entreprise e) {
		this.e = e;
	}
	public Contact getC() {
		return c;
	}
	public void setC(Contact c) {
		this.c = c;
	}
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTelOffice() {
		return telOffice;
	}
	public void setTelOffice(String telOffice) {
		this.telOffice = telOffice;
	}
	public String getTelHome() {
		return telHome;
	}
	public void setTelHome(String telHome) {
		this.telHome = telHome;
	}
	public String getTelMobile() {
		return telMobile;
	}
	public void setTelMobile(String telMobile) {
		this.telMobile = telMobile;
	}

//	public ArrayList<ContactGroup> getBooksArrayNotIn(long idcontact){
//		return daog.getBooksArrayNotIn(idcontact);
//	}
	
	public String check(){
		System.out.println("Check --");
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		id = Integer.parseInt(params.get("id"));
		System.out.println("id : " + id);
		c = daoc.getContact(id);
		siret = daoe.getSiret(id);
		telHome = c.getPhoneHome();
		telMobile = c.getPhoneMobile();
		telOffice = c.getPhoneOffice();
		return "displayContact";
	}
	
//	public String modify(){
//		if(telHome!=null && telHome !=""){c.setPhone("home", telHome,c);}
//		if(telMobile!=null && telMobile !=""){c.setPhone("mobile", telMobile,c);}
//		if(telOffice!=null && telOffice !=""){c.setPhone("office", telOffice,c);}
//		
//		try {
//			daoc.modifyContact(c);
//		} catch(Exception e2){
//			em.setMessage("Error: Les données n'étaient plus à jour.");
//			return "error";
//		}
//		
//		String oldSiret = daoe.getSiret(id);
//		if (! oldSiret.equals("")){
//			if (siret.equals("")){
//				FacesContext context = FacesContext.getCurrentInstance();
//				context.addMessage(null, new FacesMessage("Le numero de siret est obligatoire pour une entreprise"));
//				siret = oldSiret;
//				return null;
//			}
//			e = daoe.getEntreprise(id);
//			e.setNumSiret(siret);
//			daoe.modifyEntreprise(e);
//		}
//		sm.setMessage("Modification du contact "+c.getFirstName()+" avec succès !");
//		return "succes";
//	}
	
//	public void validateSiret(FacesContext context, UIComponent ComponentToValidate, Object Value) 
//			throws ValidatorException{
//		String oldSiret = daoe.getSiret(id);
//		if (!oldSiret.equals("") && (siret.equals("") || siret == null)){
//			FacesMessage message = new FacesMessage("Le numero de siret est obligatoire pour une entreprise");
//			throw new ValidatorException(message);
//		}
//	}
}
