package dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CarListRequest {
    private String carname;
    private int pageindex;
    private int pagesize;
    private LocalDateTime timestamp;
}
