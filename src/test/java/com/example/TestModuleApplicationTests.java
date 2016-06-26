package com.example;

import com.example.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestModuleApplication.class)
@WebAppConfiguration
public class TestModuleApplicationTests {
    Logger logger = LoggerFactory.getLogger(TestModuleApplication.class);

    @Test
    public void contextLoads() {

    }

    /**
     * 获取二进制字节文件对象的三种方法
     */
    @Test
    public void testReflect() {
        try {
            Class c1 = Class.forName("com.example.entity.Student");
            logger.debug("{}", c1.getName());
            System.out.println(c1.getName());

            Class c2 = Student.class;
            System.out.println(c2.getName());

            Student s = new Student();
            Class c3 = s.getClass();
            System.out.println(c3.getName());

        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        }
    }

    /**
     * getField(String name)        返回一个 Field 对象       只能获取公共的属性
     * getFields()                      返回一个包含某些            Field 对象的数组
     * getDeclaredField(String name)    返回一个 Field 对象           获取所有的属性
     * getDeclaredFields()              返回 Field 对象的一个数组　　 获取所有的属性
     * getMethod(String name, Class<?>... parameterTypes)　返回一个 Method 对象，它反映此 Class 对象所表示的类或接口的指定公共成员方法。
     * getMethods()                     返回一个包含某些 Method 对象的数组
     */
    @Test
    public void testGetFields() throws Exception {
        Class c = Class.forName("com.example.entity.Student");
        logger.debug("{}", c.getName());
        System.out.println(c.getField("age"));
        //System.out.println(c1.getField("name"));
        System.out.println(c.getDeclaredField("name"));


    }

    /**
     * 获取方法
     *
     * @throws Exception
     */
    @Test
    public void testGetMethod() throws Exception {
        Class<Student> c = (Class<Student>) Class.forName("com.example.entity.Student");
        Method m = c.getMethod("getName");
        System.out.println(m.getName());
        Method m1 = c.getMethod("hello", String.class);
        System.out.println(m1.getName());
        Method m2 = c.getMethod("hello", String.class, int.class);
        System.out.println(m2.getName());

    }

    /**
     * 获取构造方法
     *
     * @throws Exception
     */
    @Test
    public void testGetConstruct() throws Exception {
        Class c = Class.forName("com.example.entity.Student");
        Constructor c1 = c.getConstructor();
        System.out.println(c1.getName());
        Constructor c2 = c.getConstructor(int.class, String.class);
        System.out.println(c2.getName());
    }

    /**
     * 获取构造方法，得到实例对象
     *
     * @throws Exception
     */
    @Test
    public void testGetInstance() throws Exception {
        //实例化无参的方法得到对象
        Class c = Class.forName("com.example.entity.Student");
        //实例化无参的方法得到对象
        Student s = (Student) c.newInstance();
        System.out.println(s);
        //用构造函数得到对象
        Constructor<Student> c1 = c.getConstructor();
        Student s2 = c1.newInstance();
        System.out.println(s2);
        //用参构造函数得到对象
        Constructor<Student> c2 = c.getConstructor(int.class, String.class);
        Student s3 = c2.newInstance(12, "tomLuo");
        System.out.println(s3);
    }

    /**
     * 发起方法
     *
     * @throws Exception
     */
    @Test
    public void testInvokeMethod() throws Exception {
        //实例化无参的方法得到对象
        Class c = Class.forName("com.example.entity.Student");
        Method m2 = c.getMethod("hello", String.class, int.class);
        System.out.println(m2.getName());
        //实例化无参的方法得到对象
        Student s = (Student) c.newInstance();
        //发起方法
        m2.invoke(c.newInstance(), "tomluo", 15);
    }

    /**
     * 如配置文件像这样
     * <bean id='student' class='com.example.entity.Student'>
     * <attr name='name'>tomLuo</attr>
     * <attr name='age'>18</attr>
     * </bean>
     *
     * @throws Exception
     */
    @Test
    public void testSetAttr() throws Exception {
        String c = "com.example.entity.Student";
        String name = "name";
        String age = "age";
        String v1 = "tomLuo";
        int v2 = 18;
        Class cc = Class.forName(c);
        Method m1 = cc.getMethod("set" + name.substring(0, 1).toUpperCase() + name.substring(1), String.class);
        Method m2 = cc.getMethod("set" + age.substring(0, 1).toUpperCase() + age.substring(1), int.class);


        //实例化无参的方法得到对象
        Student s = (Student) cc.newInstance();
        //发起方法
        m1.invoke(s, v1);
        m2.invoke(s, v2);

        System.out.println(s);

    }
}