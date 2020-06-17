package cache.interfaces;

public interface CacheService {
    Object get(Object key);
    void put(Object key, Object value);
    void delete(Object key);
}
