package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Car;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CarDAO {
    private List<Car> cars;

    private int ID;

    {
        cars = new ArrayList<>();
        cars.add(new Car(++ID, "BMW", 3));
        cars.add(new Car(++ID, "Audi", 2));
        cars.add(new Car(++ID, "Lexus", 1));
        cars.add(new Car(++ID, "Ford", 5));
        cars.add(new Car(++ID, "Kia", 7));
    }

    public List<Car> getCars(int count) {
        return cars.stream().limit(count).toList();
    }

    public List<Car> getAllCars() {
        return cars;
    }
}
