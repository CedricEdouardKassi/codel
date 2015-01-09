package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.LongType;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import util.HibernateUtil;

public class DAOGroup extends HibernateDaoSupport implements IDAOGroup {
	
	public Set<ContactGroup> getContactGroupsbyContactID(long id){
		DAOContact dao = new DAOContact();
		Contact e = dao.getContact(id);
		return e.getBooks();
	}

	public boolean addGroup(String name){
		ContactGroup cg = new ContactGroup();
		cg.setGroupName(name);
		this.getHibernateTemplate().saveOrUpdate(cg);
		return true;
	}
	
	public boolean addContactGroup(Contact c, ContactGroup g) {
		Set<ContactGroup> books = c.getBooks();
		Set<Contact> contacts = g.getContacts();
		g.getContacts().add(c);
		this.getHibernateTemplate().saveOrUpdate(g);
		
		c.getBooks().add(g);
		this.getHibernateTemplate().saveOrUpdate(c);
		return true;
	}

	public boolean deleteContactGroup(Contact e, long idContactGroup) {
		Set<ContactGroup> books = e.getBooks();
		Set<ContactGroup> newbooks = new HashSet<ContactGroup>();
		Iterator<ContactGroup> it = books.iterator();
		ContactGroup cg;
		
		while(it.hasNext()){
			cg = it.next();
			if(cg.getGroupId()!=idContactGroup){
				newbooks.add(cg);
			}else{
				e.getBooks().remove(cg);
				cg.getContacts().remove(e);
				this.getHibernateTemplate().saveOrUpdate(cg);
			}
		}
		e.setBooks(newbooks);
		this.getHibernateTemplate().saveOrUpdate(e);
		return true;
	}
	
	public boolean deleteGroup(int id){
		System.out.println("Debut delete");
		ContactGroup cg = this.getGroup(id);
		Iterator<Contact> it = cg.getContacts().iterator();
		while(it.hasNext()){
			Contact c = it.next();
			it.remove();
			this.deleteContactGroup(c, id);
		}
		this.getHibernateTemplate().delete(cg);
		return true;
	}
	
	public ArrayList<ContactGroup> getListGroup(){
		ArrayList<ContactGroup> groups = new ArrayList<ContactGroup>();
		List<ContactGroup> list;
		list = (List<ContactGroup>)this.getHibernateTemplate().find("from ContactGroup cg");
		Iterator<ContactGroup> iter = list.iterator();
		while(iter.hasNext()){
			ContactGroup g = iter.next();
			g.getContacts();
			groups.add(g);
		}
		return groups;
	}
	
	public ArrayList<ContactGroup> getBooksArrayNotIn(long idcontact){
		ArrayList<ContactGroup> groups = new ArrayList<ContactGroup>();
		List<ContactGroup> list;
		String sqlQuery = "SELECT DISTINCT cg.ID_CONTACTGROUP as contact_group_id FROM   ContactGroup_Table cg " +
				       "left JOIN Contact_Books_Table cgb" +
				         " ON cgb.ID_CONTACTGROUP = cg.ID_CONTACTGROUP" +
				" WHERE  cg.ID_CONTACTGROUP not in (select ID_CONTACTGROUP from " +
				         " Contact_Books_Table where ID_CONTACT = " + idcontact + ") " + 
				" group by cg.ID_CONTACTGROUP order by cg.ID_CONTACTGROUP";

				List ResultList = (List)this.getHibernateTemplate().execute(
				    new HibernateCallback() {
				    public Object doInHibernate(Session session) throws HibernateException{
				        SQLQuery sq = session.createSQLQuery(sqlQuery)
				        				.addScalar("contact_group_id", LongType.INSTANCE);
				        return sq.list();
				    }});

				for(int i=0;i<ResultList.size();i++){
				         groups.add(this.getGroup((long) ResultList.get(i)));
				}
				
		
		return groups;
	}
	
	public ContactGroup getGroup(long id){
		List <ContactGroup> list =  (List<ContactGroup>) this.getHibernateTemplate().find("from ContactGroup cg where cg.groupId =?", id);
		ContactGroup cg =(ContactGroup) list.get(0);
		cg.getContacts().size();
		Hibernate.initialize(cg.getContacts());
		return cg;
	}
}
