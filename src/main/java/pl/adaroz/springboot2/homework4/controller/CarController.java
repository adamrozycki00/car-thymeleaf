package pl.adaroz.springboot2.homework4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
        model.addAttribute("car", carOptional.get());
        return "/car/selected";
    }

    @GetMapping("/add")
    public String addCar(Model model) {
        model.addAttribute("car", new Car());
        return "/car/add";
    }

    @PostMapping("/add/result")
    public String addCar(@ModelAttribute Car car) {
        listOfCars.add(car);
        return "/car/add";
    }

    @GetMapping("/mod/{id}")
    public String modCar(@PathVariable long id,
                         Model model) {
        Optional<Car> carOptional = listOfCars.stream().filter(c -> c.getId() == id).findFirst();
        model.addAttribute("car", carOptional.get());
        return "/car/mod";
    }

    @PostMapping("/mod/result")
    public String modCar(@ModelAttribute Car car) {
        long id = car.getId();
        Optional<Car> carOptional = listOfCars.stream().filter(c -> c.getId() == id).findFirst();
        listOfCars.remove(carOptional.get());
        listOfCars.add(car);
        return "redirect:/cars";
    }

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
