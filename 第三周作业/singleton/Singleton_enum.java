package singleton;

public enum Singleton_enum {
    /**
     * 优点：利于语言内置特性解决了线程安全和单例对象创建问题，而且规避了利用反射强行再创建单例对象的问题
     * 缺点：利用enum来做业务对象，语义上不太合适
     */
    INSTANCE;
}
