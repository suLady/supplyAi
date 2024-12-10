package springboot.example.supplyAi.utils;


public class ThreadLocalUtil {
    private static final ThreadLocal thread_local = new ThreadLocal();

    //获取键值对
    public static<T> T get(){
        return (T) thread_local.get();
    }

    //存储键值对
    public static void set(Object value){
        thread_local.set(value);
    }

    //移除thread_local,防止内存泄漏
    public static void remove(){
        thread_local.remove();
    }
}
