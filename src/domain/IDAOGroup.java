package domain;

import java.util.ArrayList;
import java.util.Set;

public interface IDAOGroup {
	
	public Set<ContactGroup> getContactGroupsbyContactID(long id);
	public boolean addGroup(String name);
	public boolean addContactGroup(Contact c, ContactGroup g);
	public boolean deleteContactGroup(Contact e, long idContactGroup);
	public boolean deleteGroup(int id);
	public ContactGroup getGroup(long id);
	public ArrayList<ContactGroup> getListGroup();
	public ArrayList<ContactGroup> getBooksArrayNotIn(long idcontact);
	
}
