package com.example.test.service;

import com.example.test.dto.ManufacturerCarCountDTO;
import com.example.test.enums.TypeOfCar;
import com.example.test.model.Car;
import com.example.test.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService {
    @Autowired
    private  CarRepository carRepository;

    public List<Car> getAllClass(){
        return carRepository.findAll();
    }
    public List<Car> getCarsByManufacturer(String manufacturer){
        return carRepository.findCarByManufacturer(manufacturer);
    }
    public List<Car> getCarsByYear(Integer year){
        return carRepository.findByYear(year);
    }
    public List<Car> getCarsByVolume(double volume){
        return carRepository.findByVolume(volume);
    }
    public List<Car> getCarsByType(String type){
        TypeOfCar typeOfCar = TypeOfCar.valueOf(type);
        return carRepository.findByType(typeOfCar);
    }


    public List<String> getManufacturer() {
        return carRepository.findManufacturer();
    }

    public List<ManufacturerCarCountDTO> getManufacturersAndCarCounts() {
        List<Object[]> manufacturerCounts = carRepository.findManufacturersAndCarCounts();
        List<ManufacturerCarCountDTO> result = new ArrayList<>();

        for (Object[] obj : manufacturerCounts) {
            String manufacturer = (String) obj[0];
            Long carCount = (Long) obj[1];
            result.add(new ManufacturerCarCountDTO(manufacturer, carCount));
        }

        return result;
    }

    public List<Object[]> getManufacturerWithMaxCarCount() {
        return carRepository.findManufacturerWithMaxCarCount();
    }

    public List<Object[]> getManufacturerWithMinCarCount() {
        return carRepository.findManufacturerWithMinCarCount();
    }

    public List<Car> getCarsByYearOfManufacture(int year) {
        return carRepository.findCarsByYearOfManufacture(year);
    }

    public List<Car> getCarsByYearRange(int startYear, int endYear) {
        return carRepository.findCarsByYearRange(startYear, endYear);
    }

    public Car save(Car car){
        return carRepository.save(car);
    }
    public String delete(Long id){
        if(carRepository.existsById(id)) {
            carRepository.deleteById(id);
            return "Car with ID " + id + " deleted successfully";
        }else return "Car with ID " + id + " not found!";
    }

    public String updateCar(Long id, Car updatedCar) {
        if (carRepository.existsById(id)) {
            Car car = carRepository.findById(id).get();
            car.setCar_name(updatedCar.getCar_name());
            car.setYear(updatedCar.getYear());
            car.setType(updatedCar.getType());
            car.setVolume(updatedCar.getVolume());
            car.setManufacturer(updatedCar.getManufacturer());
            carRepository.save(car);
            return "Car with ID " + id + " updated successfully";
        } else {
            return "Car with ID " + id + " not found";
        }
    }


}
