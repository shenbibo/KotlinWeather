package com.sky.kotlinweather.base;

import android.os.SystemClock;

import static android.R.attr.x;

/**
 * 一句话描述类的作用
 * 详述类的功能。
 * Created by sky on 2017/9/26.
 */
public class TEST {
    interface Base {
        void print();
        void print2();
    }

    class BaseImpl implements Base {
        private int value;
        public BaseImpl(int value) {this.value = value;}

        @Override public void print() {System.out.print(value);}

        @Override public void print2() {System.out.print(value + 2);}
    }

    class TestImpl implements Base {
        private Base delegate;

        TestImpl(Base delegate) { this.delegate = delegate;}

        @Override public void print() {delegate.print();}

        @Override public void print2() {System.out.print("TestImpl");}
    }

    public void test() {
        Base baseImpl = new BaseImpl(10);
        Base testImpl = new TestImpl(baseImpl);
        testImpl.print();   // 10
        testImpl.print2();  // TestImpl
    }
}
