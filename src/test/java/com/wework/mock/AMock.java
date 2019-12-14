package com.wework.mock;

public class AMock {

    /**
     * 待mock对象
     * 复杂逻辑方法 or 难以制造出符合条件的数据的方法
     * @param a
     * @param b
     */
    public int complex(int a,int b) {
        return a/b;
    }

    public int divid(int a,int b,int c) {
        return complex(a,b)/c;
    }
}
