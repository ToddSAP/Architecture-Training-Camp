### 作业一、 请简述JVM垃圾回收原理。   
答：   
JVM垃圾回收指的是对JVM中不再被使用的对象做内存回收，以便于内存的循环使用，回收的关键是正确找到不再被使用的对象。  
一般有两种方法来寻找不再使用的对象，引用计数法和可达性分析法。  
引用计数法是指给每个对象分配一个引用计数器，对象被引用一次，计数器就加一，引用被释放，计数器减一。这个方法的好处是简单高效，但有一个致命缺点是无法解决对循环引用对象的判定，容易引起内存泄露。  
可达性分析法是指从GC Roots开始递归寻找引用链，在引用链上的对象都是可达的，即正在被使用的，除此之外的都是需要被回收的对象。GC Roots一般是栈中的引用、方法区中的静态变量和常量，及本地方法区中的引用。这个方法的好处是可以正确找出所有未使用的对象，包括循环引用的对象，缺点是执行时间往往较长。  
找到不再使用对象后，JVM垃圾回收的具体做法一般有三种：标记-清除、复制、分代回收。  
标记-清除的做法是按照之前的方法标记垃圾对象，然后直接回收这些对象的内存。好处是简单直接，缺点是会造成内存碎片，而碎片整理是比较耗时的。  
复制的做法是将内存分成大小相同的两块，每次只使用其中一块。当GC时，将该块内存中的存活对象复制到另一块，然后把整块内存做回收。好处是简单高效、没有内存碎片，缺点是只能使用一半的内存，浪费资源。  
分代回收的做法是将内存分成新生代和老年代，新生代用来创建新对象，老年代用来存放在若干次GC后依然存活的对象。
新生代又可细分成Eden区和两个Survivor区。新对象都在Eden区创建，当Eden区满了，会触发Young GC，对Eden区做标记，存活对象会被赋值到一个Survior区，然后清理整块Eden区。下次Young GC会将Eden区和Survivor区的存活对象一起复制到另一个Survivor区。在Survivor区满时，会触发Full GC，将Eden和Survivor区的存活对象复制到老年代，并将老年代中不再使用的对象做标记-清理。标记-清理会产生碎片，Full GC往往也要做碎片整理。  
目前市面上的垃圾回收器一般使用可达性分析和分代回收算法，不同的垃圾回收器会有侧重点的优化GC的停顿时间，使得卡顿现象减少或缩短。  

### 作业二、设计一个秒杀系统，主要的挑战和问题有哪些？核心的架构方案或者思路有哪些？  
答：  
一个秒杀系统面对的主要挑战是短时间内会有海量并发请求到达服务器，主要问题是如何处理这些请求，使得系统不会因为系统资源耗尽而崩溃。  
针对高并发问题，有几个点可以重点设计：
1. 隔离业务系统和秒杀系统    
秒杀往往只涉及很少的业务提交，但会有海量的业务请求，这意味着绝大部分的请求都是无效的，而一个业务请求的处理往往涉及多次调用，如业务规则校验、资源锁定等，而大量无效请求会急剧降低系统的可用资源，如工作线程、CPU等，导致系统崩溃。故将两个系统隔离开来可以保护业务系统。   
2. 限流   
秒杀的规则往往是先到先得，超出商品数量的请求一般都是无效的。故在请求到达服务器之前，可以按时间或业务规则过滤请求，只让有效的请求到达服务器，无效的请求直接拒绝，可以极大减少服务器压力。  
3. 缓存商品信息  
秒杀时商品信息和秒杀界面的访问量会飙升，可利用CDN、反向代理将商品信息和界面静态资源缓存，提升加载速度并降低系统负载。  
4. 削峰  
如果需要较大量的请求通过限流，可使用消息队列存储请求，后端服务可按正常速度处理请求，而不会被瞬时大量请求压垮。  
5. 安全  
秒杀要保证秒杀前商品链接不可用或不能泄露，可以使用后端定时器或发布不同版本的JS来保护商品链接。  




### 总结  



