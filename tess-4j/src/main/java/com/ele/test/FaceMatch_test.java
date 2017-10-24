
/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.ele.test;


import java.net.URLEncoder;

import com.ele.ocr.AppClientConstant;
import com.ele.utils.baidu.Base64Util;
import com.ele.utils.baidu.FileUtil;
import com.ele.utils.baidu.HttpUtil;

/**
 * 人脸对比接口
 */
public class FaceMatch_test {

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
        // 人脸对比url
        String matchUrl = "https://aip.baidubce.com/rest/2.0/face/v2/match";
        // 本地文件路径，可用多张图片
        String filePath1 = "F:/faces/ccj.jpg";
        String filePath2 = "F:/faces/jjs.jpg";
        String filePath3 = "F:/faces/jly.jpg";
        String filePath5 = "F:/faces/jjs_1.jpg";
        String filePath4 = "F:/faces/cl.jpg";
        try {
            byte[] imgData1 = FileUtil.readFileByBytes(filePath1);
            byte[] imgData2 = FileUtil.readFileByBytes(filePath2);
            byte[] imgData3 = FileUtil.readFileByBytes(filePath3);
            byte[] imgData4 = FileUtil.readFileByBytes(filePath4);
            byte[] imgData5 = FileUtil.readFileByBytes(filePath5);
            
            String imgStr1 = Base64Util.encode(imgData1);
            String imgStr2 = Base64Util.encode(imgData2);
            String imgStr3 = Base64Util.encode(imgData3);
            String imgStr4 = Base64Util.encode(imgData4);
            String imgStr5 = Base64Util.encode(imgData5);
            String params = URLEncoder.encode("images", "UTF-8") + "="
                    + URLEncoder.encode(imgStr1 + "," + imgStr2 + "," + imgStr4+ "," + imgStr3 + "," + imgStr5+ ","+"UTF-8" );
            /**
             * 线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
             */
            String result = HttpUtil.post(matchUrl, accessToken, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}