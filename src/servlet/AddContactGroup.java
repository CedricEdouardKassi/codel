package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import domain.Contact;
import domain.DAOContact;
import domain.DAOGroup;
import domain.IDAOContact;
import domain.IDAOGroup;

/**
 * Servlet implementation class AddContactGroup
 */
public class AddContactGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddContactGroup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		
		String nameGroupContact = request.getParameter("nameGroupContact");
		long idContact = Integer.parseInt(request.getParameter("idContact"));
		
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		
		IDAOContact daoc = (IDAOContact) context.getBean("DAOContact");
		Contact contact = daoc.getContact(idContact);
		
		IDAOGroup daog = (IDAOGroup) context.getBean("DAOGroup");
		boolean c = true; //daog.addContactGroup(contact,nameGroupContact);
		PrintWriter out = response.getWriter();
		
		if(c==true){
			out.print( "<html><head><title>Succes</title></head><body><h1>Ajouter dans le groupe avec succes</h1></body></html>" );
		} else {
			out.print( "<html><head><title>Erreur</title></head><body><h1>Erreur ajout group name</h1></body></html>" );
		}
	}

}
