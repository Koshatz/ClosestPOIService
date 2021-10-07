/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kos_hatz.getClosestPOI.config;

import com.kos_hatz.getClosestPOI.soap.PoiServiceExceptionResolver;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

/**
 *
 * @author K. Chatzistamatis
 */
@EnableWs
@Configuration
public class WsServiceConfig extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    @Bean(name = "getApiServices")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema poiSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("PoiPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://getClosestPoi-web-service");
        wsdl11Definition.setSchema(poiSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema poiSchema() {
        return new SimpleXsdSchema(new ClassPathResource("/schemas/GetClosestPOITypes.xsd"));
    }

    @Bean
    public PoiServiceExceptionResolver poiServiceExceptionResolver() {
        PoiServiceExceptionResolver exceptionResolver = new PoiServiceExceptionResolver();
        exceptionResolver.setOrder(0);
        return exceptionResolver;
    }
}
