package com.southwind.ioc;

import com.southwind.entity.Student;

public class Test {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml" );
        Student student = (Student) applicationContext.getBean("student");
        System.out.println(student);
    }
}
