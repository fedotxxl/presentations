package io.belov.presentations.jooq;


import com.google.common.collect.Ordering;
import io.thedocs.soyuz.log.LoggerEvents;
import io.thedocs.soyuz.to;
import lombok.Getter;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Возвращает статистику по длительности исполнения запросов к БД
 */
public class JooqPerformanceCollector {

    private static final Ordering<Item> SLOWEST = Ordering.natural().onResultOf(Item::getTotalDurationInMillis);

    private final Map<String, Item> itemsByQueries = new ConcurrentHashMap<>();

    public void add(String query, long durationInMillis) {
        Item item = itemsByQueries.get(query);

        if (item == null) {
            synchronized (this) {
                item = itemsByQueries.computeIfAbsent(query, k -> new Item(query));
            }
        }

        item.add(durationInMillis);
    }

    public void reset() {
        itemsByQueries.clear();
    }

    public Collection<Item> get() {
        return itemsByQueries.values();
    }

    public List<Item> getSlowest() {
        return SLOWEST.sortedCopy(get());
    }

    @Getter
    public static class Item {
        private String query;
        private int invocationsCount;
        private long totalDurationInMillis;

        public Item(String query) {
            this.query = query;
            this.invocationsCount = 0;
            this.totalDurationInMillis = 0;
        }

        public void add(long durationInMillis) {
            this.invocationsCount++;
            this.totalDurationInMillis += durationInMillis;
        }

        @Override
        public String toString() {
            return "{" +
                    "d=" + totalDurationInMillis +
                    ", i=" + invocationsCount +
                    ", q='" + query + '\'' +
                    '}';
        }
    }

    public static class Printer {

        private static final LoggerEvents loge = LoggerEvents.getInstance(Printer.class);

        private JooqPerformanceCollector performanceCollector;
        private boolean resetOnEachIteration;

        public Printer(JooqPerformanceCollector performanceCollector, int delayInSeconds, boolean resetOnEachIteration) {
            this.performanceCollector = performanceCollector;
            this.resetOnEachIteration = resetOnEachIteration;

            if (delayInSeconds > 0) {
                to.e.daemonForever("jooq-performance", delayInSeconds * 1000, this::print, delayInSeconds * 1000).start();
            }
        }

        private void print() {
            loge.info("jooq.performance", to.map("p", "\n" + to.s(performanceCollector.getSlowest(), "\n")));

            if (resetOnEachIteration) {
                performanceCollector.reset();
            }
        }
    }
}