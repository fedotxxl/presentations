package ru.gramant.presentations.log.car;

import io.belov.soyuz.utils.to;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created on 05.04.17.
 */
public class CarJob {

    private CarService carService;
    private List<Car> cars;

    @Autowired
    public CarJob(CarService carService) {
        this.carService = carService;
        this.cars = to.list(new Car("lada"), new Car("bmw"));
    }


    @PostConstruct
    private void setUp() {
        //daemon to wash cars
    }






































//    @PostConstruct
//    private void setUp() {
//        to.daemonForever("car-job", 5000, this::washCars).start();
//    }
//
//    private void washCars() {
//        cars.forEach(carService::wash);
//    }

}
