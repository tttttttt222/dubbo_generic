package com.dubbo.generic.test.service.impl;

import com.dubbo.generic.test.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 项目名称:dubbo_generic 描述: 创建人:ryw 创建时间:2018/8/16
 */
@Service
@Slf4j
public class TestServiceImpl implements TestService {

    @Override
    public String hello(String str){
        System.out.println(str);
        return "hello" + str;
    }

}
