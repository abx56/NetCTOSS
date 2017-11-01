package com.tarena.web;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tarena.dao.AdminDao;
import com.tarena.dao.AdminDaoImpl;
import com.tarena.dao.CostDao;
import com.tarena.dao.CostDaoImpl;
import com.tarena.entity.Admin;
import com.tarena.entity.Cost;
import com.tarena.util.ImageUtil;

public class MainServlet extends HttpServlet{
	
	@Override
	protected void service(
			HttpServletRequest req,
			HttpServletResponse res)
			throws ServletException,IOException{
		
		req.setCharacterEncoding("utf-8");
		
		String uri = req.getRequestURI();
		
		if(uri.contains("login/toLogin")){
			//�򿪵�¼ҳ��
			toLogin(req,res);
		}else if(uri.contains("login/createImage")){
			//������֤��
			createImage(req,res);
		}else if(uri.contains("login/toIndex")){
			//����ҳ
			toIndex(req,res);
		}else if(uri.contains("login/login")){
			//��¼��֤
			login(req,res);
		}else if(uri.contains("cost/find")){
			//�����ѯ����
			findCost(req,res);
		}
		
	}
	//��ѯ�ʷ�����
	private void findCost(HttpServletRequest req, HttpServletResponse res) 
					throws ServletException,IOException{
		
		CostDao dao=new CostDaoImpl();
		List<Cost> list =dao.findAll();
		req.setAttribute("cost", list);
		req.getRequestDispatcher("../WEB-INF/cost/cost_list.jsp").forward(req,res);
		
	}
	//��¼��֤
	private void login(HttpServletRequest req, HttpServletResponse res) 
					throws ServletException,IOException{
		// TODO Auto-generated method stub
		String adminCode = req.getParameter("adminCode");
		String password = req.getParameter("password");
		String code = req.getParameter("code");
		String imageCode = (String)req.getSession().getAttribute("imageCode");
		if(code==null|| code.equals("") || !code.equalsIgnoreCase(imageCode)){
			req.setAttribute("error", "��֤�����");
			req.setAttribute("adminCode", adminCode);
			req.setAttribute("password", password);
			req.getRequestDispatcher("../WEB-INF/main/login.jsp").forward(req,res);
			return; 
			}
		
			AdminDao dao = new AdminDaoImpl();
			Admin a = dao.findByCode(adminCode);
			if(a==null){
				req.setAttribute("error", "�˺Ų����ڣ�");
				req.setAttribute("adminCode", adminCode);
				req.setAttribute("password", password);
				req.getRequestDispatcher("../WEB-INF/main/login.jsp").forward(req,res);
			}else if(!a.getPassword().equals(password)){
				req.setAttribute("error", "�Բۣ��ܲ����ȼ��һ������@��");
				req.setAttribute("admin", adminCode);
				req.setAttribute("password", password);
				req.getRequestDispatcher("../WEB-INF/main/login.jsp").forward(req,res);
			}else{
				req.getSession().setAttribute("admin", a);
				res.sendRedirect("toIndex.do");
			}
			
	}
	//����ҳ
	private void toIndex(HttpServletRequest req, HttpServletResponse res) 
					throws ServletException,IOException{
		req.getRequestDispatcher("../WEB-INF/main/index.jsp").forward(req, res);
		
	}
	//������֤��
	private void createImage(HttpServletRequest req, HttpServletResponse res) 
					throws ServletException,IOException{
		Object[] objs = ImageUtil.createImage();
		HttpSession session = req.getSession();
		session.setAttribute("imageCode",objs[0]);
		BufferedImage image = (BufferedImage) objs[1];
		res.setContentType("image/png");
		OutputStream out = res.getOutputStream();
		ImageIO.write(image,"png",out);
		out.close();
	}
	//�򿪵�¼ҳ��
	private void toLogin(
			HttpServletRequest req,
			HttpServletResponse res)
			throws IOException,ServletException{
		req.getRequestDispatcher("../WEB-INF/main/login.jsp").forward(req, res);
	}

}
