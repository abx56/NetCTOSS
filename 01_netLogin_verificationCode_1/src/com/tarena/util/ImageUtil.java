package com.tarena.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/*
 * 生成验证码的工具类
 */
public class ImageUtil {
	/*
	 * 字符串常量池
	 */
	public static final char[] chars ={
		'0','1','2','3','4','5','6','7','8','9',
		'a','b','c','d','e','f','g','h','i','j','k',
		'l','m','n','o','p','q','r','s','t','u','v',
		'w','x','y','z'
	};
	/*
	 * 验证码的个数
	 */
	public static final int size =4;
	/*
	 * 验证码图片的宽度
	 */
	public static final int width =1400;
	/*
	 * 验证码图片的高度
	 */
	public static final int height =400;
	/*
	 * 验证码中字符的大小
	 */
	public static final int font_size =300;
	
	public static Map<String,BufferedImage> createImage(){
		StringBuffer sb = new StringBuffer();
		BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_3BYTE_BGR);
		Graphics g = image.getGraphics();
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, width, height);
		Random ran = new Random();
		for(int i=1;i<=size;i++){
			g.setColor(getRandomColor());
			g.setFont(new Font(null,Font.BOLD+Font.ITALIC,font_size));
			int r = ran.nextInt(chars.length);
			g.drawString(chars[r]+"",(i-1)*width/size,height/2);
			g.drawLine(ran.nextInt(width),ran.nextInt(height),
					ran.nextInt(width),ran.nextInt(height));
			g.setColor(Color.red);
			g.drawString(".",(i-1)*width/size,height/2);
			sb.append(chars[r]);
			
		}
		Map<String,BufferedImage> map = new HashMap<String,BufferedImage>();
		map.put(sb.toString(), image);
		
		return map;
	}

	private static Color getRandomColor() {
		// TODO Auto-generated method stub
		Random ran = new Random();
		Color c= new Color(ran.nextInt(256),ran.nextInt(256),ran.nextInt(256));
		return c;
	}
	
}
