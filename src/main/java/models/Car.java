package models;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String carname;
    
    @Column(nullable = false)
    private String brand;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CarVariance> variance;
}
