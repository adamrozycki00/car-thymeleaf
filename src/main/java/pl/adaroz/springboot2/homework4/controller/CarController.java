package pl.adaroz.springboot2.homework4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.adaroz.springboot2.homework4.model.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cars")
public class CarController {

    private List<Car> listOfCars;

    public CarController() {
        this.listOfCars = new ArrayList<>();
        listOfCars.add(new Car(1L, "Toyota", "Avensis", "silver"));
        listOfCars.add(new Car(2L, "Porsche", "911", "blue"));
        listOfCars.add(new Car(3L, "BMW", "GT", "black"));
    }

    @GetMapping
    public String getCars(Model model) {
        model.addAttribute("cars", listOfCars);
        return "/car/list";
    }

    @GetMapping("/id/{id}")
    public String getCarById(@PathVariable long id,
                             Model model) {
        Optional<Car> carOptional = listOfCars.stream().filter(c -> c.getId() == id).findFirst();
        //Car car = carOptional.isPresent() ? car
        model.addAttribute("car", carOptional.get());
        return "/car/selected";
    }

//    @GetMapping("/color/{color}")
//    public ResponseEntity<List<Car>> getCarsByColor(@PathVariable String color) {
//        List<Car> carsOfColor = listOfCars.stream().filter(car -> car.getColor().equals(color)).collect(Collectors.toList());
//        if (!carsOfColor.isEmpty())
//            return new ResponseEntity<>(carsOfColor, HttpStatus.OK);
//        return new ResponseEntity(HttpStatus.NOT_FOUND);
//    }
//
//    @PostMapping
//    public ResponseEntity addCar(@RequestBody Car car) {
//        boolean added = listOfCars.add(car);
//        if (added)
//            return new ResponseEntity(HttpStatus.CREATED);
//        else
//            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @PutMapping
//    public ResponseEntity modCar(@RequestBody Car newCar) {
//        Optional<Car> optCar = listOfCars.stream().filter(car -> car.getId() == newCar.getId()).findFirst();
//        if (optCar.isPresent()) {
//            listOfCars.remove(optCar.get());
//            listOfCars.add(newCar);
//            return new ResponseEntity(HttpStatus.OK);
//        }
//        return new ResponseEntity(HttpStatus.NOT_FOUND);
//    }
//
//    @PatchMapping
//    public ResponseEntity modColor(@RequestHeader long id,
//                                   @RequestHeader("color") String newColor) {
//        Optional<Car> optCar = listOfCars.stream().filter(car -> car.getId() == id).findFirst();
//        if (optCar.isPresent()) {
//            optCar.get().setColor(newColor);
//            return new ResponseEntity(HttpStatus.ACCEPTED);
//        }
//        return new ResponseEntity(HttpStatus.NOT_FOUND);
//    }
//
//    @DeleteMapping("/id/{id}")
//    public ResponseEntity<Car> removeCar(@PathVariable long id) {
//        Optional<Car> optCar = listOfCars.stream().filter(car -> car.getId() == id).findFirst();
//        if (optCar.isPresent()) {
//            listOfCars.remove(optCar.get());
//            return new ResponseEntity<>(optCar.get(), HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

}
