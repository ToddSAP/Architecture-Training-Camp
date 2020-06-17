package cache.impl;

import cache.interfaces.CacheManagement;
import cache.interfaces.CacheService;
import cache.interfaces.Config;

public class Cache implements CacheService, CacheManagement {
    @Override
    public void reBuild(Config conf) {
        System.out.println("重建缓存");
    }

    @Override
    public Object get(Object key) {
        System.out.println("查询缓存");
        return null;
    }

    @Override
    public void put(Object key, Object value) {
        System.out.println("增加缓存");
    }

    @Override
    public void delete(Object key) {
        System.out.println("删除缓存");
    }
}
