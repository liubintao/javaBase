package com.robust.basis.jvm.lazyloading;

/**
 * @Description:
 * @Author: robust
 * @CreateDate: 2019/12/3 8:41
 * @Version: 1.0
 */
public class T_02 {
    static{
        System.out.println("SuperClass init！");
    }
    public static int value=123;
}
class SubClass extends T_02{
    static{
        System.out.println("SubClass init！");
    }
}

class ConstClass{
    static{
        System.out.println("ConstClass init！");
    }
    public static final String HELLOWORLD="hello world";
}
/**
 *非主动使用类字段演示
 **/
class NotInitialization{
    public static void main(String[]args){
        /*被动使用类字段演示一：通过子类引用父类的静态字段，不会导致子类初始化*/
//        System.out.println(SubClass.value);
        /**
         *被动使用类字段演示二：
         *通过数组定义来引用类，不会触发此类的初始化
         **/
        T_02[] array = new T_02[10];

    }
}

