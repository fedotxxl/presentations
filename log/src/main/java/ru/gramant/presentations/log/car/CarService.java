package ru.gramant.presentations.log.car;

import io.belov.soyuz.log.LoggerEvents;
import io.belov.soyuz.log.Mdc;
import io.belov.soyuz.utils.to;

/**
 * Created on 05.04.17.
 */
public class CarService {

    public static LoggerEvents loge = LoggerEvents.getInstance(CarService.class);

    void wash(Car car) {
        if (loge.isDebugEnabled()) {
            loge.debug("car.wash.withAdditionalInfo", to.map("car", car));
        } else {
            loge.warn("car.wash", to.map("car", car.getName()));
        }
    }

    void washWithMdc(Car car) {
        Mdc.with(to.map("car", car.getName()),() -> {
            loge.info("car.wash.withMdc");
        });
    }


}
