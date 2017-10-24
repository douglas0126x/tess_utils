package com.ele.utils.image;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import com.ele.utils.baidu.TestPrepare;

public class ImageUtil {
	private static BufferedImage InputImage(String srcImgPath) {
		BufferedImage srcImage = null;
		try {
			FileInputStream in = new FileInputStream(srcImgPath);
			srcImage = ImageIO.read(in);
		} catch (IOException e) {
			System.out.println("读取图片文件出错！" + e.getMessage());
			e.printStackTrace();
		}
		return srcImage;
	}

	private static BufferedImage InputImage(InputStream in) {
		BufferedImage srcImage = null;
		try {
			srcImage = ImageIO.read(in);
		} catch (IOException e) {
			System.out.println("读取图片文件出错！" + e.getMessage());
			e.printStackTrace();
		}
		return srcImage;
	}
	
	/**
	 * main method 
	 * @param args
	 */
	public static void main(String[] args) {

		TestPrepare prepare = new TestPrepare();
    	List<String> list = new ArrayList<String>();
		String filePath = "C:/Users/thinkpad/Desktop/发票种类/普票/original";
		String destPath = "F:/demo/增值税发票demo-latest/增值税发票demo程序(PC)/Img/";
		
		prepare.ergodic(new File(filePath), list);
		for(String fileName: list){
			Long start = System.currentTimeMillis();
			File name = new File(fileName);
			compressImage(fileName, destPath+ name.getName()+"_L2000.jpg", 2000);
			System.out.println("\n\nsucess");
			Long end = System.currentTimeMillis();
			System.out.println("\nlasted time = "+(end-start)/1000f+" s");
		}
		
		
		
		
		/*
		try {
			Map map = readfile(filePath, null);
			for (int i = 0; i < map.size(); i++) {
				System.out.println((String) map.get(Integer.valueOf(i)) + " =="	+ i);
				String oldpath = (String) map.get(Integer.valueOf(i));
				compressImage((String) map.get(Integer.valueOf(i)), destPath+"_"+ i + ".jpg", 2200, 1700);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		*/
	}
	
	/**
	 * compress image by new width and height
	 * @param srcImgPath
	 * @param outImgPath
	 * @param new_w
	 * @param new_h
	 */
	public static void compressImage(String srcImgPath, String outImgPath,int new_w, int new_h) {
		BufferedImage src = InputImage(srcImgPath);
		disposeImage(src, outImgPath, new_w, new_h);
	}
	
	/**
	 * compress image by max length
	 * @param srcImgPath
	 * @param outImgPath
	 * @param maxLength
	 */
	public static void compressImage(String srcImgPath, String outImgPath,int maxLength) {
		BufferedImage src = InputImage(srcImgPath);
		if (src != null) {
			int old_w = src.getWidth();

			int old_h = src.getHeight();

			int new_w = 0;
			int new_h = 0;
			
			if(Math.max(old_w,old_h) > maxLength){
				if (old_w > old_h ) {
					new_w = maxLength;
					Float result = (float) (old_h * ((float)maxLength / old_w));
					new_h = Math.round(result);
				} else {
					new_w = Math.round(old_w * (maxLength / old_h));
					new_h = maxLength;
				}
			}else{
				new_w = old_w;
				new_h = old_h;
			}
			
			
			disposeImage(src, outImgPath, new_w, new_h);
		}
	}
	
	/**
	 * get new width height and compress image
	 * @param in
	 * @param file
	 * @param maxLength
	 */
	public static void compressImage(InputStream in, File file, int maxLength) {
		BufferedImage src = InputImage(in);
		if (src != null) {
			int old_w = src.getWidth();

			int old_h = src.getHeight();

			int new_w = 0;

			int new_h = 0;

			if (old_w > old_h) {
				new_w = maxLength;
				new_h = Math.round(old_h * (maxLength / old_w));
			} else {
				new_w = Math.round(old_w * (maxLength / old_h));
				new_h = maxLength;
			}
			disposeImage(src, file, new_w, new_h);
		}
	}
	
	/**
	 * out image to destination path
	 * @param src
	 * @param outImgPath
	 * @param new_w
	 * @param new_h
	 */
	private static synchronized void disposeImage(BufferedImage src,String outImgPath, int new_w, int new_h) {
		int old_w = src.getWidth();

		int old_h = src.getHeight();

		BufferedImage newImg = null;

		switch (src.getType()) {
		case 13:
			break;
		default:
			newImg = new BufferedImage(new_w, new_h, 1);
		}

		Graphics2D g = newImg.createGraphics();

		g.drawImage(src, 0, 0, old_w, old_h, null);
		g.dispose();

		newImg.getGraphics().drawImage(src.getScaledInstance(new_w, new_h, 4),
				0, 0, null);

		OutImage(outImgPath, newImg);
	}
	
	/**
	 * put out  image to destination path
	 * @param src
	 * @param file
	 * @param new_w
	 * @param new_h
	 */
	private static synchronized void disposeImage(BufferedImage src, File file,	int new_w, int new_h) {
		int old_w = src.getWidth();

		int old_h = src.getHeight();

		BufferedImage newImg = null;

		switch (src.getType()) {
		case 13:
			break;
		default:
			newImg = new BufferedImage(new_w, new_h, 1);
		}

		Graphics2D g = newImg.createGraphics();

		g.drawImage(src, 0, 0, old_w, old_h, null);
		g.dispose();

		newImg.getGraphics().drawImage(src.getScaledInstance(new_w, new_h, 4),
				0, 0, null);

		OutImage(file, newImg);
	}
	
	/**
	 * put out image
	 * @param outImgPath
	 * @param newImg
	 */
	private static void OutImage(String outImgPath, BufferedImage newImg) {
		File file = new File(outImgPath);
		if (!file.getParentFile().exists())
			file.getParentFile().mkdirs();
		try {
			ImageIO.write(newImg,
					outImgPath.substring(outImgPath.lastIndexOf(".") + 1),
					new File(outImgPath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 输出
	 * @param file
	 * @param newImg
	 */
	private static void OutImage(File file, BufferedImage newImg) {
		try {
			int old_w = newImg.getWidth();

			int old_h = newImg.getHeight();

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(newImg, "gif", baos);
			InputStream is = new ByteArrayInputStream(baos.toByteArray());

			OutputStream os = new FileOutputStream(file);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = is.read(buffer, 0, 8192)) != -1) {
				os.write(buffer, 0, bytesRead);
			}

			os.close();
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *read file and return map of file
	 * @param filepath
	 * @param pathMap
	 * @return pathMap
	 * @throws Exception
	 */
	public static Map<Integer, String> readfile(String filepath,Map<Integer, String> pathMap) throws Exception {
		if (pathMap == null) {
			pathMap = new HashMap();
		}

		File file = new File(filepath);

		if (!file.isDirectory()) {
			pathMap.put(Integer.valueOf(pathMap.size()), file.getPath());
		} else if (file.isDirectory()) {
			String[] filelist = file.list();
			for (int i = 0; i < filelist.length; i++) {
				File readfile = new File(filepath + "/" + filelist[i]);
				if (!readfile.isDirectory()) {
					pathMap.put(Integer.valueOf(pathMap.size()),
							readfile.getPath());
				} else if (readfile.isDirectory()) {
					readfile(filepath + "/" + filelist[i], pathMap);
				}
			}
		}

		return pathMap;
	}
}