package com.example.exceldemo.designMode;


/**
 * 单例模式：懒汉模式
 * 优点：延迟加载（需要的时候才去加载）,适合单线程操作
 * 缺点： 线程不安全，在多线程中很容易出现不同步的情况，如在数据库对象进行的频繁读写操作时。
 */
public class Singleton {
    /* 持有私有静态实例，防止被引用，此处赋值为null，目的是实现延迟加载 */
    private static Singleton singleton = null;
    /* 私有构造方法，防止被实例化 */
    private Singleton(){}
    /* 1:懒汉式，静态工程方法，创建实例 */
    public static Singleton getInstance(){
        if (singleton==null){
            singleton = new Singleton();
        }
        return  singleton;
    }
}


/**
 * 单例模式：双重线程检查模式
 * 优点：延迟加载，线程安全
 * 缺点： 写法复杂，不简洁
 */
class SingletonInner{

    private static volatile SingletonInner singletonInner=null;

    private SingletonInner(){}

    public static SingletonInner getInstance(){
        SingletonInner sin = singletonInner;
        if (sin==null){
            synchronized (SingletonInner.class){
                sin = singletonInner;
                if (sin==null){
                    sin=new SingletonInner();
                }
            }
        }

        return sin;
    }

    protected void method(){
        System.out.println("SingletonInner");
    }
}

/**
 * 单例模式：内部类
 * 优点：延迟加载，线程安全（java中class加载时互斥的），也减少了内存消耗，推荐使用内部类方式。
 */
class SingletonInnerd{

    private static  class SingletonHolder{
        private static SingletonInnerd instance = new SingletonInnerd();
    }

    private SingletonInnerd(){}

    public static SingletonInnerd getInstance(){
        return SingletonHolder.instance;
    }

    protected static void method(){
        System.out.println("内部类");
    }

}