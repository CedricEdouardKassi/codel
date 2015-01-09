package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.aspectj.weaver.Iterators;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateOptimisticLockingFailureException;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import util.HibernateUtil;

public class DAOContact extends HibernateDaoSupport implements IDAOContact {

	/**
	 * Rajoute un contact dans la base de donnees.
	 * @param firstname
	 * @param lastname
	 * @param email
	 * @param country 
	 * @param zip 
	 * @param city 
	 * @param street 
	 * @param office 
	 * @param home 
	 * @param mobile 
	 * @return renvoit le nouveau contact
	 */
	//Data Access Object
	public Contact addContact(String firstname, String lastname, String email, 
			String street, String city, String zip, String country, 
			String mobile, String home, String office){

		Contact e = new Contact();
		
		Set<PhoneNumber> phones = new HashSet<PhoneNumber>();
		
		PhoneNumber pn1 = new PhoneNumber();
		PhoneNumber pn2 = new PhoneNumber();
		PhoneNumber pn3 = new PhoneNumber();
		
		if(mobile!=""){
			pn1.setPhoneKind("mobile");
			pn1.setPhoneNumber(mobile);
			phones.add(pn1);
			pn1.setContact(e); //<-A modifier
		}
		
		if(home!=""){
			pn2.setPhoneKind("home");
			pn2.setPhoneNumber(home);
			phones.add(pn2);
			pn2.setContact(e); //<-A modifier
		}
		
		if(office!=""){
			pn3.setPhoneKind("office");
			pn3.setPhoneNumber(office);
			phones.add(pn3);
			pn3.setContact(e); //<-A modifier
		}
		
		Address address = new Address();
		address.setStreet(street);
		address.setCity(city);
		address.setZip(zip);
		address.setCountry(country);
		
		// Contact
		e.setFirstName(firstname);
		e.setLastName(lastname);
		e.setEmail(email);
		e.setAddress(address);
		e.setPhones(phones);
		
		this.getHibernateTemplate().save(e);

		return e;
		
	}
	
	public Contact addContact(Contact e){
		this.getHibernateTemplate().save(e);
		return e;
	}
	/**
	 * Suppresion d'un contact a partir de son identifiant
	 * @param id
	 * @return vrai si la suppression a bien ete effectuee
	 */
	public int deleteContact(long id){
		int success = 0;
		
		Contact c = getContact(id);
		DAOGroup dcg = new DAOGroup();
		for (ContactGroup cg: c.getBooks()){
			cg.getContacts().remove(c);
			this.getHibernateTemplate().saveOrUpdate(cg);
			//dcg.deleteContactGroup(c, cg.getGroupId());
		}
		this.getHibernateTemplate().delete(c);
		return success;
	}

	/**
	 * Recuperation d'un contact a partir de son identifiant
	 * @param id
	 * @return
	 */
	public Contact getContact(long id){
		List <Contact> list =  (List<Contact>) this.getHibernateTemplate().find("from Contact c where c.id =?", id);
		Contact c =(Contact) list.get(0);
		Hibernate.initialize(c.getAddress());
		Hibernate.initialize(c.getPhones());
		Hibernate.initialize(c.getBooks());
		return c;
	}

	/**
	 * Methode qui modifie les coordonees d'un contact
	 * @param id
	 * @param firstname
	 * @param alstname
	 * @param email
	 * @param country 
	 * @param zip 
	 * @param city 
	 * @param street 
	 * @return
	 */
	public boolean modifyContact(long id, String firstname, String lastname, String email, 
			String street, String city, String zip, String country,
			String mobile, String home, String office){

		Contact e = getContact(id);
		
		Set<PhoneNumber> phones = e.getPhones();
		Set<PhoneNumber> phonesUpdate = new HashSet<PhoneNumber>();
		
		Iterator<PhoneNumber> Itp = phones.iterator();
		PhoneNumber pn;
		
		while(Itp.hasNext()){
			pn = Itp.next();
			if(pn.getPhoneKind().equals("mobile")){
				pn.setPhoneNumber(mobile);
				pn.setContact(e);//<-A modifier
				phonesUpdate.add(pn);
			}
			if(pn.getPhoneKind().equals("home")){
				pn.setPhoneNumber(home);
				pn.setContact(e);//<-A modifier
				phonesUpdate.add(pn);
			}
			if(pn.getPhoneKind().equals("office")){
				pn.setPhoneNumber(office);
				pn.setContact(e);//<-A modifier
				phonesUpdate.add(pn);
			}
		}

		Address address = e.getAddress();
		address.setStreet(street);
		address.setCity(city);
		address.setZip(zip);
		address.setCountry(country);
		
		// Contact
		e.setFirstName(firstname);
		e.setLastName(lastname);
		e.setEmail(email);
		e.setAddress(address);
		e.setPhones(phonesUpdate);
		
		try {
			this.getHibernateTemplate().saveOrUpdate(e);
		} catch (Exception e2) {
			return false;
		}
		
		return true;
	}
	
	public boolean modifyContact(Contact e){
		this.getHibernateTemplate().saveOrUpdate(e);
		System.out.println("Cache Hit : " + this.getHibernateTemplate().getSessionFactory().getStatistics().getSecondLevelCacheHitCount());
		return true;
	}

	/**
	 * Renvoit la liste des contacts correspondant au prenom firrstname
	 * @param firstname
	 * @return
	 */
	public ArrayList<Contact> getContactByFirstName(String firstname){
		ArrayList<Contact> contacts = new ArrayList<Contact>();
		List<Contact> list;
		if(firstname.equals("")){
			list = (List<Contact>)this.getHibernateTemplate().find("from Contact contact");
		} else {
			list = (List<Contact>)this.getHibernateTemplate().find("from Contact contact where contact.firstName = ?", firstname);
		}
		Iterator<Contact> iter = list.iterator();
		while(iter.hasNext()){
			Contact c = iter.next();
			c.getAddress();
			c.getBooks();
			c.getPhones();
			contacts.add(c);
		}
		return contacts;
	}


	/**
	 * Renvoit la liste des contacts correspondant au nom lastname
	 * @param lastname
	 * @return
	 */
	public ArrayList<Contact> getContactByLastName(String lastname){
		ArrayList<Contact> contacts = new ArrayList<Contact>();
		List<Contact> list;
		if(lastname.equals("")){
			list = (List<Contact>)this.getHibernateTemplate().find("from Contact contact");
		} else {
			list = (List<Contact>)this.getHibernateTemplate().find("from Contact contact where contact.lastName = ?",lastname);
		}
		Iterator<Contact> iter = list.iterator();
		while(iter.hasNext()){
			Contact c = iter.next();
			c.getAddress();
			c.getBooks();
			c.getPhones();
			contacts.add(c);
		}
		return contacts;
	}

	/**
	 * Renvoit la liste des contacts correspondant a l'email email
	 * @param email
	 * @return
	 */
	public ArrayList<Contact> getContactByEmail(String email){	
		ArrayList<Contact> contacts = new ArrayList<Contact>();
		List<Contact> list;
		if(email.equals("")){
			list = (List<Contact>)this.getHibernateTemplate().find("from Contact contact");
		} else {
			list = (List<Contact>)this.getHibernateTemplate().find("from Contact contact where contact.email = ?",email);
		}
		Iterator<Contact> iter = list.iterator();
		while(iter.hasNext()){
			Contact c = iter.next();
			c.getAddress();
			c.getBooks();
			c.getPhones();
			contacts.add(c);
		}
		return contacts;
	}
	
}
