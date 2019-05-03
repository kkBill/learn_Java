package annotation.repository;

import annotation.TestObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    /**
     *  默认情况下，required为true。表明被装配的这个bean一定是要存在的，即对应的类被标记为@Component
     *  就本例而言，如果TestObject类前面没有加上@Component注解，就会抛出异常
     *  No qualifying bean of type 'annotation.TestObject' available: expected at least 1 bean which qualifies as autowire candidate.
     *  若某一属性允许不被设置，可以设置 @Authwired 注解的 required 属性为 false
     */

    @Autowired(required = false)
    private TestObject testObject;


    public void save() {
        System.out.println("UserRepositoryImpl save...");
    }
}
