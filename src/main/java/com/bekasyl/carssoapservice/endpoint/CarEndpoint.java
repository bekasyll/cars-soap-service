package com.bekasyl.carssoapservice.endpoint;

import com.bekasyl.soap_service.GetCarRequest;
import com.bekasyl.soap_service.GetCarResponse;
import com.bekasyl.carssoapservice.model.Car;
import com.bekasyl.carssoapservice.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.List;

@Endpoint
@RequiredArgsConstructor
public class CarEndpoint {
    private final CarRepository carRepository;

    private static final String NAMESPACE_URI = "https://bekasyl.com/soap-service";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCarRequest")
    @ResponsePayload
    public GetCarResponse getCars(@RequestPayload GetCarRequest request) {
        GetCarResponse response = new GetCarResponse();

        List<Car> cars = carRepository.findByBrandAndModel(request.getBrand(), request.getModel());

        List<com.bekasyl.soap_service.Car> responseCars = new ArrayList<>();
        for (Car c : cars) {
            com.bekasyl.soap_service.Car car = new com.bekasyl.soap_service.Car();
            car.setBrand(c.getBrand());
            car.setModel(c.getModel());
            car.setColor(c.getColor());
            car.setYear(c.getYear());
            car.setCountryOfOrigin(c.getCountryOfOrigin());

            responseCars.add(car);
        }

        response.getCar().addAll(responseCars);
        return response;
    }
}
