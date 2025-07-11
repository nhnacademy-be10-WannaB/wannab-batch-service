package shop.wannab.batchserver.batch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@EnableScheduling
public class BatchScheduler {

    private final JobLauncher jobLauncher;
    private final Job updateUserGradeJob;
    private final Job updateUserStateJob;

    // TODO : 변경 필요
    @Scheduled(cron = "0 */2 * * * ?")
    public void runUpdateUserGradeJob() throws Exception {
        JobParameters params = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        jobLauncher.run(updateUserGradeJob, params);
    }

    // TODO : 변경 필요
    @Scheduled(cron = "0 1/2 * * * ?")
    public void runUpdateUserStateJob() throws Exception {
        JobParameters params = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        jobLauncher.run(updateUserStateJob, params);
    }
}
