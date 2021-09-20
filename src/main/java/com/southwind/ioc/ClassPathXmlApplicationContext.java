package com.southwind.ioc;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ClassPathXmlApplicationContext implements ApplicationContext {
    private Map<String, Object> ioc = new HashMap<String, Object>();
    public ClassPathXmlApplicationContext(String path){
        try {
            SAXReader reader = new SAXReader();
            Document document = reader.read("./src/main/resources/" + path);
            Element root = document.getRootElement();
            Iterator<Element> iterator = root.elementIterator();
            while(iterator.hasNext()){
                Element element = iterator.next();
                String id = element.attributeValue("id");
                String className = element.attributeValue("class");

                // 通过反射机制 创建对象，拿到class
                Class clazz = Class.forName(className);
                // 获取 无参构造
                Constructor constructor = clazz.getConstructor();

                Object object = constructor.newInstance();

                // 给 目标对象 赋值
                Iterator<Element> beanIter = element.elementIterator();
                while(beanIter.hasNext()){
                    Element property = beanIter.next();
                    String name = property.attributeValue("name");
                    String valueStr = property.attributeValue("value");

                    String ref = property.attributeValue("ref");
                    if(ref == null){ // 普通赋值
                        String methodName = "set" + name.substring(0,1).toUpperCase() + name.substring(1);

                        Field field = clazz.getDeclaredField(name);
                        Method method = clazz.getDeclaredMethod(methodName, field.getType());

                        // 根据成员变量的数据类型将 value 进行转换
                        Object value= null;
                        if(field.getType().getName() == "long"){
                            value = Long.parseLong(valueStr);
                        }
                        if(field.getType().getName() == "java.lang.String"){
                            value = valueStr;
                        }
                        if(field.getType().getName() == "int"){
                            value = Integer.parseInt(valueStr);
                        }
                        method.invoke(object, value);//赋值
                    }
                    ioc.put(id, object);
                }
            }
            Object obj1 = ioc.get("address");
            Object obj2 = ioc.get("student");
            System.out.println(obj1);
            System.out.println(obj2);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (NoSuchMethodException e){
            e.printStackTrace();
        } catch (InstantiationException e){
            e.printStackTrace();
        } catch (InvocationTargetException e){
            e.printStackTrace();
        } catch (IllegalAccessException e){
            e.printStackTrace();
        } catch (NoSuchFieldException e){
            e.printStackTrace();
        }
    }

    public Object getBean(String id) {
        return ioc.get(id);
    }
}
