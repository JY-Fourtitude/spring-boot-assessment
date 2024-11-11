package services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import dto.CarListRequest;
import models.Car;
import repo.CarRepository;

@Service
public class CarService {
    
    @Autowired
    private CarRepository carRepository;
    
    public Map<String, Object> getCarList(CarListRequest request) {
        // Convert to zero-based index for Spring pagination
        int pageIndex = request.getPageindex() - 1;
        
        // Get paginated results
        Page<Car> carPage = carRepository.findByCarNameContainingIgnoreCase(
            request.getCarname() != null ? request.getCarname() : "",
            PageRequest.of(pageIndex, request.getPagesize())
        );
        
        // Get total count for the search criteria
        long totalCount = carRepository.countByCarNameContainingIgnoreCase(
            request.getCarname() != null ? request.getCarname() : ""
        );
        
        Map<String, Object> response = new HashMap<>();
        response.put("list", carPage.getContent());
        response.put("totalcount", totalCount);
        
        return response;
    }
}