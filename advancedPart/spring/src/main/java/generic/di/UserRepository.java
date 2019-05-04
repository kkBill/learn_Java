package generic.di;


import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends BaseRepository<User> {
    @Override
    public String toString() {
        return "UserRepository{}";
    }
}
