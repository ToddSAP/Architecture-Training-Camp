package singleton;

public class Singleton_lazy {
    /**
     * 优点：在调用getInstance的时候才会创建单例变量，有延迟加载的功效，可减少类加载时间
     * 缺点：需要考虑线程安全问题，编码要避的坑多
     * 场景：适用启动时间敏感
     */
    private Singleton_lazy() {}
    private static volatile Singleton_lazy instance = null;
    public static Singleton_lazy getInstance() {
        if (instance == null) {
            synchronized (Singleton_lazy.class) {
                if (instance == null) {
                    instance = new Singleton_lazy();
                }
            }
        }
        return instance;
    }
}
