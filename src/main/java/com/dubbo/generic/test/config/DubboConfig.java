package com.dubbo.generic.test.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.MonitorConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 项目名称:dubbo_generic 描述: 创建人:ryw 创建时间:2018/8/16
 */
@Configuration
@Slf4j
public class DubboConfig {

    @Value("${zookeeper.server.address}")
    private String zkAddress;

    @Bean
    public ApplicationConfig applicationConfig() {
        log.info("============DubboConfig==============applicationConfig===========================");
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("generic");
        applicationConfig.setMonitor(this.monitorConfig());
        return applicationConfig;
    }

    @Bean
    public RegistryConfig registryConfig() {
        log.info("============DubboConfig==============registryConfig===========================");
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setProtocol("zookeeper");
        registryConfig.setAddress(zkAddress);
        registryConfig.setRegister(true);
        registryConfig.setTimeout(15000);
        return registryConfig;
    }

    @Bean
    public MonitorConfig monitorConfig() {
        MonitorConfig monitorConfig = new MonitorConfig();
        monitorConfig.setProtocol("registry");
        return monitorConfig;
    }


}
