package org.ms_demo.services;

import org.ms_demo.entity.RedPocket;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RedPocketRedisService {

    int addRedPocket(RedPocket redPocket);

    int removeRedPocket(RedPocket redPocket);

    int updateRedPocket(RedPocket redPocket);

    List<RedPocket> getRedPockets();
}
