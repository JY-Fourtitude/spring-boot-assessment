package util;

import models.Car;
import models.CarVariance;
import models.User;
import repo.CarRepository;
import repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;


@Configuration
@Order(1)
public class DatabaseInitializerConfig implements ApplicationListener<ApplicationReadyEvent> {
    
    private static final Logger logger = LoggerFactory.getLogger(DatabaseInitializerConfig.class);
    
    @Autowired
    private CarRepository carRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent event) {
        initializeData();
    }
    
    @Transactional
    public void initializeData() {
        logger.info("Starting data initialization...");
        
        try {
            // Initialize test user
            if (userRepository.count() == 0) {
                logger.info("Creating test user...");
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                
                User admin = new User();
                admin.setUsername("superadmin");
                admin.setPassword(passwordEncoder.encode("pa$$w0rd"));
                admin.setDisplayusername("my real name");
                userRepository.saveAndFlush(admin);
                logger.info("Test user created successfully");
            } else {
                logger.info("Users already exist, skipping user creation");
            }

            // Initialize cars
            if (carRepository.count() == 0) {
                logger.info("Creating sample cars...");
                
                // Create Mazda
                Car mazda = new Car();
                mazda.setCarname("3 MPS");
                mazda.setBrand("Mazda");
                mazda.setDescription("Blab la bla");
                
                CarVariance mazdaFullSpec = new CarVariance();
                mazdaFullSpec.setName("Full Spec");
                mazdaFullSpec.setPrice(new BigDecimal("175000"));
                mazdaFullSpec.setCar(mazda);
                
                CarVariance mazdaManualSpec = new CarVariance();
                mazdaManualSpec.setName("Manual Spec");
                mazdaManualSpec.setPrice(new BigDecimal("105000"));
                mazdaManualSpec.setCar(mazda);
                
                mazda.setVariance(Arrays.asList(mazdaFullSpec, mazdaManualSpec));
                carRepository.saveAndFlush(mazda);
                
                // Create Renault
                Car renault = new Car();
                renault.setCarname("RS 250 Cup");
                renault.setBrand("Renault");
                renault.setDescription("Blab la bla");
                
                CarVariance renaultFullSpec = new CarVariance();
                renaultFullSpec.setName("Full Spec");
                renaultFullSpec.setPrice(new BigDecimal("1375000"));
                renaultFullSpec.setCar(renault);
                
                CarVariance renaultManualSpec = new CarVariance();
                renaultManualSpec.setName("Manual Spec");
                renaultManualSpec.setPrice(new BigDecimal("1105000"));
                renaultManualSpec.setCar(renault);
                
                renault.setVariance(Arrays.asList(renaultFullSpec, renaultManualSpec));
                carRepository.saveAndFlush(renault);
                
                logger.info("Sample cars created successfully");
            } else {
                logger.info("Cars already exist, skipping car creation");
            }
            
            logger.info("Data initialization completed successfully");
            
        } catch (Exception e) {
            logger.error("Error during data initialization", e);
            throw new RuntimeException("Failed to initialize data", e);
        }
    }
}
