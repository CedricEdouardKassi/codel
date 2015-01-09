package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Contact {

	private long id;
	private String firstName;
	private String lastName;
	private String email;
	private Address address;
	private Set<PhoneNumber> phones;
	private Set<ContactGroup> books;
	private Date version;
	
	public Contact(){
	}
	
	public Contact(String firstName, String lastName, String email,
			Address address, Set<PhoneNumber> phones, Set<ContactGroup> books) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.phones = phones;
		this.books = books;
	}

	public String getEmail(){
		return email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public String getFirstName(){
		return firstName;
	}
	
	public void setFirstName(String firstname){
		this.firstName = firstname;
	}
	
	
	public String getLastName(){
		return lastName;
	}
	
	public void setLastName(String lastname){
		this.lastName = lastname;
	}
	
	public long getId(){
		return id;
	}
	
	public void setId(long id){
		this.id = id;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Set<PhoneNumber> getPhones() {
		return phones;
	}

	public void setPhones(Set<PhoneNumber> phones) {
		this.phones = phones;
	}

	public Set<ContactGroup> getBooks() {
		return books;
	}
	
	public ArrayList<ContactGroup> getBooksArray(){
		ArrayList<ContactGroup> groups = new ArrayList<ContactGroup>();
		Iterator<ContactGroup> iter = books.iterator();
		while(iter.hasNext()){
			ContactGroup g = iter.next();
			groups.add(g);
		}
		return groups;
	}

	public void setBooks(Set<ContactGroup> books) {
		this.books = books;
	}

	public Date getVersion() {
		return version;
	}

	public void setVersion(Date version) {
		this.version = version;
	}
	
	public void setPhone(String kind, String num, Contact c){
		Iterator<PhoneNumber> it = phones.iterator();
		PhoneNumber p;
		while(it.hasNext()){
			p = it.next();
			if(p.getPhoneKind().equals(kind)){
				p.setPhoneNumber(num);
				return;
			}
		}
		p = new PhoneNumber();
		p.setPhoneKind(kind);
		p.setPhoneNumber(num);
		p.setContact(c);
		phones.add(p);
	}
	
	public String getPhoneHome(){
		Iterator<PhoneNumber> it = phones.iterator();
		PhoneNumber p;
		while(it.hasNext()){
			p = it.next();
			if(p.getPhoneKind().equals("home")){
				return p.getPhoneNumber();
			}
		}
		return "";
	}
	
	public String getPhoneOffice(){
		Iterator<PhoneNumber> it = phones.iterator();
		PhoneNumber p;
		while(it.hasNext()){
			p = it.next();
			if(p.getPhoneKind().equals("office")){
				return p.getPhoneNumber();
			}
		}
		return "";
	}
	
	public String getPhoneMobile(){
		Iterator<PhoneNumber> it = phones.iterator();
		PhoneNumber p;
		while(it.hasNext()){
			p = it.next();
			if(p.getPhoneKind().equals("mobile")){
				return p.getPhoneNumber();
			}
		}
		return "";
	}
}
