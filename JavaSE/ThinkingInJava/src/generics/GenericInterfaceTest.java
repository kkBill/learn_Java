package generics;

import java.util.Random;

/**
 * 泛型除了可以应用于类，还可以应用于接口。该接口的不同实现类可以指定不同的类型
 * @param <T>
 */
interface Generator<T> {
    T get();
}

class CarGenerator implements Generator<String> {
    private String[] cars = new String[]{"BMW", "Tesla", "Audi"};

    @Override
    public String get() {
        Random rand = new Random();
        return cars[rand.nextInt(cars.length)];
    }
}

public class GenericInterfaceTest {
    public static void main(String[] args) {
        CarGenerator carGenerator = new CarGenerator();
        for (int i = 0; i < 5; i++) {
            System.out.println(carGenerator.get());
        }
    }
}
