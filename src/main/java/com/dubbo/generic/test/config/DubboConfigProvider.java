package com.dubbo.generic.test.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.dubbo.generic.test.service.TestService;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 项目名称:dubbo_generic 描述: 创建人:ryw 创建时间:2018/8/16
 */
@Configuration
@Slf4j
public class DubboConfigProvider {
    @Bean
    public ProtocolConfig protocolConfig() {
        ProtocolConfig protocolConfig = new ProtocolConfig("dubbo", -1);
        return protocolConfig;
    }

    @Autowired
    private ApplicationConfig applicationConfig;

    @Autowired
    private RegistryConfig registryConfig;

    @Autowired
    private TestService testServiceImpl;

    //提供者
    @PostConstruct()
    public void doInit() {
        System.out.println(testServiceImpl);
        ServiceConfig<TestService> serviceConfig;
        serviceConfig = new ServiceConfig<>();
        serviceConfig.setInterface(TestService.class);
        serviceConfig.setRef(this.testServiceImpl);
        serviceConfig.setApplication(this.applicationConfig);
        serviceConfig.setRegistry(this.registryConfig);
        serviceConfig.setProtocol(protocolConfig());
        serviceConfig.setVersion("1.0.0");
        serviceConfig.setTimeout(30000);
        serviceConfig.export();
    }
}
