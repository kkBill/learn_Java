package annotation.service;

import annotation.repository.JdbcReposity;
import annotation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    /**
     *  由于UserRepository接口有多个实现，而@Autowired在装配多个类型兼容的 Bean 时会失效，
     *  故用@Qualifier("JdbcReposity") 来指定装配哪一个bean
     */
    @Autowired
    @Qualifier("jdbcReposity")
    private UserRepository userRepository;
    public void  add(){
        System.out.println("UserService add...");
        userRepository.save();
    }
}
