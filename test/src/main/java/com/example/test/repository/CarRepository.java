package com.example.test.repository;

import com.example.test.enums.TypeOfCar;
import com.example.test.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface CarRepository  extends JpaRepository<Car, Long> {
    List<Car> findCarByManufacturer(String manufacturer);
    List<Car> findByYear(Integer year);
    List<Car> findByVolume(double volume);
    List<Car> findByType(TypeOfCar type);

    @Query("SELECT DISTINCT  c.manufacturer FROM Car c")
    List<String> findManufacturer();

    @Query("SELECT c.manufacturer, COUNT(c) FROM Car c GROUP BY c.manufacturer")
    List<Object[]> findManufacturersAndCarCounts();
    @Query("SELECT c.manufacturer, COUNT(c) FROM Car c GROUP BY c.manufacturer ORDER BY COUNT(c) DESC LIMIT 1")
    List<Object[]> findManufacturerWithMaxCarCount();

    @Query("SELECT c.manufacturer, COUNT(c) FROM Car c GROUP BY c.manufacturer ORDER BY COUNT(c) ASC LIMIT 1")
    List<Object[]> findManufacturerWithMinCarCount();

    @Query("SELECT c FROM Car c WHERE c.year = :year")
    List<Car> findCarsByYearOfManufacture(int year);

    @Query("SELECT c FROM Car c WHERE c.year BETWEEN :startYear AND :endYear")
    List<Car> findCarsByYearRange(int startYear, int endYear);
}

