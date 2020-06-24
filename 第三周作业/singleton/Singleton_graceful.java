package singleton;

public class Singleton_graceful {
    /**
     * 优点：兼顾了延迟加载和线程安全，且编码优雅简单
     * 缺点：基本没有
     * 场景：普世
     */
    private Singleton_graceful() {}

    private static class SingletonHolder {
        private static Singleton_graceful instance = new Singleton_graceful();
    }

    public static Singleton_graceful getInstance() {
        return SingletonHolder.instance;
    }
}
