package com.suni.web.login;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;



/**
 * Servlet implementation class UserControllerServlet
 */
@WebServlet("/UserControllerServlet")
public class UserControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDbUtil userDbUtil;
    
    @Resource(name = "jdbc/userdb")
	private DataSource dataSource;
    
    @Override
	public void init() throws ServletException {
		super.init();

		// Create student db util and pass in the connection pool.
		try {
			userDbUtil = new UserDbUtil(dataSource);
		} catch (Exception e) {
			throw new ServletException();
		}
	}
    public UserControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// read the "command" parameter
		try {
			String theCommand=request.getParameter("command");
			if (theCommand == null) {
				theCommand = "SIGNUP";
			}
			switch(theCommand) {
			case "SIGNUP":
				addUser(request,response);
				break;
			case "LOGIN":
				loginUser(request,response);
			default:
				addUser(request, response);

			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	private void loginUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//read user info from form
		String userName=request.getParameter("username");
		String password=request.getParameter("password");
		
		//validate user name password
		boolean valid=userDbUtil.validateUser(userName,password);
		
		//if valid redirect into Home page
		if(valid) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/home-page.jsp");
			dispatcher.forward(request, response);
		}
		
	}

	private void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//read user info from form.
		String userName=request.getParameter("username");
		String password=request.getParameter("password");
		//String conPassword=request.getParameter("con_password");
		String email=request.getParameter("email");
		String country=request.getParameter("country");
		
		//create User object
		User theUser=new User(userName,password,email,country);
		
		//add user to database
		userDbUtil.addUser(theUser);
		//Display success message
		RequestDispatcher dispatcher = request.getRequestDispatcher("/success-page.jsp");
		dispatcher.forward(request, response);
		
	}

}
