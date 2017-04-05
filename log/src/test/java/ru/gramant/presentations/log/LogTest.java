package ru.gramant.presentations.log;

import io.belov.soyuz.log.LoggerEvents;
import io.belov.soyuz.utils.to;
import org.junit.Test;

/**
 * Created on 05.04.17.
 */
public class LogTest {

    private static final LoggerEvents loge = LoggerEvents.getInstance(LogTest.class);

    @Test
    public void shouldStart() {
        loge.info("hello.world", to.map("planet", "Earth"));
    }

}
