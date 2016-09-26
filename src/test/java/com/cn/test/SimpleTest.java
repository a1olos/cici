/**
 * Project Name:maven_web
 * File Name:dddd.java
 * Package Name:com.cn.test
 * Date:2016年8月17日下午2:21:23
 * Copyright (c) 2016, All Rights Reserved.
 *
*/

package com.cn.test;

import java.net.UnknownHostException;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;

/**
 * ClassName:dddd <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年8月17日 下午2:21:23 <br/>
 * @author   TH
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class SimpleTest {
	 
    public static void main(String[] args) throws UnknownHostException, MongoException {
        Mongo mg = new Mongo();
        //查询所有的Database
        for (String name : mg.getDatabaseNames()) {
            System.out.println("dbName: " + name);
        }
        
        DB db = mg.getDB("test");
        //查询所有的聚集集合
        for (String name : db.getCollectionNames()) {
            System.out.println("collectionName: " + name);
        }
        
        DBCollection users = db.getCollection("users");
        
        //查询所有的数据
        DBCursor cur = users.find();
        while (cur.hasNext()) {
            System.out.println(cur.next());
        }
        System.out.println(cur.count());
        System.out.println(cur.getCursorId());
        System.out.println(JSON.serialize(cur));
    }
}