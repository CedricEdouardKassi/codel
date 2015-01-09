package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import util.HibernateUtil;
import domain.Contact;
import domain.DAOContact;
import domain.IDAOContact;

/**
 * Servlet implementation class AddContactInstances
 */
public class AddContactInstances extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddContactInstances() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		
		IDAOContact dao = (IDAOContact) context.getBean("DAOContact");
		
		Contact contact1 = (Contact) context.getBean("contact1");
		dao.addContact(
				contact1.getFirstName(), 
				contact1.getLastName(), 
				contact1.getEmail(), 
				contact1.getAddress().getStreet(),
				contact1.getAddress().getCity(),
				contact1.getAddress().getZip(),
				contact1.getAddress().getCountry(),
				contact1.getPhoneMobile(),
				contact1.getPhoneHome(),
				contact1.getPhoneOffice()
		);
		
		Contact contact2 = (Contact) context.getBean("contact2");
		dao.addContact(
				contact2.getFirstName(), 
				contact2.getLastName(), 
				contact2.getEmail(), 
				contact2.getAddress().getStreet(),
				contact2.getAddress().getCity(),
				contact2.getAddress().getZip(),
				contact2.getAddress().getCountry(),
				contact2.getPhoneMobile(),
				contact2.getPhoneHome(),
				contact2.getPhoneOffice()
		);
		
		PrintWriter out = response.getWriter();
		out.print("<html><head><title>Instances créée</title></head><body><h1>Création du contact " + contact1.getFirstName() +" & "+contact2.getFirstName()+" de avec succès</h1></body></html>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
