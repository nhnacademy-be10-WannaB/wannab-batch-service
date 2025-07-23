package shop.wannab.batchserver.batch;

import jakarta.persistence.EntityManagerFactory;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import shop.wannab.batchserver.batch.model.UserSpend;
import shop.wannab.batchserver.user.domain.entity.User;

@Configuration
@AllArgsConstructor
public class BatchConfig {
    private final EntityManagerFactory entityManagerFactory;
    private final int chunkSize = 10;



    @Bean
    public JpaPagingItemReader<UserSpend> userSpendReader() {
        LocalDateTime threeMonthsAgoDate = LocalDateTime.now().minusMonths(3);

        Map<String, Object> params = new HashMap<>();
        params.put("startDate", threeMonthsAgoDate);

        return new JpaPagingItemReaderBuilder<UserSpend>()
                .name("userSpendReader")
                .entityManagerFactory(entityManagerFactory)
                .pageSize(chunkSize)
                .queryString("""
                    select new shop.wannab.batchserver.batch.model.UserSpend(
                        o.userId,
                        sum(o.totalBookPrice),
                        sum(o.totalDiscountAmount)
                    )
                    from Order o
                    where o.userId is not null
                        and o.orderAt >= :startDate
                        and o.orderStatus in (
                            shop.wannab.batchserver.order.OrderStatus.COMPLETED,
                            shop.wannab.batchserver.order.OrderStatus.SHIPPING
                        )
                    group by o.userId
                    order by o.userId
                """)
                .parameterValues(params)
                .build();
    }


    @Bean
    public JpaItemWriter<User> writer() {
        return new JpaItemWriterBuilder<User>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }

    @Bean
    public JpaPagingItemReader<User> userReader() {

        return new JpaPagingItemReaderBuilder<User>()
                .name("userUserReader")
                .entityManagerFactory(entityManagerFactory)
                .pageSize(chunkSize)
                .queryString("""
                     select u from User u
                """)
                .build();
    }

}
