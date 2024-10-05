package org.ms_demo.services.impl;

import com.alibaba.fastjson.JSON;
import org.ms_demo.entity.RedPocket;
import org.ms_demo.services.RedPocketRedisService;
import org.ms_demo.util.JedisUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RedPocketRedisServiceImpl implements RedPocketRedisService {

    public static final String RED_POCKET = "red_pockets";


    @Override
    public int addRedPocket(RedPocket redPocket) {
        JedisUtil.hsetnx(RED_POCKET, String.valueOf(redPocket.getRedPocketId()), JSON.toJSONString(redPocket));
        return 0;
    }

    @Override
    public int removeRedPocket(RedPocket redPocket) {
        Long hdel = JedisUtil.hdel(RED_POCKET, String.valueOf(redPocket.getRedPocketId()));
        return hdel == null ? 0 : 1;
    }

    @Override
    public int updateRedPocket(RedPocket redPocket) {
        return (int) JedisUtil.hset(RED_POCKET, String.valueOf(redPocket.getRedPocketId()),JSON.toJSONString(redPocket));
    }

    @Override
    public ArrayList getRedPockets() {
        Map map = JedisUtil.hget(RED_POCKET);
        ArrayList arrayList = new ArrayList<>(map.values());
        return arrayList;
    }


}
