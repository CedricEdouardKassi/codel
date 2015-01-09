<%@
page import="java.util.*" import="domain.*" import="org.springframework.web.context.support.WebApplicationContextUtils"
import="org.springframework.context.ApplicationContext"
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modifier un contact</title>
<link href="css/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="scripts/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="scripts/lib.main.js"></script>
</head>
<body>
<%

	int idContact =  Integer.parseInt(request.getParameter("id"));
	ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
	IDAOContact dao = (IDAOContact) context.getBean("DAOContact");

	Contact c = dao.getContact(idContact);
	
	long id = c.getId();
	String firstName = c.getFirstName();
	String lastName = c.getLastName();
	String email = c.getEmail();
	
	Address adr = c.getAddress();
	
	String street = adr.getStreet();
	String city = adr.getCity();
	String zip = adr.getZip();
	String country = adr.getCountry();
	
	Set<PhoneNumber> phones = c.getPhones();
	PhoneNumber p;
	String mobile = c.getPhoneMobile();
	String home = c.getPhoneHome();
	String office = c.getPhoneOffice();
	
%>
<ul id="btnMenuHeader">
	<li><a href="AddContactInstances">Ajouter des instances</a></li>
	<li><a href="index.jsp">Accueil</a></li>
</ul>
<div class="page">
<h1>Modifier un contact</h1>
<form method="post" action="UpdateContact">
<input type="hidden" name="id" value="<% out.print(id); %>">
<table>
	<tr>
		<tr>
			<td>
				<label>First Name:</label>
				<input type="text" name="firstName" value="<% out.print(firstName); %>">
			</td>
			<td>
				<label>Last Name:</label>
				<input type="text" name="lastName" value="<% out.print(lastName); %>">
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<label>Email:</label>
				<input type="text" name="email" value="<% out.print(email); %>">
			</td>
		</tr>
		
		<tr>
			<td>
				<label>Street:</label>
				<input type="text" name="street" value="<% out.print(street); %>">
			</td>
			<td>
				<label>City:</label>
				<input type="text" name="city" value="<% out.print(city); %>">
			</td>
		</tr>
		<tr>
			<td>
				<label>Zip:</label>
				<input type="text" name="zip" value="<% out.print(zip); %>">
			</td>
			<td>
				<label>Country:</label>
				<input type="text" name="country" value="<% out.print(country); %>">
			</td>
		</tr>
		
		<tr>
			<td>
				<label>Tèl. Mobile:</label>
				<input type="text" name="mobile" value="<% out.print(mobile); %>">
			</td>
			<td>
				<label>Tèl. Home:</label>
				<input type="text" name="home" value="<% out.print(home); %>">
			</td>
		</tr>
		<tr>
			<td><label>Tèl. Office:</label><input type="text" name="office" value="<% out.print(office); %>"></td>
		</tr>
	</table>
	<hr/>
	<input class="button" type="submit" value="Submit" /><input class="button" type="reset" value="Reset">
</form>

<hr/>
<h1>Groupes</h1>
<% 
	DAOGroup daog = new DAOGroup();
	Set<ContactGroup> cg = c.getBooks();
	Iterator<ContactGroup> itcg = cg.iterator();
	ContactGroup gr;
	int nbContact = 0;

	String chaine = "";
	while(itcg.hasNext()){
		gr = itcg.next();
		nbContact++;
		chaine += "<tr>";
		chaine += "<td>"+gr.getGroupName()+"</td>";
		chaine += "<td><form action=\"DeleteContactGroup\" method=\"post\">";
		chaine += "<input type=\"hidden\" name=\"idContact\" value=\""+idContact+"\" />";
		chaine += "<input type=\"hidden\" name=\"idContactGroup\" value=\""+gr.getGroupId()+"\" />";
		chaine += "<input type=\"submit\" value=\"Supprimer\" action=\"delete\"></form></td>";
		chaine += "</tr>";
	}
	if(nbContact==0){
		out.print("<tr><div class=\"error\">Vous avez aucun groupe pour le moment !</div></tr>");
	} else {
		out.print("<table><tr><th>Goupes</th><th>Supprimer</th></tr>"+chaine+"</table>");
	}
	
%>

<hr/>
<h1>Ajouter un groupe</h1>
<form method="post" action="AddContactGroup" >
	<input type="hidden" name="idContact" value="<% out.print(idContact); %>" />
	<label>Nom du groupe</label><br/>
	<input id="NewNomGroupContact" name="NewNomGroupContact" type="text" /><input id="add_group_name" type="button" value="Add" /><br/>
	<select id="NameGroupContact" name="nameGroupContact">
		<option value="Famille" selected="selected">Famille</option>
		<option value="Amis">Amis</option>
		<%
			Iterator<ContactGroup> ip = cg.iterator();
			ContactGroup grb;
			while(itcg.hasNext()){
				grb = ip.next();
				out.print("<option value=\""+grb.getGroupName()+"\">"+grb.getGroupName()+"</option>");
			}
		%>
	</select><br/>
	<input type="submit" name="Valider" />
</form>
</div>
</body>
</html>