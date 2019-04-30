package com.plumre.config;

/*
 * Created by renhongjiang on 2019/4/22.
 */

import com.plumre.service.ItemService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
import org.springframework.remoting.rmi.RmiServiceExporter;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;

import java.util.Properties;

/**
 * TODO
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/4/22 15:43
 */
@Configuration
public class RemoteServiceConfig {

    @Bean
    public RmiServiceExporter rmiExporter(ItemService service) throws ClassNotFoundException {
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setService(service);
        exporter.setServiceName("ItemService");
        exporter.setServiceInterface(ItemService.class);

        /*exporter.setRegistryHost("rmi.spitter.com");
        exporter.setRegistryPort(1199);*/
        return exporter;
    }

    /*@Bean
    public HessianServiceExporter hItemService(ItemService service) {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(service);
        exporter.setServiceInterface(ItemService.class);
        return exporter;
    }

    @Bean
    public HttpInvokerServiceExporter httpItemService(ItemService service) {
        HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
        exporter.setService(service);
        exporter.setServiceInterface(ItemService.class);
        return exporter;
    }

    @Bean
    public HandlerMapping httpInvokerMapping() {
        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        Properties properties = new Properties();
        properties.setProperty("/service", "httpItemService");
        mapping.setMappings(properties);
        return mapping;
    }*/
}