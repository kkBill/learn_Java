package demofornote.IoC1;

import demofornote.Person;
import demofornote.Car;
import demofornote.Pet;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

public class MainTest {

    //private BeanFactory beanFactory;

    public static void main(String[] args) {
        DefaultListableBeanFactory beanRegistry = new DefaultListableBeanFactory();
        BeanFactory container = bindViaCode(beanRegistry);
        Person person = (Person)container.getBean("person");

        //测试
        System.out.println(person);
        person.getCar().drive();
        person.getPet().run();
    }

    public static BeanFactory bindViaCode(BeanDefinitionRegistry registry){
        AbstractBeanDefinition person = new RootBeanDefinition(Person.class);
        AbstractBeanDefinition pet = new RootBeanDefinition(Pet.class);
        AbstractBeanDefinition car = new RootBeanDefinition(Car.class);

        //将bean的定义 注册到容器中
        registry.registerBeanDefinition("person",person);
        registry.registerBeanDefinition("pet",pet);
        registry.registerBeanDefinition("car",car);

        //指定依赖关系
        //1. 可以通过构造器方法注入
//        ConstructorArgumentValues argumentValues = new ConstructorArgumentValues();
//        argumentValues.addIndexedArgumentValue(0,pet);
//        argumentValues.addIndexedArgumentValue(1,car);
//        person.setConstructorArgumentValues(argumentValues);

        //2. 通过setter方法注入
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("pet",pet));
        propertyValues.addPropertyValue(new PropertyValue("car",car));
        person.setPropertyValues(propertyValues);

        //绑定完成
        return (BeanFactory)registry;
    }

}
