package com.cn.th.proxy.memcatched;

import java.util.Collection;
import java.util.Map;


public interface MemcachedClientProxy {
	
	boolean flush(int delay);
	
	boolean set(String key, Object value, long expire);
	
	boolean set(String key, Object value);
	
	Object get(String key);
	
	boolean delete(String key);
	
	Map<String, Object> getMap(Collection<String> keys);
	
	Map<String, Object> getMap(String[] keys);
	
	boolean containKey(String key);
	
	Object gets(String key);

	boolean cas(String key,Object value,long cas);
}
