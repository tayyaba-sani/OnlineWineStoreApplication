package com.springboot.wine.store;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WineStoreApplicationTests.class)
public class WineStoreApplicationTests {

    @Test
    void contextLoads() {
        String str= "Junit is working fine";
        assertEquals("Junit is working fine",str);
    }

}
