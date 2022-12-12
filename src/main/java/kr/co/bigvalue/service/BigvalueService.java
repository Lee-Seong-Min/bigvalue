package kr.co.bigvalue.service;

import kr.co.bigvalue.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BigvalueService {

    @Autowired
    private TestMapper testMapper;

    public void test()
    {
        int te = testMapper.getYearMonth();

        int tes = 0;
    }

}
