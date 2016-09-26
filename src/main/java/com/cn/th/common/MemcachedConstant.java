
/**
 * Project Name:klpay
 * File Name:MemcachedConstant_java
 * Package Name:koalac_common_constant
 * Date:2015年6月24日下午2:14:43
 * Copyright (c) 2015, All Rights Reserved_
 *
*/
package com.cn.th.common;
/**
 * ClassName:MemcachedConstant <br/>
 * Function: memcached连接池配置参数 <br/>
 * Reason:	 TODO ADD REASON<br/>
 * Date:     2015年6月24日 下午2:14:43 <br/>
 * @author   david.shaw
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class MemcachedConstant {

		
	   public static final int  MEMCACHED_OBJECT_ALIVE_SECONDS=86400;

	   /**
	    * 
		*   设置初最始连接数
	    */
	   public static final int 	MEMCACHED_DANGA_POOL_INITCONN=50;
       /**
        *   设置连接池最小连接数
        */
	   public static final int 	MEMCACHED_DANGA_POOL_MINCONN=50;

	   /**
        *   设置连接池最大连接数
        */
	   public static final int	MEMCACHED_DANGA_POOL_MAXCONN=100;
	   
	   /**
	    *   设置连接池最大处理时间
	    */
	   public static final int	MEMCACHED_DANGA_POOL_MAXIDLE=30;

	   /**
	    *   memcached主线线休息时间 3秒钟
	    */
	   public static final int	MEMCACHED_DANGA_POOL_MAINTSLEEP=3000;

	   /**
	    * Tcp的规则就是在发送一个包之前，本地机器会等待远程主机       
                       对上一次发送的包的确认信息到来；这个方法就可以关闭套接字的缓存，       
                      以至这个包准备好了就发；    
                      设置是否使用Nagle算法，因为我们的通讯数据量通常都比较大（相对TCP控制数据）
                      而且要求响应及时，因此该值需要设置为false
	    */
	   public static final boolean	MEMCACHED_DANGA_POOL_NAGLE=false;

	   /**
	    * 连接超时时间
	    */
	   public static final int	MEMCACHED_DANGA_POOL_SOCKETTO=30;

	   /**
	    * 连接建立时对超时的控制
	    */
	   public static final int	MEMCACHED_DANGA_POOL_SOCKETCONNECTTO=0;

	   /**
	    *   是否开启压缩设置，超过指定大小（单位为K）的数据都会被压缩
	    */
	   public static final boolean	MEMCACHED_DANGA_COMPRESSENABLE=true;

	   /**
	    *  
	    */
	   public static final int	MEMCACHED_DANGA_COMPRESSTHRESHOLD=5120;
}

