package org.ms_demo.util;

import redis.clients.jedis.Jedis;

/**
 * @author AutumnLeaf
 * @date 2024/10/1
 * @Description
 * 管理Jedis相关数据操作方法
 */
public class JedisUtil {


    public static String set(String key, String value) {
        Jedis jedis = RedisPoolUtil.getJedis();
        String result = jedis.set(key, value);
        jedis.close();
        return result;
    }

    public static String get(String key) {
        Jedis jedis = RedisPoolUtil.getJedis();
        String result = jedis.get(key);
        jedis.close();
        return result;
    }

    public static Long del(String key) {
        Jedis jedis = RedisPoolUtil.getJedis();
        Long del = jedis.del(key);
        jedis.close();
        return del;
    }

    public static Long expire(String key, int seconds) {
        Jedis jedis = RedisPoolUtil.getJedis();
        Long del = jedis.expire(key, seconds);
        jedis.close();
        return del;
    }

    public static long setNx(String key, String value) {
        Jedis jedis = RedisPoolUtil.getJedis();
        Long setnx = jedis.setnx(key, value);
        jedis.close();
        return setnx;
    }

    public static String setex(String key, int seconds, String value) {
        Jedis jedis = RedisPoolUtil.getJedis();
        String setex = jedis.setex(key, seconds, value);
        jedis.close();
        return setex;
    }

    public static long hset(String key, String field, String value) {
        Jedis jedis = RedisPoolUtil.getJedis();
        Long hset = jedis.hset(key, field, value);
        jedis.close();
        return hset;
    }

    public static long hsetnx(String key, String field, String value) {
        Jedis jedis = RedisPoolUtil.getJedis();
        Long hset = jedis.hsetnx(key, field, value);
        jedis.close();
        return hset;
    }

    public static String hget(String key, String field) {
        Jedis jedis = RedisPoolUtil.getJedis();
        String hget = jedis.hget(key, field);
        jedis.close();
        return hget;
    }

    public static Long hdel(String key, String field) {
        Jedis jedis = RedisPoolUtil.getJedis();
        Long hdel = jedis.hdel(key, field);
        jedis.close();
        return hdel;
    }


}
