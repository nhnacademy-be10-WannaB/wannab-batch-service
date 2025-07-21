package shop.wannab.batchserver.global.config;

import java.time.Duration;
import javax.sql.DataSource;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "dbcp2.datasource")
public class DataSourceConfig {

    private String driverClassName;
    private String url;
    private String username;
    private String password;
    private Integer maxIdle;
    private Integer maxTotal;
    private Integer initialSize;
    private Integer minIdle;
    private Long betweenEvictionMillis;
    private Long minEvictableIdleMillis;
    private Integer numTestsPerEvictionRun;

    @Bean
    public DataSource dataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();

        basicDataSource.setDriverClassName(driverClassName);
        basicDataSource.setUrl(url);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);
        basicDataSource.setMaxIdle(maxIdle);
        basicDataSource.setMaxTotal(maxTotal);
        basicDataSource.setInitialSize(initialSize);
        basicDataSource.setMinIdle(minIdle);

        basicDataSource.setValidationQuery("SELECT 1");
        basicDataSource.setTestOnReturn(true);
        basicDataSource.setTestOnBorrow(true);
        basicDataSource.setTestWhileIdle(true);

        basicDataSource.setDurationBetweenEvictionRuns(Duration.ofMinutes(betweenEvictionMillis));
        basicDataSource.setMinEvictableIdle(Duration.ofMinutes(minEvictableIdleMillis));
        basicDataSource.setNumTestsPerEvictionRun(numTestsPerEvictionRun);

        return basicDataSource;
    }
}
