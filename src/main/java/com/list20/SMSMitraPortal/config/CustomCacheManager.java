package com.list20.SMSMitraPortal.config;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;

public class CustomCacheManager implements CacheManager {
    private final CacheManager cacheManager = new ConcurrentMapCacheManager();

    public CustomCacheManager() {
    }

    public Cache getCache(String name) {
        return this.cacheManager.getCache(name);
    }

    public Collection<String> getCacheNames() {
        return this.cacheManager.getCacheNames();
    }

    public Object getCachedValue(String cacheName, String key) {
        Cache cache = this.cacheManager.getCache(cacheName);
        if (cache != null) {
            Cache.ValueWrapper valueWrapper = cache.get(key);
            if (valueWrapper != null) {
                return valueWrapper.get();
            }
        }

        return this.getCachedValueByPartialKey(cacheName, key);
    }

    public Object getCachedValueByPartialKey(String cacheName, String partialKey) {
        Cache cache = this.cacheManager.getCache(cacheName);
        if (cache != null) {
            Iterator var4 = ((ConcurrentHashMap)cache.getNativeCache()).keySet().iterator();

            while(var4.hasNext()) {
                String cacheKey = (String)var4.next();
                if (cacheKey.contains(partialKey)) {
                    return cache.get(cacheKey).get();
                }
            }
        }

        return null;
    }
}
