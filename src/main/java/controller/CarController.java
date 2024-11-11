package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dto.CarListRequest;
import services.CarService;

@RestController
@RequestMapping("/api")
public class CarController {
    
    @Autowired
    private CarService carService;
    
    @PostMapping("/carlist")
    public ResponseEntity<?> getCarList(@RequestBody CarListRequest request) {
        return ResponseEntity.ok(carService.getCarList(request));
    }
}
