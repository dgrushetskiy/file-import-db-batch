package ru.gothmog.importdb.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@EnableScheduling
public class ScheduledTasks {

    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    @Qualifier("importDataJob")
    private Job importDataJob;
    @Autowired
    @Qualifier("job-cityInfoData")
    Job jobCityInformData;
    @Autowired
    @Qualifier("job-regionInfoData")
    Job jobRegionInformData;
    @Autowired
    @Qualifier("job-districtInfoData")
    Job jobDistrictInformData;
    @Autowired
    @Qualifier("job-areaInfoData")
    Job jobAreaInformData;

    @Scheduled(cron = "0 0 20 ? * FRI")//    0 0/46 * * * *  0 0 20 ? * TUE
    public void runJobImportData() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException{
        JobParametersBuilder builderImportData = new JobParametersBuilder();
        builderImportData.addDate("date",new Date());
        jobLauncher.run(importDataJob,builderImportData.toJobParameters());
    }
    @Scheduled(cron = "0 0/12 * * * *")//    0 0/46 * * * *  0 0 20 ? * TUE   0 0 20 ? * FRI
    public void runJobCityInformData() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException{
        JobParametersBuilder builderCityInformData = new JobParametersBuilder();
        builderCityInformData.addDate("date",new Date());
        jobLauncher.run(jobCityInformData,builderCityInformData.toJobParameters());
    }
    @Scheduled(cron = "0 0/14 * * * *")//    0 0/46 * * * *  0 0 20 ? * TUE
    public void runJobRegionInformData() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException{
        JobParametersBuilder builderRegionInformData = new JobParametersBuilder();
        builderRegionInformData.addDate("date",new Date());
        jobLauncher.run(jobRegionInformData,builderRegionInformData.toJobParameters());
    }
    @Scheduled(cron = "0 0/16 * * * *")//    0 0/46 * * * *  0 0 20 ? * TUE
    public void runJobDistrictInformData()throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException{
        JobParametersBuilder builderDistrictInformData = new JobParametersBuilder();
        builderDistrictInformData.addDate("date",new Date());
        jobLauncher.run(jobDistrictInformData,builderDistrictInformData.toJobParameters());
    }
    @Scheduled(cron = "0 0/18 * * * *")//    0 0/46 * * * *  0 0 20 ? * TUE
    public void runJobAreaInformData()throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException{
        JobParametersBuilder builderAreaInformData = new JobParametersBuilder();
        builderAreaInformData.addDate("date",new Date());
        jobLauncher.run(jobAreaInformData,builderAreaInformData.toJobParameters());
    }
}
