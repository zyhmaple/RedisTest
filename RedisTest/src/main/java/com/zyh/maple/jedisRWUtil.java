package com.zyh.maple;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;

public enum jedisRWUtil {
	
	INTANCE;
	
    //连接本地的 Redis 服务
    private static Jedis jedis =null;
    
    static {    	
    	System.out.println("连接成功");
    }

    private Jedis getRedis()
    {
    	if(jedis!=null)
    		return jedis;
    	
    	return	jedis = new Jedis("localhost");
    }
    
    
    public void set(String key,String value)
    {
    	this.getRedis().set(key, value);
        // 获取存储的数据并输出
        System.out.println("redis 存储的字符串为: "+ jedis.get(key));
    }
    
    public String get(String key)
    {

    	return this.getRedis().get(key);

    }
    
    public void lpush(String key,String value)
    {
    	this.getRedis().lpush(key,value);
    }
    
    public Set<String> getKeys()
    {
        // 获取数据并输出
        Set<String> keys = this.getRedis().keys("*"); 
        Iterator<String> it=keys.iterator() ;   
        while(it.hasNext()){   
            String key = it.next();   
            System.out.println(key);  
        }
        return keys;
    }
    
    public void getList(String key){
    	
    	List<String> list = this.getRedis().lrange(key,0,this.getRedis().llen(key));
        for(int i=0; i<this.getRedis().llen(key); i++) {
            System.out.println("列表项为: "+list.get(i));
        }
    }
    
}
