package com.example.test.service;

import com.example.test.enums.TypeOfUser;
import com.example.test.model.User;
import com.example.test.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public Optional<User> findByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }

    @Transactional
    public void create(User user) {
        User user1 = new User();
        user1.setUsername(user.getUsername());
        user1.setEmail(user.getEmail());
        user1.setAddress(user.getAddress());
        user1.setFirstname(user.getFirstname());
        user1.setLastname(user.getLastname());
        user1.setPhoneNumber(user.getPhoneNumber());
        user1.setPassword(passwordEncoder.encode(user.getPassword()));
        user1.setStatus(TypeOfUser.REGISTERED_USER);
        userRepository.save(user1);
    }



    //
//    public List<User> getAllClass(){
//        return userRepository.findAll();
//    }
//    public List<User> getUserByUserId(String id){return userRepository.findUserByUserId(id);}
//

//    public List<Car> getCarsByYear(Integer year){
//        return carRepository.findByYear(year);
//    }
//    public List<Car> getCarsByVolume(double volume){
//        return carRepository.findByVolume(volume);
//    }
//    public List<Car> getCarsByType(String type){
//        TypeOfCar typeOfCar = TypeOfCar.valueOf(type);
//        return carRepository.findByType(typeOfCar);
//    }
//
//
//    public List<String> getManufacturer() {
//        return carRepository.findManufacturer();
//    }
//
//    public List<ManufacturerCarCountDTO> getManufacturersAndCarCounts() {
//        List<Object[]> manufacturerCounts = carRepository.findManufacturersAndCarCounts();
//        List<ManufacturerCarCountDTO> result = new ArrayList<>();
//
//        for (Object[] obj : manufacturerCounts) {
//            String manufacturer = (String) obj[0];
//            Long carCount = (Long) obj[1];
//            result.add(new ManufacturerCarCountDTO(manufacturer, carCount));
//        }
//
//        return result;
//    }
//
//    public List<Object[]> getManufacturerWithMaxCarCount() {
//        return carRepository.findManufacturerWithMaxCarCount();
//    }
//
//    public List<Object[]> getManufacturerWithMinCarCount() {
//        return carRepository.findManufacturerWithMinCarCount();
//    }
//
//    public List<Car> getCarsByYearOfManufacture(int year) {
//        return carRepository.findCarsByYearOfManufacture(year);
//    }
//
//    public List<Car> getCarsByYearRange(int startYear, int endYear) {
//        return carRepository.findCarsByYearRange(startYear, endYear);
//    }
//
//    public Car save(Car car){
//        return carRepository.save(car);
//    }
//    public String delete(Long id){
//        if(carRepository.existsById(id)) {
//            carRepository.deleteById(id);
//            return "Car with ID " + id + " deleted successfully";
//        }else return "Car with ID " + id + " not found!";
//    }
//
//    public String updateCar(Long id, Car updatedCar) {
//        if (carRepository.existsById(id)) {
//            Car car = carRepository.findById(id).get();
//            car.setCar_name(updatedCar.getCar_name());
//            car.setYear(updatedCar.getYear());
//            car.setType(updatedCar.getType());
//            car.setVolume(updatedCar.getVolume());
//            car.setManufacturer(updatedCar.getManufacturer());
//            carRepository.save(car);
//            return "Car with ID " + id + " updated successfully";
//        } else {
//            return "Car with ID " + id + " not found";
//        }
//    }
//

}
