package cache.impl;

public class ApplicationClass {
    // 可annotation注入
    private Cache cache;

    public ApplicationClass(Cache cache) {
        this.cache = cache;
    }

    public Object queryBySomeBusinessKey(Object key) {
        Object result = cache.get(key);
        if (result == null) {
            // db query
            Object dbResult = new Object();

            // update cache
            cache.put(key, dbResult);
            result = dbResult;
        }
        return result;
    }

    public boolean deleteBySomeBusinessKey(Object key) {
        try {
            // db deletion

            // delete cache
            cache.delete(key);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
