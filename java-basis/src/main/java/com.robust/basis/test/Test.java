package com.robust.basis.test;

/**
 * Created by bintao on 2017/8/14.
 */
public class Test {
    private final static String NAME = "parent";

    public void getField(){
        System.out.println(this.getNAME());
    }

    public String getNAME() {
        return NAME;
    }

    static class Son extends Test{
        private final static String NAME = "son";

        @Override
        public String getNAME() {
            return NAME;
        }
    }

    public static void main(String[] args) {
        Son son = new Son();
        son.getField();
    }
}
