package annotation.repository;

import org.springframework.stereotype.Repository;

@Repository
public class JdbcReposity implements UserRepository {
    public void save() {
        System.out.println("JdbcReposity save...");
    }
}
