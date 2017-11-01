package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet{

	/**
	 * @param args
	 */
//	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		protected void service(
				HttpServletRequest request,
				HttpServletResponse response)
				throws ServletException,IOException
		{
			response.setContentType("text/html");
			PrintWriter w = response.getWriter();
			w.println("<h1>hello world but i want to play LOL</h1>");
		}

//	}

}
