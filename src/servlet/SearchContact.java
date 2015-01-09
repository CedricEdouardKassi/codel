package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import domain.Contact;
import domain.DAOContact;
import domain.IDAOContact;

/**
 * Servlet implementation class SearchContact
 */
public class SearchContact extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchContact() {
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
		
		response.setContentType("text/html");
		
		String searchby = request.getParameter("searchby");
		String term = request.getParameter("term");
		
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		IDAOContact dao = (IDAOContact) context.getBean("DAOContact");
		ArrayList<Contact> cs;
		
		if(searchby.equals("firstName")){
			cs = dao.getContactByFirstName(term);
		} else if(searchby.equals("lastName")){
			cs = dao.getContactByLastName(term);
		} else {
			cs = dao.getContactByEmail(term);
		}
		
		PrintWriter out = response.getWriter();
		if(cs.size()==0){
			out.print("<h1>Aucun résultat pour votre recherche</h1>");
		} else {
			out.print("<table style=\"width:100%;\">" );
			out.print("<tr>");
			out.print("<th>ID</td>");
			out.print("<th>First Name</td>");
			out.print("<th>Last Name</td>");
			out.print("<th>Email</td>");
			out.print("<th>Modifier</th>");
			out.print("<th>Supprimer</th>");
			out.print("</tr>");
			for (int i = 0; i < cs.size(); i++){
				out.print("<tr>");
				out.print("<td>"+cs.get(i).getId()+"</td>");
				out.print("<td>"+cs.get(i).getFirstName()+"</td>");
				out.print("<td>"+cs.get(i).getLastName()+"</td>");
				out.print("<td>"+cs.get(i).getEmail()+"</td>");
				out.print("<td><form action=\"updateContact.jsp\" method=\"post\"><input type=\"hidden\" name=\"id\" value=\""+cs.get(i).getId()+"\" /><input type=\"submit\" value=\"Modifier\" action=\"modify\"></form></td>");
				out.print("<td><form action=\"DeleteContact\" method=\"post\"><input type=\"hidden\" name=\"idContact\" value=\""+cs.get(i).getId()+"\" /><input type=\"submit\" value=\"Supprimer\" action=\"delete\"></form></td>");
				out.print("</tr>");
			}
			out.print( "</table>" );
		}
		
	}

}
