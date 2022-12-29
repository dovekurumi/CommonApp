package com.jidouauto.testapp;

public class ClassOuter {

    Object object = new Object() {

        @Override
        public void finalize() {
            System.out.println("inner Free the occupied memory...");
        }
    };

    @Override
    public void finalize() {
        System.out.println("Outer Free the occupied memory...");
    }
}
