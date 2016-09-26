/**
 * Project Name:maven_web
 * File Name:MongoDBTest.java
 * Package Name:com.cn.test
 * Date:2016年8月16日下午5:50:03
 * Copyright (c) 2016, All Rights Reserved.
 *
*/

package com.cn.test;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;

/**
 * ClassName:MongoDBTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年8月16日 下午5:50:03 <br/>
 * @author   TH
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class MongoDBTest {

	public static void main(String[] args) throws UnknownHostException {
		Mongo mongo = new Mongo();
		DB db = mongo.getDB("th");
		DBCollection users = db.getCollection("movie");
		DBCursor cur = users.find();
		while (cur.hasNext()) {
			System.out.println(cur.next());
		}
		
		Map<String,Object> map = new HashMap<String,Object>();	
	}
}

