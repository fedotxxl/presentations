package io.belov.presentations.jooq.config;

import io.belov.presentations.jooq.JooqPerformanceCollector;
import io.belov.presentations.jooq.JooqPerformanceListener;
import org.jooq.*;
import org.jooq.conf.Settings;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * Created on 29.06.17.
 */
public class JooqSpringBootConfiguration {

    @Bean
    public org.jooq.Configuration jooqConfig(ConnectionProvider connectionProvider, TransactionProvider transactionProvider, JooqPerformanceCollector jooqPerformanceCollector) {
        Settings s = new Settings();

        return new DefaultConfiguration()
                .set(s)
                .derive(connectionProvider)
                .derive(transactionProvider)
                .derive(new ExecuteListenerProvider() {
                    @Override
                    public ExecuteListener provide() {
                        return new JooqPerformanceListener(jooqPerformanceCollector);
                    }
                })
                .derive(SQLDialect.POSTGRES_9_5);
    }
}
