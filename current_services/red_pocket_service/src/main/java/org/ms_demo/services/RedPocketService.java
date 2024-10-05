package org.ms_demo.services;

import org.ms_demo.entity.RedPocket;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RedPocketService {

    public int addRedPocket(RedPocket redPocket);

    public RedPocket getRedPocketBy(int rid);

    public List<RedPocket> getUsersRedPockets(int uid);

    public int disableRedPocket(int rid);

    public double gainRedPocket(int rid);

//    public boolean isRedPocketExist(int rid);

}
