package demofornote.IoC2;

import demofornote.Person;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

public class MainTest {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanRegistry = new DefaultListableBeanFactory();
        BeanFactory container = bindViaXMLFile(beanRegistry);
        Person Tom = (Person) container.getBean("Tom");

        //测试
        System.out.println(Tom);
        Tom.getCar().drive();
        Tom.getPet().run();
    }

    public static BeanFactory bindViaXMLFile(BeanDefinitionRegistry registry){
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(registry);
        reader.loadBeanDefinitions("ioc-config.xml");
        return (BeanFactory)registry;
    }
}
