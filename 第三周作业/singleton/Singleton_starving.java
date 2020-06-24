package singleton;

public class Singleton_starving {
    /**
     * 优点：简单粗暴，编码容易，没有线程安全问题
     * 缺点：类加载时就会初始化单例变量，会延长类加载时间
     * 场景：适用于启动时间不敏感，且单例对象创建耗时的case
     */
    private Singleton_starving() {}
    private static final Singleton_starving instance = new Singleton_starving();
    public static Singleton_starving getInstance() {
        return instance;
    }
}
