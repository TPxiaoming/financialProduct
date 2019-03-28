package com.xiaoming.seller;

import com.xiaoming.seller.service.VerifyService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 对账测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VerifyTest {
    @Autowired
    private VerifyService verifyService;

    @Test
    public void makeVerificationFileTest(){
        Date day = new GregorianCalendar(2018,11,30).getTime();
        verifyService.makeVerificationFile("111", day);
    }

    @Test
    public void saveChanOrdersTest(){
        Date day = new GregorianCalendar(2018,11,30).getTime();
        verifyService.saveChanOrders("111", day);
    }
}
