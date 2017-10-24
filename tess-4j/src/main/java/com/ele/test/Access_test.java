package com.ele.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.ele.ocr.AppClientConstant;
import com.ele.utils.baidu.AuthService;
import com.ele.utils.baidu.Base64Util;
import com.ele.utils.baidu.FileUtil;
import com.ele.utils.baidu.HttpUtil;

/**
 * test for baidu ocr
 * @author yaoxj
 * @time 2017年5月12日下午3:14:17
 */
public class Access_test {
	
	/**
	 * main method
	 * @param args
	 */
	public static void main(String[] args) {
		String paramString = null;
//		String pic_path = "F://pic//id_pic//positive.jpg";
		String pic_path = "F:\\pic\\tesseract_test\\photo\\image.png";
		//------------------------------------------------------------------------------------------------
		
		long startTime = System.currentTimeMillis();
		
		try{
		
		/**
		 * 一:access token 测试成功!
		 */
		/*
		String accessToken = AuthService.getAuth();
		System.out.println("\n\naccessToken="+accessToken);
		*/
		
		byte[] picByte = FileUtil.readFileByBytes(pic_path);
		String pic_String = Base64Util.encode(picByte);
		paramString = "&image="+pic_String;
		HttpUtil.post(AppClientConstant.getGeneralBasic(), AppClientConstant.getAccesstoken(), paramString);
		
		
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("something error");
		}
		long endTime = System.currentTimeMillis();
		
		System.out.println("\n\n-----------------------------------\n\nlasted time = "+ (endTime - startTime) / 1000f + " s");
	}

}
