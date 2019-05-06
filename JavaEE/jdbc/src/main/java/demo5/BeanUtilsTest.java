package demo5;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

public class BeanUtilsTest {
    @Test
    public void testSetProperty() throws Exception{
        Object stu = new Student();
        System.out.println(stu);

        //通过BeanUtils的setProperty()方法给对象的属性赋值
        BeanUtils.setProperty(stu, "id","1010");
        System.out.println(stu);
    }

}
