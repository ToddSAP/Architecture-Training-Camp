# 作业

## 1. 请描述什么是依赖倒置原则，为什么有时候依赖倒置原则又被称为好莱坞原则？
 - 高层模块不应该依赖于底层模块，两者都应该依赖抽象  
 - 抽象不应该依赖于细节  
 - 细节应该依赖于抽象 
 - 高层模块就是在调用关系上位于上层的模块，而底层模块就是位于底层的模块。  
 - 如果高层模块直接依赖底层模块会导致强耦合，不利于扩展和复用。  
 - 在实践上，可以用接口来作为抽象，模块间通过依赖接口来建立调用关系。实现接口的类可以在需要时动态注入，从而实现代码的解耦和复用。  

## 2. 请描述一个你熟悉的框架，是如何实现依赖倒置原则的。
 - JDK中序列化：
    - 所有需要支持序列化的类都需要实现Serializable接口，即依赖抽象Serializable接口，而JDK序列化框架也依赖于Serializable接口来判定是否可以执行序列化。  
 - Spring中类注册--配置文件方式：
    - 需要被Spring托管的类都需要按格式配置在applicationContext*.xml中，而Spring框架也是读取配置文件然后完成类注册。配置文件就是抽象，而上、下层模块都依赖抽象。  

## 3. 请用接口隔离原则优化Cache类的设计，画出优化后的类图。  
 - 将Cache接口分解为CacheService和CacheManagement两个接口，其中CacheService暴露给应用程序，而CacheManagement暴露给系统管理模块调用。  
 - 在应用程序类中使用CacheService，注入Cache类后，应用程序将仅能看到`get、put`和`delete`方法。  
 - 在系统管理类中使用CacheManagement，注入Cache类后，应用程序将仅能看到`reBuild`方法。    
 [代码](https://github.com/ToddSAP/Architecture-Training-Camp/tree/master/%E7%AC%AC%E4%BA%8C%E5%91%A8%E4%BD%9C%E4%B8%9A/cache)  
 ![类图](https://github.com/ToddSAP/Architecture-Training-Camp/blob/master/%E7%AC%AC%E4%BA%8C%E5%91%A8%E4%BD%9C%E4%B8%9A/%E6%8E%A5%E5%8F%A3%E9%9A%94%E7%A6%BB%E7%B1%BB%E5%9B%BE.png)
