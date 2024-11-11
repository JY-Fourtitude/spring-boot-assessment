package repo;

//import java.awt.print.Pageable;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import models.Car;

public interface CarRepository extends JpaRepository<Car, Long> {
    @Query("SELECT c FROM Car c WHERE LOWER(c.carname) LIKE LOWER(CONCAT('%', :carname, '%'))")
    Page<Car> findByCarNameContainingIgnoreCase(@Param("carname") String carname, Pageable pageable);
    
    @Query("SELECT COUNT(c) FROM Car c WHERE LOWER(c.carname) LIKE LOWER(CONCAT('%', :carname, '%'))")
    long countByCarNameContainingIgnoreCase(@Param("carname") String carname);
}
