package domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import util.HibernateUtil;

public class DAOEntreprise extends HibernateDaoSupport implements IDAOEntreprise {

	public Entreprise addEntreprise(String numSiret, String firstName,
			String lastName, String email, 
			String street, String city, String zip, String country,
			String mobile, String home, String office) {
		
		Entreprise e = new Entreprise();
		
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
		e.setFirstName(firstName);
		e.setLastName(lastName);
		e.setEmail(email);
		e.setAddress(address);
		e.setPhones(phones);
		e.setNumSiret(numSiret);
		
		this.getHibernateTemplate().save(e);
		
		return e;
	}

	public Entreprise getEntreprise(long id){
		List <Entreprise> list =  (List<Entreprise>) this.getHibernateTemplate().find("from Entreprise e where e.id =?", id);
		if (list.isEmpty()) return null;
		Entreprise e =(Entreprise) list.get(0);
		Hibernate.initialize(e.getAddress());
		Hibernate.initialize(e.getPhones());
		Hibernate.initialize(e.getBooks());
		return e;
	}
	
	public boolean modifyEntreprise(Entreprise e){
		this.getHibernateTemplate().saveOrUpdate(e);
		return true;
	}
	
	public String getSiret(long id) {
		Entreprise e = this.getEntreprise(id);
		if (e != null){
			return e.getNumSiret();
		}
		return "";
	}

}
