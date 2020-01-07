package com.robust.basis.jvm.lazyloading;

/**
 * @Description:
 * @Author: robust
 * @CreateDate: 2019/12/3 9:03
 * @Version: 1.0
 */
public class T_02_1 {
    public static void main(String[]args){
        /**
         *被动使用类字段演示三：
         *常量在编译阶段会存入调用类的常量池中，本质上并没有直接引用到定义常量的类，因此不会触发定义常量的类的初始化。
         **/
        /**
         * 可通过jclasslib插件进行查看,在Constant Pool里会有hello world常量存在
         */
        System.out.println(ConstClass.HELLOWORLD);
    }
}
