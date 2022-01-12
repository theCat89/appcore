package ru.horologer.core;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class RenewableReadCache<T> {
    private final TimeUnit tu;
    private final int interval;
    private final Supplier<T> refresher;
    private long lastRefresh;
    private T cacheableValue;

    private RenewableReadCache(T cacheableValue, TimeUnit tu, int interval, Supplier<T> refresher) {
        this.cacheableValue = cacheableValue;
        this.tu = tu;
        this.interval = interval;
        this.refresher = refresher;
    }

    public T getCacheableValue() {
        if(System.currentTimeMillis() - lastRefresh  > tu.toMillis(interval))
            return cacheableValue = refresh();
        return cacheableValue;
    }

    public T refresh(){
        lastRefresh = System.currentTimeMillis();
        return refresher.get();
    }

    public static class CacheBuilder<T> {
        TimeUnit tu;
        int interval;
        Supplier<T> refresher;

        public CacheBuilder<T> setTimeUnit(TimeUnit tu) {
            this.tu = tu;
            return this;
        }

        public CacheBuilder<T> setInterval(int interval) {
            this.interval = interval;
            return this;
        }

        public CacheBuilder<T> setRefresher(Supplier<T> refresher) {
            this.refresher = refresher;
            return this;
        }

        public RenewableReadCache<T> build() {
            return new RenewableReadCache<T>(refresher.get(), tu, interval, refresher);
        }
    }

}
