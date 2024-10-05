package org.ms_demo.services.impl;

import org.ms_demo.entity.RedPocket;
import org.ms_demo.mapper.RedPocketMapper;
import org.ms_demo.services.RedPocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RedPocketServiceImpl implements RedPocketService {

    @Autowired
    private RedPocketMapper redPocketMapper;

    @Override
    public int addRedPocket(RedPocket redPocket) {
        int insert = redPocketMapper.insert(redPocket);
        return insert;
    }

    @Override
    public RedPocket getRedPocketBy(int rid) {
        RedPocket redPocket = redPocketMapper.selectByRedPocketId(rid);
        return redPocket;
    }

    @Override
    public List<RedPocket> getUsersRedPockets(int uid) {
        List<RedPocket> redPockets = redPocketMapper.selectByUid(uid);
        return redPockets;
    }

    @Override
    public int disableRedPocket(int rid) {
        int i = redPocketMapper.updateRedPocketStatus(rid);
        return i;
    }

    @Override
    public double gainRedPocket(int rid) {
        return 0;
    }

}
