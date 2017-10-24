package com.ele.test;

import java.awt.Image;
import java.net.URLEncoder;

import com.ele.ocr.AppClientConstant;
import com.ele.utils.baidu.Base64Util;
import com.ele.utils.baidu.FileUtil;
import com.ele.utils.baidu.HttpUtil;

/**
 * OCR 通用识别
 */
public class General_test {

    /**
     * 代码中所需工具类
     * FileUtil,Base64Util,HttpUtil请从
     * https://ai.baidu.com/file/BA73D199EED14D8AA5FC5A4BF4BDDA34
     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
     * https://ai.baidu.com/file/88C6E86FB5D141889391693FC84504B1
     * 下载
     */
    
    @SuppressWarnings("static-access")
	public static void main(String[] args) {
    	
    	System.out.println("\n★★★★★★★★★★★★★★★★   application is running ★★★★★★★★★★★★★★★★★★★★★★★\n\n");
    	
    	/*
    	TestPrepare prepare = new TestPrepare();
    	
    	
    	List<String> list = new ArrayList<String>();
        // 通用识别url
        String otherHost = "https://aip.baidubce.com/rest/2.0/ocr/v1/general";
        // 本地图片路径
//        String filePath = "F:\\pic\\tesseract_test\\photo\\test1.jpg";
//        File dirPath = new File("F:\\demo\\Demo_20170413\\Img");
        File dirPath = new File("F:\\tess-5-23");
        prepare.ergodic(dirPath, list);
        
        
//        execute_recognize(otherHost, "F:/pic/tesseract_test/tess-train/test/IMG_0423.JPG");
       
        System.out.println("before for");
        for(String filePath: list){
        	String format = filePath.substring(filePath.lastIndexOf(".")+1);
        	if(prepare.getFormats().contains(format)){
        		System.out.println("this is for loop ");
                execute_recognize(otherHost, filePath);
        	}
        	
        }
        
        */
    	
        
        
    }
    /**
     * 线上识别图片信息
     * @param otherHost
     * @param filePath
     */
	private static void execute_recognize(String otherHost, String filePath) {
		long startTime = System.currentTimeMillis();
		try {
		    byte[] imgData = FileUtil.readFileByBytes(filePath);
		    String imgStr = Base64Util.encode(imgData);
		    String params = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(imgStr, "UTF-8");
		    /**
		     * 线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
		     */
		    String accessToken = AppClientConstant.getAccesstoken();
		    String result = HttpUtil.post(otherHost, accessToken, params);
		    System.out.println("fileName="+filePath+" \n----------------------------------------------------------------------\n");
		} catch (Exception e) {
		    e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("\n----------------------------------------------------------------------\nlasted time = "
				+ (endTime - startTime) / 1000f + " s\n\n");
	}
}