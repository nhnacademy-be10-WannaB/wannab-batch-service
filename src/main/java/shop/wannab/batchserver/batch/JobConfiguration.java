package shop.wannab.batchserver.batch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import shop.wannab.batchserver.batch.model.UserSpend;
import shop.wannab.batchserver.batch.processor.UpdateUserGradeProcessor;
import shop.wannab.batchserver.batch.processor.UpdateUserStateProcessor;
import shop.wannab.batchserver.user.domain.entity.User;

@Configuration
@RequiredArgsConstructor
public class JobConfiguration {

    private final PlatformTransactionManager platformTransactionManager;
    private final JobRepository jobRepository;

    @Bean
    public Job updateUserStateJob(Step updateUserStateStep) {
        return new JobBuilder("updateUserStateJob", jobRepository)
                .start(updateUserStateStep)
                .build();
    }

    @Bean
    public Job updateUserGradeJob(Step updateUserGradeStep) {
        return new JobBuilder("updateUserGradeJob", jobRepository)
                .start(updateUserGradeStep)
                .build();
    }

    @Bean
    public Step updateUserGradeStep(JpaPagingItemReader<UserSpend> userSpendReader,
                                    UpdateUserGradeProcessor processor,
                                    JpaItemWriter<User> writer) {

        return new StepBuilder("updateUserGradeStep", jobRepository)
                .<UserSpend, User>chunk(10, platformTransactionManager)
                .reader(userSpendReader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public Step updateUserStateStep(JpaPagingItemReader<User> userReader,
                                    UpdateUserStateProcessor processor,
                                    JpaItemWriter<User> writer) {

        return new StepBuilder("updateUserStateStep", jobRepository)
                .<User, User>chunk(10, platformTransactionManager)
                .reader(userReader)
                .processor(processor)
                .writer(writer)
                .build();
    }

}
