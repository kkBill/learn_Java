package generic.di;

import org.springframework.beans.factory.annotation.Autowired;

public class BaseService<T> {

    //这里实际上注入的是BaseRepository的实现类
    @Autowired
    protected BaseRepository<T> repository;

    public void add(){
        System.out.println("add...");
        System.out.println(repository);
    }
}
