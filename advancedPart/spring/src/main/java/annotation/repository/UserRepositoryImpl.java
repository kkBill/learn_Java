package annotation.repository;

import org.springframework.stereotype.Repository;

@Repository(value = "userRepository")
public class UserRepositoryImpl implements UserRepository {

    public void save() {
        System.out.println("UserRepositoryImpl save...");
    }
}
