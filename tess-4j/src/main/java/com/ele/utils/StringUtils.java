package com.ele.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

import org.springframework.util.Assert;

public class StringUtils {
	
	/***
	 * 
	 * <p>通过StringBuilder拼接字符串</p>
	 * 
	 * @param strs
	 * @return String
	 * @author: 赵睿
	 * @date: Created on 2016年5月30日 上午10:15:11
	 */
	public static String stringConcatenation(String... args){
		StringBuilder builder=new StringBuilder();
		for (String string : args) {
			builder.append(string);
		}
		return builder.toString();
	}
	
	
	/**
	 * 
	 * <p>获得唯一的uuid</p>
	 * 
	 * @return String
	 * @author: 赵睿
	 * @date: Created on 2016年5月27日 上午10:23:45
	 */
	public static String getUUid(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	/**
	 * 
	 * <p>字符串首字母大写</p>
	 * 
	 * @param str
	 * @return String
	 * @author: 赵睿
	 * @date: Created on 2016年4月18日 下午2:21:10
	 */
	public static String upperFirst(String str){
		char[] chars=str.toCharArray();
		if(Character.isLowerCase(chars[0])){
			chars[0]-=32;
		}
		return new String(chars);
	}

	/**
	 * 将对象中所有值为空的属性替换为null
	 * 注意：
	 *  1，所有类型为String的属性都必须有标准的get set方法，
	 * @param target                        目标对象
	 * @param <T>                           对象类型
	 * @throws IllegalArgumentException     过滤失败时会抛出该异常
	 */
	public static <T> void emptyStrFilter(T target)throws IllegalArgumentException{
		Assert.notNull(target, "target不能为null");
		Set<String> targetFieldNameSet = new TreeSet<>();
		for (Field curField : target.getClass().getDeclaredFields()) {
			targetFieldNameSet.add(curField.getName());
		}
		Set<PropertyDescriptor> targetPropertySet = new HashSet<>();
		try {
			for (String curFiledName : targetFieldNameSet) {
				if(curFiledName.equals("serialVersionUID")){
					continue;
				}
				PropertyDescriptor curPd = new PropertyDescriptor(curFiledName,target.getClass());
				targetPropertySet.add(curPd);
			}
			for (PropertyDescriptor curPd : targetPropertySet) {
				if(!curPd.getPropertyType().equals(String.class)){
					continue;
				}
				String value = (String) curPd.getReadMethod().invoke(target);
				if(org.apache.commons.lang3.StringUtils.isBlank(value) && value != null){
					curPd.getWriteMethod().invoke(target,(Object)null);
				}
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("过滤空字符串时出现异常",e);
		}

	}
	
	
}
