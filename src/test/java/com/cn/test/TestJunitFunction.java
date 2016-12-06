/**
 * Project Name:cici
 * File Name:TestJunitFunction.java
 * Package Name:com.cn.test
 * Date:2016年10月25日下午4:13:17
 * Copyright (c) 2016, All Rights Reserved.
 *
*/

package com.cn.test;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import junit.framework.Assert;

import org.junit.Test;

/**
 * ClassName:TestJunitFunction <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年10月25日 下午4:13:17 <br/>
 * @author   TH
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class TestJunitFunction{

	@SuppressWarnings("deprecation")
	@Test
	public void testJunit(){
		System.out.println("1");
		Assert.assertEquals("sss","s");
	}
	
	
	@Test
	public void testXml(){
		SortedMap<String,String> s = new TreeMap<String, String>();
		s.put("a", "aaa");
		s.put("b", "bbb");
		s.put("c", "ccc");
		parseXML(s);
	}
	
	public static String parseXML(SortedMap<String, String> parameters) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            String k = (String)entry.getKey();
            String v = (String)entry.getValue();
            if (null != v && !"".equals(v) && !"appkey".equals(k)) {
            	String value = parameters.get(k);
            	sb.append("<").append(k).append(">");
            	sb.append("<![CDATA[").append(value).append("]]>");
            	sb.append("</").append(k).append(">\n");
                //sb.append("<" + k + ">" + parameters.get(k) + "</" + k + ">\n");
            }
        }
        sb.append("</xml>");
        return sb.toString();
    }
	
	@Test
	public void subString(){
		String attach = "bank_mch_name=xxxx&bank_mch_id=111";
		attach.lastIndexOf("=");
		System.out.println(attach.substring(0, attach.lastIndexOf("=")+1));
	}
}

