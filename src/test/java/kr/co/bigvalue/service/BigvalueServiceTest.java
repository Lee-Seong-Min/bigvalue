package kr.co.bigvalue.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BigvalueServiceTest {

    @Autowired
    private BigvalueService bigvalueService;

    @Test
    public void test() {

        bigvalueService.test();
    }
}
