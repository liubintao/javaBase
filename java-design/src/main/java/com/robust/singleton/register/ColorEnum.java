package com.robust.singleton.register;

/**
 * @author bintao
 * @Despription
 * @date 2018/9/1 12:44
 */
public enum ColorEnum {
    RED(){
        private int r = 255;
        private int g = 0;
        private int b = 0;

        @Override
        public String toString() {
            return r + "_" + g + "_" + b;
        }
    },BLACK(){
        private int r = 0;
        private int g = 0;
        private int b = 255;
    },WHITE(){
        private int r = 255;
        private int g = 255;
        private int b = 255;
    };
}
