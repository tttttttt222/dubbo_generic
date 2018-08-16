package com.dubbo.generic.test.client;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.rpc.service.GenericService;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 项目名称:dubbo_generic 描述: 创建人:ryw 创建时间:2018/8/16
 */
@Component
@Slf4j
public class ReleaseManagerImpl {

    @Autowired
    private ApplicationConfig applicationConfig;

    @Autowired
    private RegistryConfig registryConfig;

    private int defaultTimeOut=30000;//30秒

    private ReferenceConfig<GenericService> genericReferenceConfig;

    @PostConstruct
    private void doInit() throws Throwable {
        genericReferenceConfig = createGenericReferenceConfig();

    }


    public Object doInvoke() throws Throwable {

        Object hello = genericReferenceConfig.get()
                .$invoke("hello", new String[]{"java.lang.String"}, new Object[]{"123"});

        System.out.println(hello);

        return hello;
    }



    public ReferenceConfig createGenericReferenceConfig(){
        try {
            ReferenceConfig<GenericService> service = new ReferenceConfig<>();
            service.setApplication(applicationConfig);
            service.setRegistry(registryConfig);
            service.setInterface("com.dubbo.generic.test.service.TestService");
            service.setVersion("1.0.0");
            service.setTimeout(defaultTimeOut);
            service.setRetries(0);
            service.setCheck(false);
            service.setGeneric(true);
            return service;
        } catch (Exception e) {
            log.error("创建dubbo消费端失败", e);
            return null;
        }
    }
}
