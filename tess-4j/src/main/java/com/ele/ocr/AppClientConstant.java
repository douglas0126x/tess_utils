package com.ele.ocr;

/**
 * OCR_constant for baidu ocr
 * @author thinkpad
 *
 */
public class AppClientConstant {
	
	//----------------------- character recognition--------------------------------
	private static final String APPID = "9636175";
	private static final String API_KEY = "S7QGRur5f8Br7525hUN4YPLA";
	private static final String SECRET_KEY = "WHzDzkGIQK9qELLMojuW7tTaeHdUWoc2 ";
	
	private static final String BAIDU_TOKEN_URL = "https://aip.baidubce.com/oauth/2.0/token?";
	private static final String GRANT_TYPE = "client_credentials";
	/**
	 * 2017-5-12  开始用;
	 * 有效期 :一个月
	 */
	private static final String ACCESSTOKEN="24.33801e81b448567a50baeb982961cce3.2592000.1497165470.282335-9636175";
	private static final String GENERAL_BASIC = "https://aip.baidubce.com/rest/2.0/ocr/v1/general_basic";
	//--------------------------------------    end     -----------------------------------------------
	
	
	//-------------------------   face detected --------------------------------------------
	private static final String APPID_FACE = "9687605";
	private static final String API_KEY_FACE = "LyZiRQBjGYGhMjrhuualmHAf";
	private static final String SECRET_KEY_FACE = "ZP8D9EjCrEyeMgOfaBeVEwF0SnVOerGU ";
	/**
	 * 2017-5-24  开始用;
	 * 有效期 :一个月
	 */
	private static final String ACCESSTOKEN_FACE="24.c631679cc09c678bdbce114e6cfb07ad.2592000.1498217416.282335-9687605";
	//-----------------------------end---------------------------------------------------------------
	
	
	
	//-----------------------------Method---------------------------------------------
	/**
	 * get app id
	 * @return
	 */
	public static String getAppid() {
		return APPID;
	}
	/**
	 * get client_id
	 *  @return
	 */
	public static String getApiKey() {
		return API_KEY;
	}
	/**
	 * get secret key
	 * @return
	 */
	public static String getSecretKey() {
		return SECRET_KEY;
	}
	/**
	 * get URL for access_token of baidu
	 * @return
	 */
	public static String getBaiduTokenUrl() {
		return BAIDU_TOKEN_URL;
	}
	/**
	 * get grant type
	 * @return
	 */
	public static String getGrantType() {
		return GRANT_TYPE;
	}
	public static String getAccesstoken() {
		return ACCESSTOKEN;
	}
	public static String getGeneralBasic() {
		return GENERAL_BASIC;
	}
	public static String getAppidFace() {
		return APPID_FACE;
	}
	public static String getApiKeyFace() {
		return API_KEY_FACE;
	}
	public static String getSecretKeyFace() {
		return SECRET_KEY_FACE;
	}
	public static String getAccesstokenFace() {
		return ACCESSTOKEN_FACE;
	}
	
}
