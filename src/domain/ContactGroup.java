package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class ContactGroup {

	private long groupId;
	private String groupName;
	private Set<Contact> contacts;
	private Date version;
	
	public ContactGroup(){
	}
	
	public ContactGroup(String groupName, Set<Contact> contacts) {
		super();
		this.groupName = groupName;
		this.contacts = contacts;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Set<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}

	public Date getVersion() {
		return version;
	}

	public void setVersion(Date version) {
		this.version = version;
	}
	
	
}
