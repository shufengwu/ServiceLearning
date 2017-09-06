// MyAidlService.aidl
package com.delta.servicelearning;

// Declare any non-default types here with import statements

interface MyAidlService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    int plus(int a,int b);
    String toUpperCase(String str);
}
