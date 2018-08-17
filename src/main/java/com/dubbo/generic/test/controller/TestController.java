package com.dubbo.generic.test.controller;

import com.dubbo.generic.test.client.ReleaseManagerImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 项目名称:dubbo_generic 描述: 创建人:ryw 创建时间:2018/8/16
 */
@RestController
@RequestMapping("/")
@Slf4j
public class TestController {

    @Autowired
    private ReleaseManagerImpl releaseManager;

    @RequestMapping(value = "hello.do", method = RequestMethod.GET)
    String transReq() throws Throwable {
        Object o = releaseManager.doInvoke();
        System.out.println(o);
        return  o.toString();
    }


}
