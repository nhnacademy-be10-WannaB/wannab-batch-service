package shop.wannab.batchserver.global.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter @Setter
@Component
@ConfigurationProperties(prefix = "batch.schedule")
public class BatchScheduleProperties {
    private String updateUserGrade;
    private String updateUserState;
}

