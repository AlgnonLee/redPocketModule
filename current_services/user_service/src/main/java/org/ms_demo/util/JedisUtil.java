package org.ms_demo.util;


import redis.clients.jedis.Jedis;

public class JedisUtil {

    public static Long set(String key, String value) {
        Jedis jedis = RedisPoolUtil.getJedis();
        Long result = jedis.setnx(key, value);
        jedis.close();
        return result;
    }

    public static int getNumber(String key) {
        Jedis jedis = RedisPoolUtil.getJedis();
        int result = Integer.parseInt(jedis.get(key));
        jedis.close();
        return result;
    }

    public static float getFloat(String key) {
        Jedis jedis = RedisPoolUtil.getJedis();
        float result = Float.parseFloat(jedis.get(key));
        jedis.close();
        return result;
    }

    public static Long hsetnx(String key, String field, String value) {
        Jedis jedis = RedisPoolUtil.getJedis();
        Long hsetnx = jedis.hsetnx(key, field, value);
        jedis.close();
        return hsetnx;
    }

    public static String hget(String key, String field) {
        Jedis jedis = RedisPoolUtil.getJedis();
        String result = jedis.hget(key, field);
        jedis.close();
        return result;
    }

    public static String setEx(String key, int seconds, String value) {
        Jedis jedis = RedisPoolUtil.getJedis();
        String s = jedis.get(key);
        if (s!=null){
            jedis.setex(key, seconds, value);
            jedis.close();
            return "success";
        }else {
            jedis.close();
            return "failed";
        }

    }

    public static Long hdel(String key, String field) {
        Jedis jedis = RedisPoolUtil.getJedis();
        Long hdel = jedis.hdel(key, field);
        jedis.close();
        return hdel;
    }

    public static Long del(String key) {
        Jedis jedis = RedisPoolUtil.getJedis();
        Long del = jedis.del(key);
        jedis.close();
        return del;
    }

    public static Long hset(String key, String field, String value) {
        Jedis jedis = RedisPoolUtil.getJedis();
        Long i = jedis.hset(key, field, value);
        jedis.close();
        return i;
    }

}
