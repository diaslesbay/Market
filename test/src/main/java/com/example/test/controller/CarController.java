package com.example.test.controller;

import com.example.test.dto.ManufacturerCarCountDTO;
import com.example.test.model.Car;
import com.example.test.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping("/")
    public List<Car> getAllCars() {return carService.getAllClass();}

    @GetMapping("/manufacturer/{manufacturer}")
    public List<Car> getCarsByManufacturer(@PathVariable String manufacturer) {return carService.getCarsByManufacturer(manufacturer);}

    @GetMapping("/year/{year}")
    public List<Car> getCarsByYear(@PathVariable Integer year) {
        return carService.getCarsByYear(year);
    }

    @GetMapping("/volume/{volume}")
    public List<Car> getCarsByVolume(@PathVariable double volume) {
        return carService.getCarsByVolume(volume);
    }

    @GetMapping("/type/{type}")
    public List<Car> getCarsByType(@PathVariable String type) {
        return carService.getCarsByType(type);
    }

    @GetMapping("/manufacturers")
    public List<String> getManufacturer() {
        return carService.getManufacturer();
    }

    @GetMapping("/manufacturers-and-count")
    public List<ManufacturerCarCountDTO> getManufacturersAndCarCounts() {return carService.getManufacturersAndCarCounts();}

    @GetMapping("/manufacturer-with-max-car-count")
    public List<Object[]> getManufacturerWithMaxCarCount() {
        return carService.getManufacturerWithMaxCarCount();
    }

    @GetMapping("/manufacturer-with-min-car-count")
    public List<Object[]> getManufacturerWithMinCarCount() {
        return carService.getManufacturerWithMinCarCount();
    }

    @GetMapping("/cars-by-year/{year}")
    public List<Car> getCarsByYear(@PathVariable int year) {
        return carService.getCarsByYearOfManufacture(year);
    }

    @GetMapping("/cars-by-year-range/{startYear}/{endYear}")
    public List<Car> getCarsByYearRange(@PathVariable int startYear, @PathVariable int endYear) {return carService.getCarsByYearRange(startYear, endYear);}


    @PostMapping("/add")
    public Car addCar(@RequestBody Car car){
        return carService.save(car);
    }

    @DeleteMapping("/delete/{id}")
    public String addCar(@PathVariable Long id){
        return carService.delete(id);
    }

    @PatchMapping("/update/{id}")
    public String updateCar(@PathVariable Long id, @RequestBody Car updatedCar) {
        return carService.updateCar(id, updatedCar);
    }

}
