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
import domain.DAOEntreprise;
import domain.IDAOContact;
import domain.IDAOEntreprise;

/**
 * Servlet implementation class AddEntreprise
 */
public class AddEntreprise extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEntreprise() {
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
		
		String numSiret =  request.getParameter("numSiret");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		
		String street = request.getParameter("street");
		String city = request.getParameter("city");
		String zip = request.getParameter("zip");
		String country = request.getParameter("country");
		
		String mobile = request.getParameter("mobile");
		String home = request.getParameter("home");
		String office = request.getParameter("office");
		
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		IDAOEntreprise dao = (IDAOEntreprise) context.getBean("DAOEntreprise");
		Contact c = dao.addEntreprise(numSiret,firstName, lastName, email,street,city,zip,country,mobile,home,office);
		PrintWriter out = response.getWriter();
		
		if(c==null){
			response.sendRedirect("addContact.jsp");
		} else {
			out.print( "<html><head><title>Ajouter un contact/entreprise</title></head><body><h1>Création de l'entreprise "+firstName+" "+lastName+" avec succès</h1></body></html>" );
		}
	}

}
