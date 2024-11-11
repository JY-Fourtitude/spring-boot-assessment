package dto;

import lombok.Data;
import java.time.LocalDateTime;



@Data
public class UpdateProfileRequest {
    private String displayusername;
    private LocalDateTime timestamp;
}
