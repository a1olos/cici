package com.cn.th.proxy.memcatched;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;

import com.cn.th.common.MemcachedConstant;
import com.schooner.MemCached.MemcachedItem;
import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;


public class MemcachedClientDanga implements MemcachedClientProxy {

	private static Logger logger = Logger.getLogger(MemcachedClientDanga.class);

	private MemCachedClient client = null;

	private Date expireDate = new Date(0L);

//	String serverIp = CommonConstant.isDebug? "139.129.109.111:11211"
//			: "139.129.97.163:11211";
	String serverIp = "192.168.1.224:11212";
	public MemcachedClientDanga() {
		try {

			String[] servers = { serverIp };

			Integer[] weights = { 3 };

			SockIOPool pool = SockIOPool.getInstance();
			pool.setHashingAlg(SockIOPool.CONSISTENT_HASH);
			pool.setServers(servers);
			pool.setWeights(weights);

			expireDate = new Date(
					MemcachedConstant.MEMCACHED_OBJECT_ALIVE_SECONDS * 1000);

			pool.setInitConn(MemcachedConstant.MEMCACHED_DANGA_POOL_INITCONN);
			pool.setMinConn(MemcachedConstant.MEMCACHED_DANGA_POOL_MINCONN);
			pool.setMaxConn(MemcachedConstant.MEMCACHED_DANGA_POOL_MAXCONN);
			pool.setMaxIdle(MemcachedConstant.MEMCACHED_DANGA_POOL_MAXIDLE);

			pool.setMaintSleep(MemcachedConstant.MEMCACHED_DANGA_POOL_MAINTSLEEP);

			pool.setNagle(MemcachedConstant.MEMCACHED_DANGA_POOL_NAGLE);
			pool.setSocketTO(MemcachedConstant.MEMCACHED_DANGA_POOL_SOCKETTO);
			pool.setSocketConnectTO(MemcachedConstant.MEMCACHED_DANGA_POOL_SOCKETCONNECTTO);

			pool.initialize();

			client = new MemCachedClient();
			
			/*
			 * client.setCompressEnable(
			 * MemcachedConstant.MEMCACHED_DANGA_COMPRESSENABLE );
			 * client.setCompressThreshold(
			 * MemcachedConstant.MEMCACHED_DANGA_COMPRESSTHRESHOLD );
			 */
		} catch (Exception e) {

			logger.error(e.getMessage(), e);
		}
	}

	public boolean delete(String key) {
		return client.delete(key);
	}

	public boolean flush(int delay) {
		return client.flushAll();
	}

	public Object get(String key) {
		return client.get(key);
	}

	public Map<String, Object> getMap(Collection<String> keys) {
		String[] keysStr = new String[keys.size()];
		System.arraycopy(keys.toArray(), 0, keysStr, 0, keys.size());
		return client.getMulti(keysStr);
	}

	public Map<String, Object> getMap(String[] keys) {
		return client.getMulti(keys);
	}

	public boolean set(String key, Object value) {
		return client.set(key, value, expireDate);
	}

	public boolean set(String key, Object value, long expire) {
		Date exp = new Date(expire * 1000);
		return client.set(key, value, exp);
	}

	public boolean containKey(String key) {
		return client.keyExists(key) && get(key) != null;
	}

	public static void main(String[] args) {
		MemcachedClientDanga client = new MemcachedClientDanga();
		boolean result= client.flush(0);
		System.out.println(result);
		client.set("1", "1");
		System.out.println(client.get("1"));
		//2592038 15625018371
//		String hString="Url_Visit_Constraint_Cache_Data_2382572_"+"/klpay/member/pay/apply/set/pass.do";
		
		 //client.flush(0);
		//"Verify_Code_Key_1599502905"
		
		/*Object g = client.get(DigestUtils.shaHex(hString));
		String mobile = "13025119214";
		String userId = "2592038";
		String businessType = null;
		g = client.get(SmsConstant.SMS_CACHE_DATA + mobile
				+ (businessType == null ? "" : "_" + businessType));
		System.out.println(g);*/
		// client.flush(0);
		// Object g= client.delete("NewOrderSumByPassedDaysDate");
		/*
		 * Object o = client.get("user_own_account_userid_5401669");
		 * if(StringUtils.isEmpty(o)){ System.out.println("------"); }else{
		 * System.out.println("+++++"); }
		 */

		// System.out.println(g);
		/*
		 * Object g=client.get(SmsConstant.SMS_VERIFY_CODE_PREFIX_CACHE_KEY+
		 * "13760890719"+"_2382249"+"_1");
		 * g=client.delete(SmsConstant.SMS_CACHE_DATA+
		 * "13760890719"+"_2382249"+"_1"); System.out.println(g);
		 */
		
	}

	public Object gets(String key) {
		MemcachedItem item = client.gets(key);
		return item;
	}

	public boolean cas(String key, Object value, long cas) {
		boolean issuccess = client.cas(key, value, cas);
		return issuccess;
	}
}
