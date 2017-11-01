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
		 * ��ImageUtil�������ȡ�洢ͼƬ�����map����
		 */
		Map<String,BufferedImage> imageMap = ImageUtil.createImage();
		String code = imageMap.keySet().iterator().next();
		//��ȡ�����д洢����֤���ַ�
		session.setAttribute("imageCode",code);
		//����֤���ַ�����session��
		BufferedImage image = imageMap.get(code);
		//���ݼ����е��ַ���ȡ�����е�ͼƬ����
		response.setContentType("image/jpeg");
		//������������ݸ�ʽ
		OutputStream os = response.getOutputStream();
		//����Ӧ�����л�ȡ�����
		ImageIO.write(image, "jpeg", os);
		//���������ͼƬ������jpeg�ĸ�ʽд����Ӧ�С�
		os.close();
		//�ر������
		
	}
}