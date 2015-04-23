package com.example.Brice.myapplication.cloud;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import java.util.logging.Logger;

import javax.inject.Named;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "carApi",
        version = "v1",
        resource = "car",
        namespace = @ApiNamespace(
                ownerDomain = "cloud.myapplication.Brice.example.com",
                ownerName = "cloud.myapplication.Brice.example.com",
                packagePath = ""
        )
)
public class CarEndpoint {

    private static final Logger logger = Logger.getLogger(CarEndpoint.class.getName());

    /**
     * This method gets the <code>Car</code> object associated with the specified <code>id</code>.
     *
     * @param id The id of the object to be returned.
     * @return The <code>Car</code> associated with <code>id</code>.
     */
    @ApiMethod(name = "getCar")
    public Car getCar(@Named("id") Long id) {
        // TODO: Implement this function
        logger.info("Calling getCar method");
        return null;
    }

    /**
     * This inserts a new <code>Car</code> object.
     *
     * @param car The object to be added.
     * @return The object to be added.
     */
    @ApiMethod(name = "insertCar")
    public Car insertCar(Car car) {
        // TODO: Implement this function
        logger.info("Calling insertCar method");
        return car;
    }
}