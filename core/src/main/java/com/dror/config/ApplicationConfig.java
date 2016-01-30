package com.dror.config;

import com.dror.extensions.BaseApplicationExceptionMapper;
import com.dror.extensions.BaseApplicationJsonProvider;
import com.dror.rest.services.TestService;
import com.dror.serializing.JacksonSerializer;
import com.dror.serializing.Serializer;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import javax.ws.rs.core.Application;
import javax.ws.rs.ext.RuntimeDelegate;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * User: Dror
 * Date: 1/8/2016
 */
@Configuration
@PropertySource(value = {"classpath:dev-application.properties"})
@Import({DataConfig.class, SecurityConfig.class})
@EnableCassandraRepositories(basePackages = "com.dror.repositories")
@EnableWebSecurity
public class ApplicationConfig
{
    @Autowired
    Environment environment;

    @Bean(destroyMethod = "shutdown")
    public SpringBus cxf()
    {
        return new SpringBus();
    }

    @Bean
    public Server jaxRsServer()
    {
        Application application = baseApplication();
        JAXRSServerFactoryBean factoryBean = RuntimeDelegate.getInstance()
                .createEndpoint(application, JAXRSServerFactoryBean.class);

        // register services in application
        List<Object> services = new LinkedList<>();
        services.add(testService());

        factoryBean.setServiceBeans(services);

        // register other providers
        List<Object> providers = Arrays.asList(
                baseApplicationExceptionMapper(),
                baseApplicationJsonProvider());
        factoryBean.setProviders(providers);

        return factoryBean.create();
    }

    @Bean
    public Application baseApplication()
    {
        return new Application();
    }

    @Bean
    public Serializer serializer()
    {
        return new JacksonSerializer();
    }

    @Bean
    public BaseApplicationExceptionMapper baseApplicationExceptionMapper()
    {
        return new BaseApplicationExceptionMapper();
    }

    @Bean
    public BaseApplicationJsonProvider baseApplicationJsonProvider()
    {
        return new BaseApplicationJsonProvider();
    }

    @Bean
    public TestService testService()
    {
        return new TestService();
    }
}
