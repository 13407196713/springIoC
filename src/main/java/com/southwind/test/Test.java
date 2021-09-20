package com.southwind.test;

import com.southwind.entity.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
//        Student student = new Student();
//        student.setId(1L);
//        student.setName("张三");
//        student.setAge(22);
//        System.out.println(student);

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        Student student=(Student) applicationContext.getBean("student2");
        System.out.println(student);

        Student stu=(Student) applicationContext.getBean("stu");
        System.out.println(stu);



    }
}
