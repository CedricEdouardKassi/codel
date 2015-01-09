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
import domain.DAOGroup;
import domain.IDAOContact;
import domain.IDAOGroup;

/**
 * Servlet implementation class DeleteContactGroup
 */
public class DeleteContactGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteContactGroup() {
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
		response.setContentType("text/html");
		
		long idContact = Integer.parseInt(request.getParameter("idContact"));
		long idContactGroup = Integer.parseInt(request.getParameter("idContactGroup"));
		
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		
		IDAOContact daoc = (IDAOContact) context.getBean("DAOContact");
		Contact contact = daoc.getContact(idContact);
		
		IDAOGroup daog = (IDAOGroup) context.getBean("DAOGroup");
		boolean c = daog.deleteContactGroup(contact,idContactGroup);
		PrintWriter out = response.getWriter();
		
		if(c==true){
			out.print( "<html><head><title>Succes</title></head><body><h1>suppression du groupe avec succes</h1></body></html>" );
		} else {
			out.print( "<html><head><title>Erreur</title></head><body><h1>Erreur suppression group name</h1></body></html>" );
		}
	}

}
