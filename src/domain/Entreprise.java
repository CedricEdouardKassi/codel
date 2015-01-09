package domain;

import java.util.Set;

public class Entreprise extends Contact {

	private String numSiret;
	
	public Entreprise(String numSiret, String firstName, String lastName, String email,
			Address address, Set<PhoneNumber> phones, Set<ContactGroup> books) {
		super(firstName, lastName, email, address, phones, books);
		this.numSiret = numSiret;
	}
	
	public Entreprise(){
	}
	
	public String getNumSiret() {
		return numSiret;
	}

	public void setNumSiret(String numSiret) {
		this.numSiret = numSiret;
	}
	
}
