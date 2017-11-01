package com.tarena.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tarena.util.ImageUtil;

@Controller
@RequestMapping("/login")
public class LoginController {
	@RequestMapping("/createImage.do")
	public void createImage(HttpServletResponse response,HttpSession session) throws IOException{
		/*
		 * 用ImageUtil工具类获取存储图片对象的map集合
		 */
		Map<String,BufferedImage> imageMap = ImageUtil.createImage();
		String code = imageMap.keySet().iterator().next();
		//获取集合中存储的验证码字符
		session.setAttribute("imageCode",code);
		//将验证码字符存入session中
		BufferedImage image = imageMap.get(code);
		//根据集合中的字符获取集合中的图片对象
		response.setContentType("image/jpeg");
		//设置输出的数据格式
		OutputStream os = response.getOutputStream();
		//从响应对象中获取输出流
		ImageIO.write(image, "jpeg", os);
		//用输出流将图片数据以jpeg的格式写到响应中。
		os.close();
		//关闭输出流
		
	}
}