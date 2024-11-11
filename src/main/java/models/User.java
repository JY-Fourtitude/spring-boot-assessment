package models;

import lombok.Data;
import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "users_assessment")
public class User {
    @Id
    @GeneratedValue
    private UUID userid;
    
    @Column(unique = true, nullable = false)
    private String username;
    
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    private String displayusername;
}