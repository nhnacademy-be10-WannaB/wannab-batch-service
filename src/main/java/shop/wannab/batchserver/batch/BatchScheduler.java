package shop.wannab.batchserver.batch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;
import shop.wannab.batchserver.global.config.BatchScheduleProperties;

@Slf4j
@Component
@RequiredArgsConstructor
@EnableScheduling
public class BatchScheduler implements SchedulingConfigurer {

    private final JobLauncher jobLauncher;
    private final Job updateUserGradeJob;
    private final Job updateUserStateJob;
    private final BatchScheduleProperties scheduleProperties;

    public void runUpdateUserGradeJob() {
        JobParameters params = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        try {
            jobLauncher.run(updateUserGradeJob, params);
        } catch (Exception e) {
            log.error("runUpdateUserGradeJob Error : {}", e.getMessage());
        }

    }

    public void runUpdateUserStateJob() {
        JobParameters params = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        try{
            jobLauncher.run(updateUserStateJob, params);
        } catch (Exception e){
            log.error("runUpdateUserStateJob Error : {}", e.getMessage());
        }

    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addCronTask(this::runUpdateUserGradeJob, scheduleProperties.getUpdateUserGrade());
        taskRegistrar.addCronTask(this::runUpdateUserStateJob, scheduleProperties.getUpdateUserState());
    }
}
