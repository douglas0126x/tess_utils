
/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.ele.test;


import java.net.URLEncoder;

import com.ele.ocr.AppClientConstant;
import com.ele.utils.baidu.AuthService;
import com.ele.utils.baidu.Base64Util;
import com.ele.utils.baidu.FileUtil;
import com.ele.utils.baidu.HttpUtil;

/**
 * 人脸探测
 */
public class FaceDetect_test {

    /**
     * 代码中所需工具类
     * FileUtil,Base64Util,HttpUtil请从
     * https://ai.baidu.com/file/BA73D199EED14D8AA5FC5A4BF4BDDA34
     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
     * https://ai.baidu.com/file/88C6E86FB5D141889391693FC84504B1
     * 下载
     */
	
	private static String accessToken = AppClientConstant.getAccesstokenFace();
    
    public static void main(String[] args) {
    	
//    	String access = AuthService.getAuth();
//    	System.out.println("access= "+access);
    	
    	
    	Long start = System.currentTimeMillis();
        // 人脸检测 url
        String detectUrl = "https://aip.baidubce.com/rest/2.0/face/v1/detect";
        // 本地文件路径
        String filePath = "F:/faces/cl.jpg";
        try {
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            System.out.println("imgStr:" + imgStr);
            // face_fields 自定之指定返回的人脸特征值
            String params =
                    "max_face_num=5&face_fields=age,beauty,expression,faceshape,gender,glasses,landmark,race,qualities&"
                            + URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(imgStr, "UTF-8");
            /**
             * 线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
             */
            String result = HttpUtil.post(detectUrl, accessToken, params);
            Long end = System.currentTimeMillis();
            System.out.println("---\nlasted time = "+(end-start)/1000f+" s");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}