package org.ms_demo.util;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.ms_demo.mapper.CurrentMapper;
import org.ms_demo.mapper.RedPocketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MapperTest {

    @Autowired
    public RedPocketMapper redPocketMapper;

    @Autowired
    public CurrentMapper currentMapper;


    @Test
    public void test() {
        System.out.println(redPocketMapper.selectAll());
    }

    @Test
    public void test1() {
        System.out.println(currentMapper.getRedPocketByUid(5));
    }
}
