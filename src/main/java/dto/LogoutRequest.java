package dto;

import lombok.Data;
import java.time.LocalDateTime;


@Data
public class LogoutRequest {
    private LocalDateTime timestamp;
}
