package io.belov.presentations.jooq;

import io.belov.presentations.jooq.config.JooqSpringBootConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jooq.JooqAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.boot.autoconfigure.web.*;
import org.springframework.boot.devtools.autoconfigure.LocalDevToolsAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created on 29.06.17.
 */
@ImportResource({"classpath:/applicationContext.xml"})
@EnableTransactionManagement(proxyTargetClass = true)
@Import({
        App.Config.class,
        JooqSpringBootConfiguration.class,

        // ORDER IS IMPORTANT! Configurations should be before autoconfigurations

        DispatcherServletAutoConfiguration.class,
        EmbeddedServletContainerAutoConfiguration.class,
        ErrorMvcAutoConfiguration.class,
        HttpEncodingAutoConfiguration.class,
        HttpMessageConvertersAutoConfiguration.class,
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        JacksonAutoConfiguration.class,
        PropertyPlaceholderAutoConfiguration.class,
        TransactionAutoConfiguration.class,
        ServerPropertiesAutoConfiguration.class,
        JooqAutoConfiguration.class,
        WebMvcAutoConfiguration.class,
        LocalDevToolsAutoConfiguration.class,
})
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    public static class Config {

    }
}
