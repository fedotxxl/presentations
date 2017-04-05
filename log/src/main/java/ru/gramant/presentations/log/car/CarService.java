package ru.gramant.presentations.log.car;

import io.belov.soyuz.log.Mdc;
import io.belov.soyuz.utils.to;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created on 05.04.17.
 */
public class CarService {

    public static Logger log = LoggerFactory.getLogger(CarService.class);

    void wash(Car car) {
        if (log.isDebugEnabled()) {
            log.debug("I'm washing car {}", car);
        } else {
            log.warn("I'm washing car {}", car.getName());
        }
    }

    void washWithMdc(Car car) {
        Mdc.with(to.map("car", car.getName()),() -> {
            log.warn("I'm washing car");
        });
    }







































//    public static LoggerEvents loge = LoggerEvents.getInstance(CarService.class);
//
//    void wash(Car car) {
//        if (loge.isDebugEnabled()) {
//            loge.debug("car.wash.withAdditionalInfo", to.map("car", car));
//        } else {
//            loge.warn("car.wash", to.map("car", car.getName()));
//        }
//    }
//
//    void washWithMdc(Car car) {
//        Mdc.with(to.map("car", car.getName()),() -> {
//            loge.info("car.wash.withMdc");
//        });
//    }


}
