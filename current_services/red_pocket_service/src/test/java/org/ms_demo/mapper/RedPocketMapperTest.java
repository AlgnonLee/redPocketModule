package org.ms_demo.mapper;

import org.junit.jupiter.api.Test;
import org.ms_demo.entity.RedPocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;


/**
 * @author AutumnLeaf
 * @date 2024/10/4
 * @Description
 */
@SpringBootTest
public class RedPocketMapperTest {

    @Autowired
    private RedPocketMapper redPocketMapper;

    @Test
    public void test(){
        List<RedPocket> redPockets = redPocketMapper.selectAll();
        RedPocket redPocket = redPockets.get(5);
        redPocket.setUuid(String.valueOf(UUID.randomUUID()));
        redPocket.setRedPocketId(0);
        redPocketMapper.insert(redPocket);
    }

}