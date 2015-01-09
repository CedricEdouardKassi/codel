package domain;

public interface IDAOEntreprise {

	public Entreprise addEntreprise(String numSiret, String firstName, String lastName, String email, 
			String street, String city, String zip, String country,
			String mobile, String home, String office);
	public String getSiret(long id);
	public Entreprise getEntreprise(long id);
	public boolean modifyEntreprise(Entreprise e);
	
}
