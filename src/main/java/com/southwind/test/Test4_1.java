package com.southwind.test;

import com.southwind.entity.Car;
import com.southwind.factory.InstanceCarFactory;

public class Test4_1 {
    public static void main(String[] args) {
        InstanceCarFactory instanceCarFactory = new InstanceCarFactory();
        Car car = instanceCarFactory.getCar(1L);
        System.out.println(car);
    }
}
