/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import bean.Customer;
import bean.CustomerSession;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Iris-
 */
public class LoginServlet extends HttpServlet {

    private Customer customer;
    private CustomerSession session;
    
    @Override
    protected void doGet(HttpServletRequest request, 
            HttpServletResponse response) 
            throws ServletException, IOException {
        
	session = new CustomerSession();
//        HttpSession session = request.getSession(true); 
        String username = (String)request.getParameter("username");
        String password = (String)request.getParameter("password");

        PrintWriter pw = response.getWriter();   
        if(request.getParameter("loginButton")!=null){
            
            if(session.login(username, password)){
                //shopping page
            }else{
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/LoginAgain.jsp");
                dispatcher.forward(request, response);
            }
        }else if(request.getParameter("registerButton")!=null){
            session.createTable();
            session.register(username, password);
            pw.println("<!DOCTYPE HTML PUBLIC " +
                   "-//W3C//DTD HTML 4.0 Transitional//EN"  + ">\n" +
                           "<HTML>\n" + "<HEAD>\n" + "</HEAD>\n" + "<BODY>\n" +
                           "<H1>register successful" +"</H1>\n" +
                           "<P> "+"</P>\n"+
                           "</BODY>\n</HTML>\n");
        }
        
    }
    
    @Override
    public void doPost(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException{
        doGet(request, response);                       
    }



}
