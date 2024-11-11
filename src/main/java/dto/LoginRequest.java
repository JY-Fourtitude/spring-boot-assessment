package dto;

import lombok.Data;
import java.time.LocalDateTime;


@Data
public class LoginRequest {
    private String username;
    private String password;
    private LocalDateTime timestamp;
}
