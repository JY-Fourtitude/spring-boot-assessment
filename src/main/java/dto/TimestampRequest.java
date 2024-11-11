package dto;

import lombok.Data;
import java.time.LocalDateTime;


@Data
public class TimestampRequest {
    private LocalDateTime timestamp;
}