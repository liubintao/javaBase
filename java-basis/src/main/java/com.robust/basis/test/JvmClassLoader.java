package com.robust.basis.test;

/**
 * Created by neil on 2017/5/4.
 */
public class JvmClassLoader {

    private static JvmClassLoader jcl = new JvmClassLoader();
    public static int count1;
    public static int count2 = 4;

    private JvmClassLoader(){
        count1++;
        count2++;
    }

    public static JvmClassLoader getInstance(){
        return jcl;
    }

    public static void main(String[] args) {
        JvmClassLoader jcl = JvmClassLoader.getInstance();
        System.out.println(jcl.count1);
        System.out.println(jcl.count2);

        JvmClassLoader jc2 = JvmClassLoader.getInstance();
        System.out.println(jc2.count1);
        System.out.println(jc2.count2);

    }
}
